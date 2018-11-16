drop table SYS_WS_MESSAGE;

ALTER TABLE SYS_CHANNEL_BRAND DROP CONSTRAINT S_C_B_FK_S_A_S;

alter table SYS_CHANNEL_BRAND drop (STATE_ID,CREATE_DATE,LAST_OPER_DATE);

drop table SYS_AGENT_STATE;
--  DDL for Table SYS_WS_MESSAGE
--------------------------------------------------------

  CREATE TABLE "SYS_WS_MESSAGE" ("ID" NUMBER, "SERIAL_NUMBER" VARCHAR2(50), "SOURCE" VARCHAR2(20), "REQUEST_TIMESTAMP" TIMESTAMP (6), "REQUEST_MESSAGE" VARCHAR2(2000), "RESPONSE_MESSAGE" VARCHAR2(2000), "RESPONSE_TIMESTAMP" TIMESTAMP (6)) ;
--------------------------------------------------------
--  DDL for Index SYS_WS_MESSAGE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_WS_MESSAGE_PK" ON "SYS_WS_MESSAGE" ("ID") ;
--------------------------------------------------------
--  Constraints for Table SYS_WS_MESSAGE
--------------------------------------------------------

  ALTER TABLE "SYS_WS_MESSAGE" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "SYS_WS_MESSAGE" ADD CONSTRAINT "SYS_WS_MESSAGE_PK" PRIMARY KEY ("ID") USING INDEX  ENABLE;
  
  --以下为删除超级管理员的新增、修改、删除、启用、停用功能
  delete from Sys_Role_Resource t where T.Role_Id = 1 and T.Resource_Id in (4,7,8,9,10);
  commit;
  
  --以下为同步B_AGENT增加的表结构
  --------------------------------------------------------

  CREATE TABLE "SYS_AGENT_STATE" ("ID" NUMBER, "CODE" VARCHAR2(20), "NAME" VARCHAR2(20)) ;
--------------------------------------------------------
--  DDL for Index SYS_AGENT_STATE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_AGENT_STATE_PK" ON "SYS_AGENT_STATE" ("ID") ;
--------------------------------------------------------
--  Constraints for Table SYS_AGENT_STATE
--------------------------------------------------------

  ALTER TABLE "SYS_AGENT_STATE" ADD CONSTRAINT "SYS_AGENT_STATE_PK" PRIMARY KEY ("ID") USING INDEX  ENABLE;
  ALTER TABLE "SYS_AGENT_STATE" MODIFY ("ID" NOT NULL ENABLE);

  ALTER TABLE SYS_CHANNEL_BRAND 
ADD (STATE_ID NUMBER );

ALTER TABLE SYS_CHANNEL_BRAND 
ADD (CREATE_DATE TIMESTAMP );

ALTER TABLE SYS_CHANNEL_BRAND 
ADD (LAST_OPER_DATE TIMESTAMP );

ALTER TABLE SYS_CHANNEL_BRAND
ADD CONSTRAINT S_C_B_FK_S_A_S FOREIGN KEY
(
  STATE_ID 
)
REFERENCES SYS_AGENT_STATE
(
  ID 
)
ENABLE;

delete from SYS_AGENT_STATE;
Insert into SYS_AGENT_STATE (ID,CODE,NAME) values (1,'10','启用');
Insert into SYS_AGENT_STATE (ID,CODE,NAME) values (2,'20','停用');
Insert into SYS_AGENT_STATE (ID,CODE,NAME) values (3,'40','删除');
commit;

ALTER TABLE SYS_USER 
DROP CONSTRAINT USER_FK_CITY;

ALTER TABLE SYS_USER
ADD CONSTRAINT USER_FK_CITY FOREIGN KEY
(
  CITY_ID 
)
REFERENCES SYS_CITY
(
  ID 
)
ENABLE;
