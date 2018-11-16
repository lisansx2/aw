--1、删除原有表结构


--2、新建的表结构


--大客户表
drop table SYS_CUSTOMER;
create table SYS_CUSTOMER
(
  id          NUMBER(19) not null,
  code        VARCHAR2(20),
  name        VARCHAR2(50),
  province_no VARCHAR2(6),
  province_id NUMBER(19)
)
tablespace TS_OTHERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column SYS_CUSTOMER.code
  is '大客户编码';
comment on column SYS_CUSTOMER.name
  is '大客户名称';
comment on column SYS_CUSTOMER.province_no
  is '省中心编码';
comment on column SYS_CUSTOMER.province_id
  is '省中心ID';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SYS_CUSTOMER
  add primary key (ID)
  using index 
  tablespace TS_OTHERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table SYS_CUSTOMER
  add constraint CUSTOMER_FK_PROVINCE foreign key (PROVINCE_ID)
  references SYS_PROVINCE (ID);
  
 
 
 ----机构大客户关系表
 drop table SYS_ORG_CUSTOMER;
create table SYS_ORG_CUSTOMER
(
  id          NUMBER(19) not null,
  org_id      NUMBER(19),
  customer_id NUMBER(19)
)
tablespace TS_OTHERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column SYS_ORG_CUSTOMER.org_id
  is '机构ID';
comment on column SYS_ORG_CUSTOMER.customer_id
  is '大客户ID';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SYS_ORG_CUSTOMER
  add constraint SYS_ORG_CUSTOMER_PK primary key (ID)
  using index 
  tablespace TS_OTHERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table SYS_ORG_CUSTOMER
  add constraint ORG_CUSTOMER_FK_CUSTOMER foreign key (CUSTOMER_ID)
  references SYS_CUSTOMER (ID);
alter table SYS_ORG_CUSTOMER
  add constraint ORG_CUSTOMER_FK_ORG foreign key (ORG_ID)
  references SYS_ORG (ID); 
  
  
  
---游戏系统表 
ALTER TABLE sys_game 
DROP CONSTRAINT SYS_GAME_FK_GAME_SYSTEM;
drop table SYS_GAME_SYSTEM;
create table SYS_GAME_SYSTEM
(
  id   NUMBER(19) not null,
  code VARCHAR2(20),
  name VARCHAR2(20)
)
tablespace TS_OTHERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate primary, unique and foreign key constraints 
alter table SYS_GAME_SYSTEM
  add constraint SYS_GAME_SYSTEM_PK primary key (ID)
  using index 
  tablespace TS_OTHERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

 
--游戏系统记录初始化

delete from sys_game_system t where t.id in(1,2);
insert into sys_game_system (id,code,name) values (1,'10','电彩' );
insert into sys_game_system (id,code,name) values (2,'20','即开' );
 
----游戏表增加列

ALTER TABLE sys_game 
DROP CONSTRAINT SYS_GAME_FK_GAME_SYSTEM;

ALTER TABLE sys_game 
DROP COLUMN SYSTEM_ID;

alter table sys_game
add (SYSTEM_ID NUMBER(19) default 1 not null);

alter table SYS_GAME
  add constraint SYS_GAME_FK_GAME_SYSTEM foreign key (SYSTEM_ID)
  references SYS_GAME_SYSTEM (ID);
  

--3、初始化数据
delete from SYS_ROLE_RESOURCE sr where sr.resource_id in (44,45,46);
delete from sys_resource sr where sr.id in (44,45,46);


Insert into SYS_RESOURCE (ID,NAME,IDENTIFIER,URL,PARENT_ID,PARENT_IDS,WEIGHT,HAS_CHILDREN,IS_SHOW,ICON,RESOURCE_TYPE_ID) values (44,'游戏设置','gameSetting','/settings/gameSetting',40,'0/1/40',1,0,null,'fa fa-cog fa-fw',1);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (102,1,44);

Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (104,3,44);

Insert into SYS_RESOURCE (ID,NAME,IDENTIFIER,URL,PARENT_ID,PARENT_IDS,WEIGHT,HAS_CHILDREN,IS_SHOW,ICON,RESOURCE_TYPE_ID) values (45,'设置游戏','setGame','/settings/gameSetting/setGame',44,'0/1/40/44',0,0,null,'fa fa-cog fa-fw',2);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (105,1,45);

Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (107,3,45);

