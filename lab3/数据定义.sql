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
