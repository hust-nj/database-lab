/* 创建备份设备并命名，ems是设备逻辑名 */
EXEC sp_addumpdevice 'disk', 'ems', 'e:\\workspace\\数据库实验\\lab1.bak';
/* 备份完整数据库 */
BACKUP DATABASE microblogging TO ems;
/* 备份事务日志 */
BACKUP LOG microblogging TO ems;
/* 恢复数据库 */
RESTORE DATABASE microblogging FROM ems