Insert into SYS_RESOURCE (ID,NAME,IDENTIFIER,URL,PARENT_ID,PARENT_IDS,WEIGHT,HAS_CHILDREN,IS_SHOW,ICON,RESOURCE_TYPE_ID) values (46,'查询已设置游戏api','queryGameSetted','/api/settings/gameSetting/queryGameSetted',44,'0/1/40/44',0,0,null,null,4);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (108,1,46);

Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (110,3,46);



--大客户功能
delete from SYS_ROLE_RESOURCE sr where sr.resource_id in (47,48,49);
delete from sys_resource sr where sr.id in (47,48,49);


Insert into SYS_RESOURCE (ID,NAME,IDENTIFIER,URL,PARENT_ID,PARENT_IDS,WEIGHT,HAS_CHILDREN,IS_SHOW,ICON,RESOURCE_TYPE_ID) values (47,'大客户设置','customerSetting','/settings/customerSetting',40,'0/1/40',1,0,null,'fa fa-cog fa-fw',1);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (111,1,47);

Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (113,3,47);


Insert into SYS_RESOURCE (ID,NAME,IDENTIFIER,URL,PARENT_ID,PARENT_IDS,WEIGHT,HAS_CHILDREN,IS_SHOW,ICON,RESOURCE_TYPE_ID) values (48,'查询已设置大客户api','queryCustomerSetted','/api/settings/customerSetting/queryCustomerSetted',47,'0/1/40/47',0,0,null,null,4);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (114,1,48);

Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (116,3,48);

Insert into SYS_RESOURCE (ID,NAME,IDENTIFIER,URL,PARENT_ID,PARENT_IDS,WEIGHT,HAS_CHILDREN,IS_SHOW,ICON,RESOURCE_TYPE_ID) values (49,'设置大客户','setCustomer','/settings/customerSetting/setCustomer',47,'0/1/40/47',0,0,null,'fa fa-cog fa-fw',2);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (117,1,49);

Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (119,3,49);








---门店即开交易报表
delete from SYS_ROLE_RESOURCE t where t.RESOURCE_ID in(51,52,53,54);
delete from sys_resource t where t.ID in(51,52,53,54);
insert into sys_resource (ID, NAME, IDENTIFIER, URL, PARENT_ID, PARENT_IDS, WEIGHT, HAS_CHILDREN, IS_SHOW, ICON, RESOURCE_TYPE_ID)
values (51, '门店即开兑奖激活报表', 'shopInstantTradeReport', '/reports/shopInstantTradeReport', 12, '0/1/12/', 4, 0, null, 'fa fa-square fa-fw', 1);

insert into sys_resource (ID, NAME, IDENTIFIER, URL, PARENT_ID, PARENT_IDS, WEIGHT, HAS_CHILDREN, IS_SHOW, ICON, RESOURCE_TYPE_ID)
values (52, '查询门店即开兑奖激活信息api', 'shopInstantTradeInfoApi', '/api/reports/shopInstantTradeReport/search', 51, '0/1/12/51', 0, 0, null, null, 4);

insert into sys_resource (ID, NAME, IDENTIFIER, URL, PARENT_ID, PARENT_IDS, WEIGHT, HAS_CHILDREN, IS_SHOW, ICON, RESOURCE_TYPE_ID)
values (53, '导出pdf格式的门店即开兑奖激活报表', 'exportPdfShopInstantTradeReport', '/reports/shopInstantTradeReport/exportFile/*', 51, '0/1/12/51', 0, 0, null, null, 2);

insert into sys_resource (ID, NAME, IDENTIFIER, URL, PARENT_ID, PARENT_IDS, WEIGHT, HAS_CHILDREN, IS_SHOW, ICON, RESOURCE_TYPE_ID)
values (54, '导出excel格式的门店即开兑奖激活报表', 'exportExcelShopInstantTradeReport', '/reports/shopInstantTradeReport/exportFile/*', 51, '0/1/12/51', 0, 0, null, null, 2);

--赋权限

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (120, 1, 51);
insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (121, 2, 51);
insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (122, 3, 51);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (123, 1, 52);
insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (124, 2, 52);
insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (125, 3, 52);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (126, 1, 53);
insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (127, 2, 53);
insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (128, 3, 53);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (129, 1, 54);
insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (130, 2, 54);
insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (131, 3, 54);



--门店即开促销兑奖报表
delete from SYS_ROLE_RESOURCE t where t.RESOURCE_ID in(55,56,57,58);
delete from sys_resource t where t.ID in(55,56,57,58);
insert into sys_resource (ID, NAME, IDENTIFIER, URL, PARENT_ID, PARENT_IDS, WEIGHT, HAS_CHILDREN, IS_SHOW, ICON, RESOURCE_TYPE_ID)
values (55, '门店即开促销兑奖报表', 'shopInstantPromotionReport', '/reports/shopInstantPromotionReport', 12, '0/1/12/', 5, 0, null, 'fa fa-square fa-fw', 1);

