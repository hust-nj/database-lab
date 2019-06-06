DROP DATABASE sqllab;
CREATE DATABASE sqllab;
use sqllab;

DROP TABLE 
IF EXISTS employee;
CREATE TABLE employee
(
eno CHAR(20) PRIMARY KEY,
password CHAR(20),
type SMALLINT
);

DROP TABLE
IF EXISTS goods;
CREATE TABLE goods
(
gno CHAR(20) PRIMARY KEY,
name CHAR(20),
place CHAR(20),
amount SMALLINT,
factory CHAR(20)
);

DROP TABLE
IF EXISTS purchase;
CREATE TABLE purchase
(
pno CHAR(20) PRIMARY KEY,
state SMALLINT,
provide_eno CHAR(20) REFERENCES employee(eno),
check_eno CHAR(20) REFERENCES employee(eno)
);

DROP TABLE
IF EXISTS purchase_include;
CREATE TABLE purchase_include
(
pno CHAR(20),
gno CHAR(20) REFERENCES goods(gno),
amount SMALLINT,
PRIMARY KEY(pno, gno),
FOREIGN KEY(pno) REFERENCES purchase(pno)
ON DELETE CASCADE
);

DROP TABLE
IF EXISTS shipping;
CREATE TABLE shipping
(
sno CHAR(20) PRIMARY KEY,
state SMALLINT,
register_time datetime,
provide_eno CHAR(20) REFERENCES employee(eno),
check_eno CHAR(20) REFERENCES employee(eno)
);

DROP TABLE
IF EXISTS shipping_include;
CREATE TABLE shipping_include
(
sno CHAR(20),
gno CHAR(20) REFERENCES goods(gno),
amount SMALLINT,
PRIMARY KEY(sno, gno),
FOREIGN KEY(sno) REFERENCES shipping(sno)
ON DELETE CASCADE
);
DROP TRIGGER
IF EXISTS pass_purchase;
DELIMITER $
CREATE TRIGGER pass_purchase
BEFORE UPDATE ON purchase
FOR EACH ROW
BEGIN
	IF(new.state = 2 and old.state = 1)
		THEN
			UPDATE goods, purchase_include
			SET goods.amount = goods.amount +	purchase_include.amount
			WHERE goods.gno = purchase_include.gno AND purchase_include.pno = new.pno;
	END IF;
END$
DELIMITER ;

DROP TRIGGER
IF EXISTS pass_shipping;
DELIMITER $
CREATE TRIGGER pass_shipping
AFTER UPDATE ON shipping
FOR EACH ROW
BEGIN
	IF(new.state = 3 and old.state !=3)
		THEN
			UPDATE goods, shipping_include
			SET goods.amount = goods.amount - shipping_include.amount
			WHERE goods.gno = shipping_include.gno AND shipping_include.sno = new.sno;
	END IF;
END$
DELIMITER ;

CREATE VIEW shipping_num(sno, num)
AS
(SELECT shipping.sno, COUNT(*)
FROM shipping, shipping_include
WHERE shipping.sno = shipping_include.sno AND shipping.state = 2
GROUP BY sno);

CREATE VIEW shipping_time(sno, num, time)
AS
(SELECT shipping.sno sno, COUNT(*) num, shipping.register_time time
FROM shipping, shipping_include, goods
WHERE shipping_include.sno = shipping.sno AND shipping.state = 2 AND goods.gno = shipping_include.gno and goods.amount >= shipping_include.amount
GROUP BY shipping.sno);

CREATE VIEW shipping_answer
AS
(SELECT sno
from shipping_time
WHERE time = 
(SELECT MIN(time)
FROM shipping_num, shipping_time
WHERE shipping_num.sno = shipping_time.sno and shipping_num.num = shipping_time.num));

DROP TRIGGER
IF EXISTS provide_lack;
DELIMITER $
CREATE TRIGGER provide_lack
AFTER UPDATE ON purchase
FOR EACH ROW
BEGIN
DECLARE answer CHAR(10);
	IF(new.state = 2 and old.state = 1)
		THEN
			WHILE ((SELECT COUNT(*) from shipping_answer) > 0) DO
				SELECT * INTO answer
				FROM shipping_answer;
				UPDATE shipping
				set shipping.state = 3
				WHERE shipping.sno = answer;
			END WHILE;
	END IF;
END$
DELIMITER ;DELETE FROM employee;
DELETE FROM goods;
DELETE FROM purchase;
DELETE FROM purchase_include;
DELETE FROM shipping;
DELETE FROM shipping_include;

INSERT INTO employee VALUES('000001', '000001', 1);
INSERT INTO employee VALUES('000002', '000002', 2);
INSERT INTO employee VALUES('000003', '000003', 3);

INSERT INTO goods VALUES(1,"HUAWEI P30", "武汉",0,"华为");
INSERT INTO goods VALUES(2,"HUAWEI P30 pro", "武汉",0,"华为");
INSERT INTO goods VALUES(3,"Honor 10", "深圳", 0, "华为");
INSERT INTO goods VALUES(4,"Honor V10", "深圳", 0, "华为");
INSERT INTO goods VALUES(5,"HUAWEI Mate 20", "广州", 0, "华为");
INSERT INTO goods VALUES(6,"HUAWEI Mate 20 pro", "广州",0,"华为");
INSERT INTO goods VALUES(7,"iPhone XR", "荆州", 0,"苹果");
INSERT INTO goods VALUES(8,"Galaxy A70", "长沙", 0,"三星");