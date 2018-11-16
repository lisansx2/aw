--1、删除原有表结构
drop table sys_user_organization;
drop table sys_organization;

ALTER TABLE SYS_USER 
DROP CONSTRAINT USER_FK_CHANNNEL_BRAND;

ALTER TABLE SYS_USER 
DROP COLUMN CHANNEL_BRAND_ID;

drop table SYS_CHANNEL_BRAND;

delete from qrtz_fired_triggers;
delete from qrtz_simple_triggers;
delete from qrtz_simprop_triggers;
delete from qrtz_cron_triggers;
delete from qrtz_blob_triggers;
delete from qrtz_triggers;
delete from qrtz_job_details;
delete from qrtz_calendars;
delete from qrtz_paused_trigger_grps;
delete from qrtz_locks;
delete from qrtz_scheduler_state;
commit;

drop table SYS_PERIOD_SALES_REPORT_TEMP;

--2、新建的表结构
--------------------------------------------------------
--  文件已创建 - 星期五-九月-22-2017   
--------------------------------------------------------
DROP TABLE "SYS_AGENT" cascade constraints;
DROP TABLE "SYS_AGENT_STATE" cascade constraints;
DROP TABLE "SYS_ORG" cascade constraints;
DROP TABLE "SYS_ORG_AGENT" cascade constraints;
DROP TABLE "SYS_USER_ORG" cascade constraints;
--------------------------------------------------------
--  DDL for Table SYS_AGENT
--------------------------------------------------------

  CREATE TABLE "SYS_AGENT" 
   (	"ID" NUMBER(19,0), 
	"CODE" VARCHAR2(20), 
	"NAME" VARCHAR2(50), 
	"STATE_ID" NUMBER(19,0), 
	"PROVINCE_NO" VARCHAR2(6), 
	"CREATE_DATE" TIMESTAMP (6), 
	"LAST_OPER_DATE" TIMESTAMP (6)
   ) ;
--------------------------------------------------------
--  DDL for Table SYS_AGENT_STATE
--------------------------------------------------------

  CREATE TABLE "SYS_AGENT_STATE" 
   (	"ID" NUMBER(19,0), 
	"CODE" VARCHAR2(20), 
	"NAME" VARCHAR2(20)
   ) ;
--------------------------------------------------------
--  DDL for Table SYS_ORG
--------------------------------------------------------

  CREATE TABLE "SYS_ORG" 
   (	"ID" NUMBER(19,0), 
	"NAME" VARCHAR2(100 CHAR), 
	"PARENT_ID" NUMBER(19,0), 
	"PARENT_IDS" VARCHAR2(200 CHAR), 
	"IS_SHOW" NUMBER(3,0), 
	"HAS_CHILDREN" NUMBER(3,0), 
	"WEIGHT" NUMBER(10,0), 
	"CODE" VARCHAR2(20), 
	"PARENT_CODE" VARCHAR2(20)
   ) ;
--------------------------------------------------------
--  DDL for Table SYS_ORG_AGENT
--------------------------------------------------------

  CREATE TABLE "SYS_ORG_AGENT" 
   (	"ID" NUMBER(19,0), 
	"ORG_ID" NUMBER(19,0), 
	"AGENT_ID" NUMBER(19,0)
   ) ;
--------------------------------------------------------
--  DDL for Table SYS_USER_ORG
--------------------------------------------------------

  CREATE TABLE "SYS_USER_ORG" 
   (	"ID" NUMBER(19,0), 
	"ORG_ID" NUMBER(19,0), 
	"USER_ID" NUMBER(19,0)
   ) ;
--------------------------------------------------------
--  DDL for Index SYS_AGENT_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_AGENT_PK" ON "SYS_AGENT" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index SYS_AGENT_STATE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_AGENT_STATE_PK" ON "SYS_AGENT_STATE" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index SYS_ORG_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_ORG_PK" ON "SYS_ORG" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index SYS_ORG_AGENT_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_ORG_AGENT_PK" ON "SYS_ORG_AGENT" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index SYS_USER_ORG_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_USER_ORG_PK" ON "SYS_USER_ORG" ("ID") 
  ;
--------------------------------------------------------
--  Constraints for Table SYS_AGENT
--------------------------------------------------------

  ALTER TABLE "SYS_AGENT" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "SYS_AGENT" ADD CONSTRAINT "SYS_AGENT_PK" PRIMARY KEY ("ID")
  USING INDEX  ENABLE;