insert into sys_resource (ID, NAME, IDENTIFIER, URL, PARENT_ID, PARENT_IDS, WEIGHT, HAS_CHILDREN, IS_SHOW, ICON, RESOURCE_TYPE_ID)
values (56, '查询门店即开促销兑奖信息api', 'shopInstantPromotionInfoApi', '/api/reports/shopInstantPromotionReport/search', 55, '0/1/12/55', 0, 0, null, null, 4);

insert into sys_resource (ID, NAME, IDENTIFIER, URL, PARENT_ID, PARENT_IDS, WEIGHT, HAS_CHILDREN, IS_SHOW, ICON, RESOURCE_TYPE_ID)
values (57, '导出pdf格式的门店即开促销兑奖报表', 'exportPdfShopInstantPromotionReport', '/reports/shopInstantPromotionReport/exportFile/*', 55, '0/1/12/55', 0, 0, null, null, 2);

insert into sys_resource (ID, NAME, IDENTIFIER, URL, PARENT_ID, PARENT_IDS, WEIGHT, HAS_CHILDREN, IS_SHOW, ICON, RESOURCE_TYPE_ID)
values (58, '导出excel格式的门店即开促销兑奖报表', 'exportExcelShopInstantPromotionReport', '/reports/shopInstantPromotionReport/exportFile/*', 55, '0/1/12/55', 0, 0, null, null, 2);



insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (132, 1, 55);
insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (133, 2, 55);
insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (134, 3, 55);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (135, 1, 56);
insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (136, 2, 56);
insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (137, 3, 56);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (138, 1, 57);
insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (139, 2, 57);
insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (140, 3, 57);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (141, 1, 58);
insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (142, 2, 58);
insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (143, 3, 58);


--广东省中心
Insert into SYS_ORG (ID,NAME,PARENT_ID,PARENT_IDS,IS_SHOW,HAS_CHILDREN,WEIGHT,CODE,PARENT_CODE,PROVINCE_ID,ORG_TYPE_ID) values (15,'广东省中心',2,'0/1/2',1,1,44,'00044','000',20,1);
--美宜佳品牌机构
Insert into SYS_ORG (ID,NAME,PARENT_ID,PARENT_IDS,IS_SHOW,HAS_CHILDREN,WEIGHT,CODE,PARENT_CODE,PROVINCE_ID,ORG_TYPE_ID) values (16,'广东美宜佳',3,'0/1/3',1,0,1,'001007','001',20,2);

--代理
Insert into SYS_AGENT (ID,CODE,NAME,STATE_ID,PROVINCE_NO,CREATE_DATE,LAST_OPER_DATE,PROVINCE_ID) values (8,'440001','广东美宜佳',1,'44',null,null,20);
--代理机构
Insert into SYS_ORG_AGENT (ID,ORG_ID,AGENT_ID) values (8,16,8);



---大客户数据

insert into sys_customer (ID, CODE, NAME, PROVINCE_NO, PROVINCE_ID)
values (1, '370102', '济南孟鑫超市', '37', 16);

insert into sys_customer (ID, CODE, NAME, PROVINCE_NO, PROVINCE_ID)
values (2, '370103', '济南倍全超市', '37', 16);

insert into sys_customer (ID, CODE, NAME, PROVINCE_NO, PROVINCE_ID)
values (3, '370104', '济南华联鲜超', '37', 16);

insert into sys_customer (ID, CODE, NAME, PROVINCE_NO, PROVINCE_ID)
values (4, '370301', '青岛可好超市', '37', 16);


insert into sys_customer (ID, CODE, NAME, PROVINCE_NO, PROVINCE_ID)
values (5, '370302', '青岛友客超市', '37', 16);



 insert into sys_org_customer(ID,ORG_ID,CUSTOMER_ID)
values (1,7, 1);
 insert into sys_org_customer(ID,ORG_ID,CUSTOMER_ID)
values (2, 10,2);

 insert into sys_org_customer(ID,ORG_ID,CUSTOMER_ID)
values (3, 11, 3);

 insert into sys_org_customer(ID,ORG_ID,CUSTOMER_ID)
values (4,13, 4);

 insert into sys_org_customer(ID,ORG_ID,CUSTOMER_ID)
values (5,14,5);




commit;