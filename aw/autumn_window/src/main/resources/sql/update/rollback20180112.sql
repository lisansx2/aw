--1、删除原有表结构


--竞彩兑奖报表
delete from SYS_ROLE_RESOURCE t where t.RESOURCE_ID in(59,60,61,62);
delete from sys_resource t where t.ID in(59,60,61,62);

commit;