--------------------------------------------------------
--  Constraints for Table SYS_AGENT_STATE
--------------------------------------------------------

  ALTER TABLE "SYS_AGENT_STATE" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "SYS_AGENT_STATE" ADD CONSTRAINT "SYS_AGENT_STATE_PK" PRIMARY KEY ("ID")
  USING INDEX  ENABLE;
--------------------------------------------------------
--  Constraints for Table SYS_ORG
--------------------------------------------------------

  ALTER TABLE "SYS_ORG" MODIFY ("NAME" NOT NULL ENABLE);
  ALTER TABLE "SYS_ORG" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "SYS_ORG" ADD CONSTRAINT "SYS_ORG_PK" PRIMARY KEY ("ID")
  USING INDEX  ENABLE;
--------------------------------------------------------
--  Constraints for Table SYS_ORG_AGENT
--------------------------------------------------------

  ALTER TABLE "SYS_ORG_AGENT" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "SYS_ORG_AGENT" ADD CONSTRAINT "SYS_ORG_AGENT_PK" PRIMARY KEY ("ID")
  USING INDEX  ENABLE;
--------------------------------------------------------
--  Constraints for Table SYS_USER_ORG
--------------------------------------------------------

  ALTER TABLE "SYS_USER_ORG" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "SYS_USER_ORG" ADD CONSTRAINT "SYS_USER_ORG_PK" PRIMARY KEY ("ID")
  USING INDEX  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table SYS_AGENT
--------------------------------------------------------

  ALTER TABLE "SYS_AGENT" ADD CONSTRAINT "SYS_AGENT_FK_AGENT_STATE" FOREIGN KEY ("STATE_ID")
	  REFERENCES "SYS_AGENT_STATE" ("ID") ENABLE;
    
--------------------------------------------------------
--  Ref Constraints for Table SYS_ORG
--------------------------------------------------------

  ALTER TABLE "SYS_ORG" ADD CONSTRAINT "ORG_FK_ORG" FOREIGN KEY ("PARENT_ID")
	  REFERENCES "SYS_ORG" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table SYS_ORG_AGENT
--------------------------------------------------------

  ALTER TABLE "SYS_ORG_AGENT" ADD CONSTRAINT "ORG_AGENT_FK_AGENT" FOREIGN KEY ("AGENT_ID")
	  REFERENCES "SYS_AGENT" ("ID") ENABLE;
  ALTER TABLE "SYS_ORG_AGENT" ADD CONSTRAINT "ORG_AGENT_FK_ORG" FOREIGN KEY ("ORG_ID")
	  REFERENCES "SYS_ORG" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table SYS_USER_ORG
--------------------------------------------------------

  ALTER TABLE "SYS_USER_ORG" ADD CONSTRAINT "USER_ORG_FK_ORG" FOREIGN KEY ("ORG_ID")
	  REFERENCES "SYS_ORG" ("ID") ENABLE;
  ALTER TABLE "SYS_USER_ORG" ADD CONSTRAINT "USER_ORG_FK_USER" FOREIGN KEY ("USER_ID")
	  REFERENCES "SYS_USER" ("ID") ENABLE;

Insert into SYS_AGENT_STATE (ID,CODE,NAME) values (1,'10','启用');
Insert into SYS_AGENT_STATE (ID,CODE,NAME) values (2,'20','停用');
Insert into SYS_AGENT_STATE (ID,CODE,NAME) values (3,'40','删除');
commit;

--------------------------------------------------------
--  文件已创建 - 星期三-九月-27-2017   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table SYS_PERIOD_SALES_REPORT_TEMP
--------------------------------------------------------

  CREATE TABLE "SYS_PERIOD_SALES_REPORT_TEMP" 
   (	"ID" NUMBER(19,0), 
	"REPORT_DATE" DATE, 
	"TERMINAL_ID" NUMBER, 
	"SHOP_ID" NUMBER, 
	"AGENT_ID" NUMBER, 
	"CITY_ID" NUMBER, 
	"PROVINCE_ID" NUMBER, 
	"TERMINAL_NO" VARCHAR2(20), 
	"AGENT_NO" VARCHAR2(20), 
	"CITY_NO" VARCHAR2(20), 
	"PROVINCE_NO" VARCHAR2(20), 
	"SHOP_NO" VARCHAR2(20)
   ) ;
--------------------------------------------------------
--  DDL for Index S_P_S_R_T_INDEX_SHOP_NO
--------------------------------------------------------

  CREATE INDEX "S_P_S_R_T_INDEX_SHOP_NO" ON "SYS_PERIOD_SALES_REPORT_TEMP" ("SHOP_NO") 
  ;
