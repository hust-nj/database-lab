echo -e "DROP DATABASE sqllab;\nCREATE DATABASE sqllab;\nuse sqllab;\n" > whole.sql
cat 数据定义.sql 触发器.sql 数据添加.sql >> whole.sql

