DELETE FROM employee;
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