--------------------------------------------------------
--  DDL for Index S_P_S_R_T_INDEX_AGENT_NO
--------------------------------------------------------

  CREATE INDEX "S_P_S_R_T_INDEX_AGENT_NO" ON "SYS_PERIOD_SALES_REPORT_TEMP" ("AGENT_NO") 
  ;
--------------------------------------------------------
--  DDL for Index S_P_S_R_T_INDEX_REPORT_DATE
--------------------------------------------------------

  CREATE INDEX "S_P_S_R_T_INDEX_REPORT_DATE" ON "SYS_PERIOD_SALES_REPORT_TEMP" ("REPORT_DATE") 
  ;
--------------------------------------------------------
--  DDL for Index SYS_PERIOD_SALES_REPORT_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_PERIOD_SALES_REPORT_PK" ON "SYS_PERIOD_SALES_REPORT_TEMP" ("ID") 
  ;
--------------------------------------------------------
--  Constraints for Table SYS_PERIOD_SALES_REPORT_TEMP
--------------------------------------------------------

  ALTER TABLE "SYS_PERIOD_SALES_REPORT_TEMP" ADD CONSTRAINT "SYS_PERIOD_SALES_REPORT_PK" PRIMARY KEY ("ID")
  USING INDEX  ENABLE;
  ALTER TABLE "SYS_PERIOD_SALES_REPORT_TEMP" MODIFY ("ID" NOT NULL ENABLE);

  create or replace FUNCTION f_getavailableamount
/*
  ver=32.1
  db=qcdb,clct,qrdb,
  */
(p_balance_amount               IN NUMBER,
 p_credit_amount                IN NUMBER,
 p_attached_balance_amount      IN NUMBER,
 p_temp_credit_begin_time       IN DATE,
 p_temp_credit_available_length IN NUMBER,
 p_temp_credit_amount           IN NUMBER,
 p_trading_time                 IN DATE DEFAULT SYSDATE) --计算所要求的时间
 RETURN NUMBER AS
  /**************************************************************************
  Ver2.4版本建立，修订内容如下：
  Created by xiebo,zyy on 2012-3-1  Tiger系统 计算可用余额
  Ver2.6版本建立，修订内容如下：
  Created by xsb on 2012-4-17 函数名字修订，临时信用额度生效时长单位不对，修订成秒,
  本函数仅供内部存储过程调用
  Ver2.8版本建立，修订内容如下：
  Created by zyy on 2012-6-25 合并亦庄调优阶段产生的代码修订
  Ver2.9版本建立，修订内容如下：
  Created by xsb on 2012-7-9 优化代码，调整传入参数
  Ver3.5版本建立，修订内容如下：
  Created by zyy on 2012-11-8 额度存储过程上收;
  Ver32.1版本建立，修订内容如下：
  Created by zyy on 2013-11-13 统一版本号:三段版本号变两段,并且大版本号在后流水号在前;
  **************************************************************************/
  v_available_amount NUMBER := 0;
BEGIN
  --计算可用余额
  v_available_amount := nvl(p_balance_amount, 0) + nvl(p_credit_amount, 0) +
                        nvl(p_attached_balance_amount, 0);

  IF (p_trading_time >= p_temp_credit_begin_time) AND
     (p_trading_time <=
     p_temp_credit_begin_time + nvl(p_temp_credit_available_length, 0) / 24 / 60 / 60) THEN
    v_available_amount := v_available_amount + nvl(p_temp_credit_amount, 0);
  END IF;

  RETURN v_available_amount;
END;
/

--3、初始化数据
Insert into SYS_ORG (ID,NAME,PARENT_ID,PARENT_IDS,IS_SHOW,HAS_CHILDREN,WEIGHT,CODE,PARENT_CODE) values (1,'全部机构',null,null,1,1,0,'999',null);
Insert into SYS_ORG (ID,NAME,PARENT_ID,PARENT_IDS,IS_SHOW,HAS_CHILDREN,WEIGHT,CODE,PARENT_CODE) values (2,'体彩机构',1,'0/1',1,1,1,'000','999');
Insert into SYS_ORG (ID,NAME,PARENT_ID,PARENT_IDS,IS_SHOW,HAS_CHILDREN,WEIGHT,CODE,PARENT_CODE) values (3,'品牌机构',1,'0/1',1,1,2,'001','999');
Insert into SYS_ORG (ID,NAME,PARENT_ID,PARENT_IDS,IS_SHOW,HAS_CHILDREN,WEIGHT,CODE,PARENT_CODE) values (4,'山东省中心',2,'0/1/2',1,1,37,'00037','000');
Insert into SYS_ORG (ID,NAME,PARENT_ID,PARENT_IDS,IS_SHOW,HAS_CHILDREN,WEIGHT,CODE,PARENT_CODE) values (5,'济南市中心',4,'0/1/2/4',1,0,3701,'0003701','00037');
Insert into SYS_ORG (ID,NAME,PARENT_ID,PARENT_IDS,IS_SHOW,HAS_CHILDREN,WEIGHT,CODE,PARENT_CODE) values (6,'青岛市中心',4,'0/1/2/4',1,0,3703,'0003703','00037');

