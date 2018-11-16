


----删除数据

delete from sys_org_agent sa where sa.id=8;
delete from sys_agent sa where sa.id=8;
delete from sys_org  so where so.id in (15,16);


delete from SYS_ROLE_RESOURCE sr where sr.resource_id in (44,45,46);
delete from sys_resource sr where sr.id in (44,45,46);

delete from SYS_ROLE_RESOURCE sr where sr.resource_id in (47,48,49);
delete from sys_resource sr where sr.id in (47,48,49);

delete from SYS_ROLE_RESOURCE t where t.RESOURCE_ID in(51,52,53,54);
delete from sys_resource t where t.ID in(51,52,53,54);

delete from SYS_ROLE_RESOURCE t where t.RESOURCE_ID in(55,56,57,58);
delete from sys_resource t where t.ID in(55,56,57,58);


delete from sys_org_customer so where so.id in (1,2,3,4,5);

delete from sys_customer sc where sc.id in (1,2,3,4,5);

--删除表结构
drop table SYS_ORG_CUSTOMER;
drop table SYS_CUSTOMER;


ALTER TABLE sys_game 
DROP CONSTRAINT SYS_GAME_FK_GAME_SYSTEM;

ALTER TABLE sys_game 
DROP COLUMN SYSTEM_ID;

drop table SYS_GAME_SYSTEM;


commit;

