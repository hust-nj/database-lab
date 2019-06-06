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
DELIMITER ;