Insert into SYS_ORG (ID,NAME,PARENT_ID,PARENT_IDS,IS_SHOW,HAS_CHILDREN,WEIGHT,CODE,PARENT_CODE) values (7,'济南孟鑫',3,'0/1/3',1,1,1,'001001','001');
Insert into SYS_ORG (ID,NAME,PARENT_ID,PARENT_IDS,IS_SHOW,HAS_CHILDREN,WEIGHT,CODE,PARENT_CODE) values (8,'济南孟鑫直营',7,'0/1/3/7',1,0,1,'001001001','001001');
Insert into SYS_ORG (ID,NAME,PARENT_ID,PARENT_IDS,IS_SHOW,HAS_CHILDREN,WEIGHT,CODE,PARENT_CODE) values (9,'济南孟鑫加盟',7,'0/1/3/7',1,0,2,'001001002','001001');
Insert into SYS_ORG (ID,NAME,PARENT_ID,PARENT_IDS,IS_SHOW,HAS_CHILDREN,WEIGHT,CODE,PARENT_CODE) values (10,'济南倍全',3,'0/1/3',1,0,2,'001002','001');
Insert into SYS_ORG (ID,NAME,PARENT_ID,PARENT_IDS,IS_SHOW,HAS_CHILDREN,WEIGHT,CODE,PARENT_CODE) values (11,'济南华联生鲜',3,'0/1/3',1,0,3,'001003','001');
Insert into SYS_ORG (ID,NAME,PARENT_ID,PARENT_IDS,IS_SHOW,HAS_CHILDREN,WEIGHT,CODE,PARENT_CODE) values (12,'青岛迷你岛',3,'0/1/3',1,0,4,'001004','001');
Insert into SYS_ORG (ID,NAME,PARENT_ID,PARENT_IDS,IS_SHOW,HAS_CHILDREN,WEIGHT,CODE,PARENT_CODE) values (13,'青岛可好',3,'0/1/3',1,0,5,'001005','001');
Insert into SYS_ORG (ID,NAME,PARENT_ID,PARENT_IDS,IS_SHOW,HAS_CHILDREN,WEIGHT,CODE,PARENT_CODE) values (14,'青岛友客',3,'0/1/3',1,0,5,'001006','001');

Insert into SYS_USER_ORG (ID,ORG_ID,USER_ID) values (1,1,1);
Insert into SYS_USER_ORG (ID,ORG_ID,USER_ID) values (2,1,143176);
Insert into SYS_USER_ORG (ID,ORG_ID,USER_ID) values (3,1,140385);
Insert into SYS_USER_ORG (ID,ORG_ID,USER_ID) values (4,1,145046);
Insert into SYS_USER_ORG (ID,ORG_ID,USER_ID) values (5,1,140384);

Insert into SYS_AGENT (ID,CODE,NAME,STATE_ID,CREATE_DATE,LAST_OPER_DATE,PROVINCE_NO) values (1,'370101','济南孟鑫',1,null,null,'37');
Insert into SYS_AGENT (ID,CODE,NAME,STATE_ID,CREATE_DATE,LAST_OPER_DATE,PROVINCE_NO) values (2,'370102','济南倍全',1,null,null,'37');
Insert into SYS_AGENT (ID,CODE,NAME,STATE_ID,CREATE_DATE,LAST_OPER_DATE,PROVINCE_NO) values (3,'370103','济南华联生鲜',1,null,null,'37');
Insert into SYS_AGENT (ID,CODE,NAME,STATE_ID,CREATE_DATE,LAST_OPER_DATE,PROVINCE_NO) values (4,'370104','济南孟鑫加盟',1,null,null,'37');
Insert into SYS_AGENT (ID,CODE,NAME,STATE_ID,CREATE_DATE,LAST_OPER_DATE,PROVINCE_NO) values (5,'370301','青岛迷你岛',1,null,null,'37');
Insert into SYS_AGENT (ID,CODE,NAME,STATE_ID,CREATE_DATE,LAST_OPER_DATE,PROVINCE_NO) values (6,'370302','青岛可好',1,null,null,'37');
Insert into SYS_AGENT (ID,CODE,NAME,STATE_ID,CREATE_DATE,LAST_OPER_DATE,PROVINCE_NO) values (7,'370303','青岛友客',1,null,null,'37');


Insert into SYS_ORG_AGENT (ID,ORG_ID,AGENT_ID) values (1,8,1);
Insert into SYS_ORG_AGENT (ID,ORG_ID,AGENT_ID) values (2,10,2);
Insert into SYS_ORG_AGENT (ID,ORG_ID,AGENT_ID) values (3,11,3);
Insert into SYS_ORG_AGENT (ID,ORG_ID,AGENT_ID) values (4,9,4);
Insert into SYS_ORG_AGENT (ID,ORG_ID,AGENT_ID) values (5,12,5);
Insert into SYS_ORG_AGENT (ID,ORG_ID,AGENT_ID) values (6,13,6);
Insert into SYS_ORG_AGENT (ID,ORG_ID,AGENT_ID) values (7,14,7);

delete from SYS_ROLE_RESOURCE;
delete from SYS_RESOURCE;
Insert into SYS_RESOURCE (ID,NAME,IDENTIFIER,URL,PARENT_ID,PARENT_IDS,WEIGHT,HAS_CHILDREN,IS_SHOW,ICON,RESOURCE_TYPE_ID) values (21,'显示未选择机构用户','showNoOrgUser','/user/showNoOrgUser',3,'0/1/3',0,0,null,null,2);
Insert into SYS_RESOURCE (ID,NAME,IDENTIFIER,URL,PARENT_ID,PARENT_IDS,WEIGHT,HAS_CHILDREN,IS_SHOW,ICON,RESOURCE_TYPE_ID) values (22,'查询未选择机构用户','getNoOrgUser','/api/user/getNoOrgUser',3,'0/1/3',0,0,null,null,4);
Insert into SYS_RESOURCE (ID,NAME,IDENTIFIER,URL,PARENT_ID,PARENT_IDS,WEIGHT,HAS_CHILDREN,IS_SHOW,ICON,RESOURCE_TYPE_ID) values (28,'门店额度查询','shopBalance','/balance/shopBalance',23,'0/1/23',2,0,null,'fa fa-square fa-fw',1);
Insert into SYS_RESOURCE (ID,NAME,IDENTIFIER,URL,PARENT_ID,PARENT_IDS,WEIGHT,HAS_CHILDREN,IS_SHOW,ICON,RESOURCE_TYPE_ID) values (29,'门店额度查询api','queryShopBalance','/api/balance/shopBalance/queryBalance',28,'0/1/23/28',0,0,null,null,4);
Insert into SYS_RESOURCE (ID,NAME,IDENTIFIER,URL,PARENT_ID,PARENT_IDS,WEIGHT,HAS_CHILDREN,IS_SHOW,ICON,RESOURCE_TYPE_ID) values (30,'代理历史额度记录报表','agentHistoryBalanceReport','/reports/agentHistoryBalanceReport',12,'0/1/12/',2,0,null,'fa fa-square fa-fw',1);
Insert into SYS_RESOURCE (ID,NAME,IDENTIFIER,URL,PARENT_ID,PARENT_IDS,WEIGHT,HAS_CHILDREN,IS_SHOW,ICON,RESOURCE_TYPE_ID) values (31,'查询代理历史额度记录api','searchAgentHistoryBalanceApi','/api/reports/agentHistoryBalanceReport/search',30,'0/1/12/30',0,0,null,null,4);
Insert into SYS_RESOURCE (ID,NAME,IDENTIFIER,URL,PARENT_ID,PARENT_IDS,WEIGHT,HAS_CHILDREN,IS_SHOW,ICON,RESOURCE_TYPE_ID) values (32,'导出pdf格式的代理历史额度记录报表','exportPdfAgentHistoryBalanceReport','/reports/agentHistoryBalanceReport/exportFile/*',30,'0/1/12/30',0,0,null,null,2);
Insert into SYS_RESOURCE (ID,NAME,IDENTIFIER,URL,PARENT_ID,PARENT_IDS,WEIGHT,HAS_CHILDREN,IS_SHOW,ICON,RESOURCE_TYPE_ID) values (33,'导出excel格式的代理历史额度记录报表','exportExcelAgentHistoryBalanceReport','/reports/agentHistoryBalanceReport/exportFile/*',30,'0/1/12/30',0,0,null,null,2);
Insert into SYS_RESOURCE (ID,NAME,IDENTIFIER,URL,PARENT_ID,PARENT_IDS,WEIGHT,HAS_CHILDREN,IS_SHOW,ICON,RESOURCE_TYPE_ID) values (34,'门店历史额度记录报表','shopHistoryBalanceReport','/reports/shopHistoryBalanceReport',12,'0/1/12/',3,0,null,'fa fa-square fa-fw',1);
Insert into SYS_RESOURCE (ID,NAME,IDENTIFIER,URL,PARENT_ID,PARENT_IDS,WEIGHT,HAS_CHILDREN,IS_SHOW,ICON,RESOURCE_TYPE_ID) values (35,'查询门店历史额度记录api','searchShopHistoryBalanceApi','/api/reports/shopHistoryBalanceReport/search',34,'0/1/12/34',0,0,null,null,4);
Insert into SYS_RESOURCE (ID,NAME,IDENTIFIER,URL,PARENT_ID,PARENT_IDS,WEIGHT,HAS_CHILDREN,IS_SHOW,ICON,RESOURCE_TYPE_ID) values (36,'导出pdf格式的门店历史额度记录报表','exportPdfShopHistoryBalanceReport','/reports/shopHistoryBalanceReport/exportFile/*',34,'0/1/12/34',0,0,null,null,2);
Insert into SYS_RESOURCE (ID,NAME,IDENTIFIER,URL,PARENT_ID,PARENT_IDS,WEIGHT,HAS_CHILDREN,IS_SHOW,ICON,RESOURCE_TYPE_ID) values (37,'导出excel格式的门店历史额度记录报表','exportExcelShopHistoryBalanceReport','/reports/shopHistoryBalanceReport/exportFile/*',34,'0/1/12/30',0,0,null,null,2);
Insert into SYS_RESOURCE (ID,NAME,IDENTIFIER,URL,PARENT_ID,PARENT_IDS,WEIGHT,HAS_CHILDREN,IS_SHOW,ICON,RESOURCE_TYPE_ID) values (1,'全部资源','allResource',null,0,'0/',1,1,null,null,null);
Insert into SYS_RESOURCE (ID,NAME,IDENTIFIER,URL,PARENT_ID,PARENT_IDS,WEIGHT,HAS_CHILDREN,IS_SHOW,ICON,RESOURCE_TYPE_ID) values (2,'首页','home','/',1,'0/1/',1,0,null,'fa fa-home fa-fw',1);
Insert into SYS_RESOURCE (ID,NAME,IDENTIFIER,URL,PARENT_ID,PARENT_IDS,WEIGHT,HAS_CHILDREN,IS_SHOW,ICON,RESOURCE_TYPE_ID) values (3,'用户管理','userManage','/user',1,'0/1/',2,0,null,'fa fa-users fa-fw',1);
Insert into SYS_RESOURCE (ID,NAME,IDENTIFIER,URL,PARENT_ID,PARENT_IDS,WEIGHT,HAS_CHILDREN,IS_SHOW,ICON,RESOURCE_TYPE_ID) values (4,'新增用户','addUser','/user/addUser',3,'0/1/3/',0,0,null,null,2);
Insert into SYS_RESOURCE (ID,NAME,IDENTIFIER,URL,PARENT_ID,PARENT_IDS,WEIGHT,HAS_CHILDREN,IS_SHOW,ICON,RESOURCE_TYPE_ID) values (5,'查询用户列表','searchUserApi','/api/user/search',3,'0/1/3/',0,0,null,null,4);
Insert into SYS_RESOURCE (ID,NAME,IDENTIFIER,URL,PARENT_ID,PARENT_IDS,WEIGHT,HAS_CHILDREN,IS_SHOW,ICON,RESOURCE_TYPE_ID) values (6,'查询省下属的城市列表','commonGetProvinceCityList','/api/common/city/getProvinceCityList/*',14,'0/1/14/',0,0,null,null,4);
Insert into SYS_RESOURCE (ID,NAME,IDENTIFIER,URL,PARENT_ID,PARENT_IDS,WEIGHT,HAS_CHILDREN,IS_SHOW,ICON,RESOURCE_TYPE_ID) values (7,'修改用户','modifyUser','/user/modifyUser',3,'0/1/3/',0,0,null,null,3);
Insert into SYS_RESOURCE (ID,NAME,IDENTIFIER,URL,PARENT_ID,PARENT_IDS,WEIGHT,HAS_CHILDREN,IS_SHOW,ICON,RESOURCE_TYPE_ID) values (8,'删除用户','deleteUser','/user/deleteUser',3,'0/1/3/',0,0,null,null,2);
Insert into SYS_RESOURCE (ID,NAME,IDENTIFIER,URL,PARENT_ID,PARENT_IDS,WEIGHT,HAS_CHILDREN,IS_SHOW,ICON,RESOURCE_TYPE_ID) values (9,'启用用户','enableUser','/user/enableUser',3,'0/1/3/',0,0,null,null,2);
Insert into SYS_RESOURCE (ID,NAME,IDENTIFIER,URL,PARENT_ID,PARENT_IDS,WEIGHT,HAS_CHILDREN,IS_SHOW,ICON,RESOURCE_TYPE_ID) values (10,'停用用户','disableUser','/user/disableUser',3,'0/1/3/',0,0,null,null,2);
Insert into SYS_RESOURCE (ID,NAME,IDENTIFIER,URL,PARENT_ID,PARENT_IDS,WEIGHT,HAS_CHILDREN,IS_SHOW,ICON,RESOURCE_TYPE_ID) values (11,'分配角色','assignRoles','/user/assignRoles',3,'0/1/3/',0,0,null,null,3);
Insert into SYS_RESOURCE (ID,NAME,IDENTIFIER,URL,PARENT_ID,PARENT_IDS,WEIGHT,HAS_CHILDREN,IS_SHOW,ICON,RESOURCE_TYPE_ID) values (12,'报表管理','reportManage','#',1,'0/1/',4,1,null,'fa fa-bar-chart fa-fw',1);
Insert into SYS_RESOURCE (ID,NAME,IDENTIFIER,URL,PARENT_ID,PARENT_IDS,WEIGHT,HAS_CHILDREN,IS_SHOW,ICON,RESOURCE_TYPE_ID) values (13,'时段销量报表','periodSalesReport','/reports/periodSalesReport',12,'0/1/12/',1,0,null,'fa fa-square fa-fw',1);
Insert into SYS_RESOURCE (ID,NAME,IDENTIFIER,URL,PARENT_ID,PARENT_IDS,WEIGHT,HAS_CHILDREN,IS_SHOW,ICON,RESOURCE_TYPE_ID) values (14,'全部公共API','allApi',null,1,'0/1/',0,1,null,null,null);
Insert into SYS_RESOURCE (ID,NAME,IDENTIFIER,URL,PARENT_ID,PARENT_IDS,WEIGHT,HAS_CHILDREN,IS_SHOW,ICON,RESOURCE_TYPE_ID) values (15,'查询时段销量api','searchPeriodSalesReportApi','/api/reports/periodSalesReport/search',13,'0/1/12/13',0,0,null,null,4);
Insert into SYS_RESOURCE (ID,NAME,IDENTIFIER,URL,PARENT_ID,PARENT_IDS,WEIGHT,HAS_CHILDREN,IS_SHOW,ICON,RESOURCE_TYPE_ID) values (16,'导出pdf格式的时段销量报表','exportPdfPeriodSaleReport','/reports/periodSalesReport/exportFile/*',13,'0/1/12/13',0,0,null,null,2);
Insert into SYS_RESOURCE (ID,NAME,IDENTIFIER,URL,PARENT_ID,PARENT_IDS,WEIGHT,HAS_CHILDREN,IS_SHOW,ICON,RESOURCE_TYPE_ID) values (17,'导出excel格式的时段销量报表','exportExcelPeriodSaleReport','/reports/periodSalesReport/exportFile/*',13,'0/1/12/13',0,0,null,null,2);
Insert into SYS_RESOURCE (ID,NAME,IDENTIFIER,URL,PARENT_ID,PARENT_IDS,WEIGHT,HAS_CHILDREN,IS_SHOW,ICON,RESOURCE_TYPE_ID) values (18,'修改密码','modifyPwd','/user/modifyPwd',3,'0/1/3/',0,0,null,null,3);
Insert into SYS_RESOURCE (ID,NAME,IDENTIFIER,URL,PARENT_ID,PARENT_IDS,WEIGHT,HAS_CHILDREN,IS_SHOW,ICON,RESOURCE_TYPE_ID) values (26,'显示未分配角色用户','showNoRoleUser','/user/showNoRoleUser',3,'0/1/3',0,0,null,null,2);
Insert into SYS_RESOURCE (ID,NAME,IDENTIFIER,URL,PARENT_ID,PARENT_IDS,WEIGHT,HAS_CHILDREN,IS_SHOW,ICON,RESOURCE_TYPE_ID) values (27,'查询未分配角色用户','getNoRoleUser','/api/user/getNoRoleUser',3,'0/1/3',0,0,null,null,4);
Insert into SYS_RESOURCE (ID,NAME,IDENTIFIER,URL,PARENT_ID,PARENT_IDS,WEIGHT,HAS_CHILDREN,IS_SHOW,ICON,RESOURCE_TYPE_ID) values (20,'查询所辖机构','getOrgTree','/api/common/org/getOrgTree/*',14,'0/1/14',0,0,null,null,4);
Insert into SYS_RESOURCE (ID,NAME,IDENTIFIER,URL,PARENT_ID,PARENT_IDS,WEIGHT,HAS_CHILDREN,IS_SHOW,ICON,RESOURCE_TYPE_ID) values (23,'额度管理','balanceManage','#',1,'0/1',3,1,null,'fa fa-credit-card fa-fw',1);
Insert into SYS_RESOURCE (ID,NAME,IDENTIFIER,URL,PARENT_ID,PARENT_IDS,WEIGHT,HAS_CHILDREN,IS_SHOW,ICON,RESOURCE_TYPE_ID) values (24,'代理额度查询','agentBalance','/balance/agentBalance',23,'0/1/23',1,0,null,'fa fa-square fa-fw',1);
Insert into SYS_RESOURCE (ID,NAME,IDENTIFIER,URL,PARENT_ID,PARENT_IDS,WEIGHT,HAS_CHILDREN,IS_SHOW,ICON,RESOURCE_TYPE_ID) values (25,'代理额度查询api','queryAgentBalance','/api/balance/agentBalance/queryBalance',24,'0/1/23/24',0,0,null,null,4);
Insert into SYS_RESOURCE (ID,NAME,IDENTIFIER,URL,PARENT_ID,PARENT_IDS,WEIGHT,HAS_CHILDREN,IS_SHOW,ICON,RESOURCE_TYPE_ID) values (19,'选择机构','selectOrg','/user/selectOrg',3,'0/1/3',0,0,null,null,3);

Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (25,1,21);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (26,1,22);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (42,1,29);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (41,1,28);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (43,2,28);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (44,2,29);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (45,1,30);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (46,1,31);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (49,1,33);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (48,1,32);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (50,1,34);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (51,1,35);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (52,1,36);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (53,1,37);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (54,2,30);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (55,2,31);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (56,2,32);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (57,2,33);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (58,2,34);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (59,2,35);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (60,2,36);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (61,2,37);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (1,1,2);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (16,1,3);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (3,1,5);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (4,1,6);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (34,1,27);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (9,1,11);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (10,1,12);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (12,1,13);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (13,1,15);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (14,1,16);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (15,1,17);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (2,1,18);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (17,2,2);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (18,2,12);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (19,2,13);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (20,2,15);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (21,2,16);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (22,2,17);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (30,2,23);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (31,2,24);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (32,2,25);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (33,1,26);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (39,2,20);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (24,1,20);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (27,1,23);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (28,1,24);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (29,1,25);
Insert into SYS_ROLE_RESOURCE (ID,ROLE_ID,RESOURCE_ID) values (23,1,19);

--处理处理测试用户数据
delete from SYS_AUTHORITY;
Insert into SYS_AUTHORITY (ID,ORGANIZATION_ID,ROLE_ID,USER_ID) values (1,null,1,1);
Insert into SYS_AUTHORITY (ID,ORGANIZATION_ID,ROLE_ID,USER_ID) values (2,null,1,143176);
Insert into SYS_AUTHORITY (ID,ORGANIZATION_ID,ROLE_ID,USER_ID) values (3,null,1,140385);
Insert into SYS_AUTHORITY (ID,ORGANIZATION_ID,ROLE_ID,USER_ID) values (4,null,1,145046);
Insert into SYS_AUTHORITY (ID,ORGANIZATION_ID,ROLE_ID,USER_ID) values (5,null,1,140384);
delete from sys_user t where t.ID not in (1,143176,140385,145046,140384);

commit;