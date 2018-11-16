--
-- A hint submitted by a user: Oracle DB MUST be created as "shared" and the 
-- job_queue_processes parameter  must be greater than 2
-- However, these settings are pretty much standard after any
-- Oracle install, so most users need not worry about this.
--
-- Many other users (including the primary author of Quartz) have had success
-- runing in dedicated mode, so only consider the above as a hint ;-)
--
drop table qrtz_calendars;
drop table qrtz_fired_triggers;
drop table qrtz_blob_triggers;
drop table qrtz_cron_triggers;
drop table qrtz_simple_triggers;
drop table qrtz_simprop_triggers;
drop table qrtz_triggers;
drop table qrtz_job_details;
drop table qrtz_paused_trigger_grps;
drop table qrtz_locks;
drop table qrtz_scheduler_state;


CREATE TABLE qrtz_job_details
  (
    SCHED_NAME VARCHAR2(120) NOT NULL,
    JOB_NAME  VARCHAR2(200) NOT NULL,
    JOB_GROUP VARCHAR2(200) NOT NULL,
    DESCRIPTION VARCHAR2(250) NULL,
    JOB_CLASS_NAME   VARCHAR2(250) NOT NULL, 
    IS_DURABLE VARCHAR2(1) NOT NULL,
    IS_NONCONCURRENT VARCHAR2(1) NOT NULL,
    IS_UPDATE_DATA VARCHAR2(1) NOT NULL,
    REQUESTS_RECOVERY VARCHAR2(1) NOT NULL,
    JOB_DATA BLOB NULL,
    CONSTRAINT QRTZ_JOB_DETAILS_PK PRIMARY KEY (SCHED_NAME,JOB_NAME,JOB_GROUP)
);
CREATE TABLE qrtz_triggers
  (
    SCHED_NAME VARCHAR2(120) NOT NULL,
    TRIGGER_NAME VARCHAR2(200) NOT NULL,
    TRIGGER_GROUP VARCHAR2(200) NOT NULL,
    JOB_NAME  VARCHAR2(200) NOT NULL, 
    JOB_GROUP VARCHAR2(200) NOT NULL,
    DESCRIPTION VARCHAR2(250) NULL,
    NEXT_FIRE_TIME NUMBER(13) NULL,
    PREV_FIRE_TIME NUMBER(13) NULL,
    PRIORITY NUMBER(13) NULL,
    TRIGGER_STATE VARCHAR2(16) NOT NULL,
    TRIGGER_TYPE VARCHAR2(8) NOT NULL,
    START_TIME NUMBER(13) NOT NULL,
    END_TIME NUMBER(13) NULL,
    CALENDAR_NAME VARCHAR2(200) NULL,
    MISFIRE_INSTR NUMBER(2) NULL,
    JOB_DATA BLOB NULL,
    CONSTRAINT QRTZ_TRIGGERS_PK PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
    CONSTRAINT QRTZ_TRIGGER_TO_JOBS_FK FOREIGN KEY (SCHED_NAME,JOB_NAME,JOB_GROUP) 
      REFERENCES QRTZ_JOB_DETAILS(SCHED_NAME,JOB_NAME,JOB_GROUP) 
);
CREATE TABLE qrtz_simple_triggers
  (
    SCHED_NAME VARCHAR2(120) NOT NULL,
    TRIGGER_NAME VARCHAR2(200) NOT NULL,
    TRIGGER_GROUP VARCHAR2(200) NOT NULL,
    REPEAT_COUNT NUMBER(7) NOT NULL,
    REPEAT_INTERVAL NUMBER(12) NOT NULL,
    TIMES_TRIGGERED NUMBER(10) NOT NULL,
    CONSTRAINT QRTZ_SIMPLE_TRIG_PK PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
    CONSTRAINT QRTZ_SIMPLE_TRIG_TO_TRIG_FK FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP) 
	REFERENCES QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
);
CREATE TABLE qrtz_cron_triggers
  (
    SCHED_NAME VARCHAR2(120) NOT NULL,
    TRIGGER_NAME VARCHAR2(200) NOT NULL,
    TRIGGER_GROUP VARCHAR2(200) NOT NULL,
    CRON_EXPRESSION VARCHAR2(120) NOT NULL,
    TIME_ZONE_ID VARCHAR2(80),
    CONSTRAINT QRTZ_CRON_TRIG_PK PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
    CONSTRAINT QRTZ_CRON_TRIG_TO_TRIG_FK FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP) 
      REFERENCES QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
);
CREATE TABLE qrtz_simprop_triggers
  (          
    SCHED_NAME VARCHAR2(120) NOT NULL,
    TRIGGER_NAME VARCHAR2(200) NOT NULL,
    TRIGGER_GROUP VARCHAR2(200) NOT NULL,
    STR_PROP_1 VARCHAR2(512) NULL,
    STR_PROP_2 VARCHAR2(512) NULL,
    STR_PROP_3 VARCHAR2(512) NULL,
    INT_PROP_1 NUMBER(10) NULL,
    INT_PROP_2 NUMBER(10) NULL,
    LONG_PROP_1 NUMBER(13) NULL,
    LONG_PROP_2 NUMBER(13) NULL,
    DEC_PROP_1 NUMERIC(13,4) NULL,
    DEC_PROP_2 NUMERIC(13,4) NULL,
    BOOL_PROP_1 VARCHAR2(1) NULL,
    BOOL_PROP_2 VARCHAR2(1) NULL,
    CONSTRAINT QRTZ_SIMPROP_TRIG_PK PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
    CONSTRAINT QRTZ_SIMPROP_TRIG_TO_TRIG_FK FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP) 
      REFERENCES QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
);
CREATE TABLE qrtz_blob_triggers
  (
    SCHED_NAME VARCHAR2(120) NOT NULL,
    TRIGGER_NAME VARCHAR2(200) NOT NULL,
    TRIGGER_GROUP VARCHAR2(200) NOT NULL,
    BLOB_DATA BLOB NULL,
    CONSTRAINT QRTZ_BLOB_TRIG_PK PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
    CONSTRAINT QRTZ_BLOB_TRIG_TO_TRIG_FK FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP) 
        REFERENCES QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
);
CREATE TABLE qrtz_calendars
  (
    SCHED_NAME VARCHAR2(120) NOT NULL,
    CALENDAR_NAME  VARCHAR2(200) NOT NULL, 
    CALENDAR BLOB NOT NULL,
    CONSTRAINT QRTZ_CALENDARS_PK PRIMARY KEY (SCHED_NAME,CALENDAR_NAME)
);
CREATE TABLE qrtz_paused_trigger_grps
  (
    SCHED_NAME VARCHAR2(120) NOT NULL,
    TRIGGER_GROUP  VARCHAR2(200) NOT NULL, 
    CONSTRAINT QRTZ_PAUSED_TRIG_GRPS_PK PRIMARY KEY (SCHED_NAME,TRIGGER_GROUP)
);
CREATE TABLE qrtz_fired_triggers 
  (
    SCHED_NAME VARCHAR2(120) NOT NULL,
    ENTRY_ID VARCHAR2(95) NOT NULL,
    TRIGGER_NAME VARCHAR2(200) NOT NULL,
    TRIGGER_GROUP VARCHAR2(200) NOT NULL,
    INSTANCE_NAME VARCHAR2(200) NOT NULL,
    FIRED_TIME NUMBER(13) NOT NULL,
    SCHED_TIME NUMBER(13) NOT NULL,
    PRIORITY NUMBER(13) NOT NULL,
    STATE VARCHAR2(16) NOT NULL,
    JOB_NAME VARCHAR2(200) NULL,
    JOB_GROUP VARCHAR2(200) NULL,
    IS_NONCONCURRENT VARCHAR2(1) NULL,
    REQUESTS_RECOVERY VARCHAR2(1) NULL,
    CONSTRAINT QRTZ_FIRED_TRIGGER_PK PRIMARY KEY (SCHED_NAME,ENTRY_ID)
);
CREATE TABLE qrtz_scheduler_state 
  (
    SCHED_NAME VARCHAR2(120) NOT NULL,
    INSTANCE_NAME VARCHAR2(200) NOT NULL,
    LAST_CHECKIN_TIME NUMBER(13) NOT NULL,
    CHECKIN_INTERVAL NUMBER(13) NOT NULL,
    CONSTRAINT QRTZ_SCHEDULER_STATE_PK PRIMARY KEY (SCHED_NAME,INSTANCE_NAME)
);
CREATE TABLE qrtz_locks
  (
    SCHED_NAME VARCHAR2(120) NOT NULL,
    LOCK_NAME  VARCHAR2(40) NOT NULL, 
    CONSTRAINT QRTZ_LOCKS_PK PRIMARY KEY (SCHED_NAME,LOCK_NAME)
);

create index idx_qrtz_j_req_recovery on qrtz_job_details(SCHED_NAME,REQUESTS_RECOVERY);
create index idx_qrtz_j_grp on qrtz_job_details(SCHED_NAME,JOB_GROUP);

create index idx_qrtz_t_j on qrtz_triggers(SCHED_NAME,JOB_NAME,JOB_GROUP);
create index idx_qrtz_t_jg on qrtz_triggers(SCHED_NAME,JOB_GROUP);
create index idx_qrtz_t_c on qrtz_triggers(SCHED_NAME,CALENDAR_NAME);
create index idx_qrtz_t_g on qrtz_triggers(SCHED_NAME,TRIGGER_GROUP);
create index idx_qrtz_t_state on qrtz_triggers(SCHED_NAME,TRIGGER_STATE);
create index idx_qrtz_t_n_state on qrtz_triggers(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP,TRIGGER_STATE);
create index idx_qrtz_t_n_g_state on qrtz_triggers(SCHED_NAME,TRIGGER_GROUP,TRIGGER_STATE);
create index idx_qrtz_t_next_fire_time on qrtz_triggers(SCHED_NAME,NEXT_FIRE_TIME);
create index idx_qrtz_t_nft_st on qrtz_triggers(SCHED_NAME,TRIGGER_STATE,NEXT_FIRE_TIME);
create index idx_qrtz_t_nft_misfire on qrtz_triggers(SCHED_NAME,MISFIRE_INSTR,NEXT_FIRE_TIME);
create index idx_qrtz_t_nft_st_misfire on qrtz_triggers(SCHED_NAME,MISFIRE_INSTR,NEXT_FIRE_TIME,TRIGGER_STATE);
create index idx_qrtz_t_nft_st_misfire_grp on qrtz_triggers(SCHED_NAME,MISFIRE_INSTR,NEXT_FIRE_TIME,TRIGGER_GROUP,TRIGGER_STATE);

create index idx_qrtz_ft_trig_inst_name on qrtz_fired_triggers(SCHED_NAME,INSTANCE_NAME);
create index idx_qrtz_ft_inst_job_req_rcvry on qrtz_fired_triggers(SCHED_NAME,INSTANCE_NAME,REQUESTS_RECOVERY);
create index idx_qrtz_ft_j_g on qrtz_fired_triggers(SCHED_NAME,JOB_NAME,JOB_GROUP);
create index idx_qrtz_ft_jg on qrtz_fired_triggers(SCHED_NAME,JOB_GROUP);
create index idx_qrtz_ft_t_g on qrtz_fired_triggers(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP);
create index idx_qrtz_ft_tg on qrtz_fired_triggers(SCHED_NAME,TRIGGER_GROUP);

--------------------------------------------------------
--  文件已创建 - 星期四-五月-25-2017   
--------------------------------------------------------
DROP TABLE "SYS_USER_ORGANIZATION";
DROP TABLE "SYS_AUTHORITY";
DROP TABLE "SYS_USER";
DROP TABLE "SYS_CREDENTIAL_TYPE";
DROP TABLE "SYS_CITY";
DROP TABLE "SYS_GAME";
DROP TABLE "SYS_PROVINCE";
DROP TABLE "SYS_ORGANIZATION";
DROP TABLE "SYS_ROLE_RESOURCE";
DROP TABLE "SYS_RESOURCE";
DROP TABLE "SYS_RESOURCE_TYPE";
DROP TABLE "SYS_ROLE";
DROP TABLE "SYS_USER_STATE";
DROP TABLE "SYS_CHANNEL_BRAND";
DROP TABLE "SYS_PERIOD_SALES_REPORT_TEMP";
DROP SEQUENCE "AW_SEQUENCE";
--------------------------------------------------------
--  DDL for Table SYS_AUTHORITY
--------------------------------------------------------

  CREATE TABLE "SYS_AUTHORITY" 
   (	"ID" NUMBER(19,0), 
	"ORGANIZATION_ID" NUMBER(19,0), 
	"ROLE_ID" NUMBER(19,0), 
	"USER_ID" NUMBER(19,0)
   ) ;
--------------------------------------------------------
--  DDL for Table SYS_CHANNEL_BRAND
--------------------------------------------------------

  CREATE TABLE "SYS_CHANNEL_BRAND" 
   (	"ID" NUMBER, 
	"CHANNEL_BRAND_NO" VARCHAR2(20), 
	"CHANNEL_BRAND_NAME" VARCHAR2(50)
   ) ;
--------------------------------------------------------
--  DDL for Table SYS_CITY
--------------------------------------------------------

  CREATE TABLE "SYS_CITY" 
   (	"ID" NUMBER(19,0), 
	"PROVINCE_ID" NUMBER(19,0), 
	"CITY_NO" VARCHAR2(6 CHAR), 
	"CITY_NAME" VARCHAR2(30 CHAR), 
	"CITY_SHORT_NAME" VARCHAR2(30 CHAR), 
	"CITY_FULL_NAME" VARCHAR2(60 CHAR), 
	"CAPITAL_FLAG" NUMBER(10,0), 
	"SORT_ID" NUMBER(10,0), 
	"STATUS_CHANGE_TIME" TIMESTAMP (6), 
	"STATUS_CODE" NUMBER(10,0), 
	"DEL_FLAG" NUMBER(10,0), 
	"DEL_TIME" TIMESTAMP (6)
   ) ;
--------------------------------------------------------
--  DDL for Table SYS_CREDENTIAL_TYPE
--------------------------------------------------------

  CREATE TABLE "SYS_CREDENTIAL_TYPE" 
   (	"ID" NUMBER(19,0), 
	"CODE" VARCHAR2(10 CHAR), 
	"NAME" VARCHAR2(50 CHAR)
   ) ;
--------------------------------------------------------
--  DDL for Table SYS_GAME
--------------------------------------------------------

  CREATE TABLE "SYS_GAME" 
   (	"GAME_NO" VARCHAR2(8), 
	"GAME_NAME" VARCHAR2(45), 
	"GAME_SHORT_NAME" VARCHAR2(45), 
	"ID" NUMBER(19,0), 
	"PROVINCE_ID" NUMBER
   ) ;
--------------------------------------------------------
--  DDL for Table SYS_ORGANIZATION
--------------------------------------------------------

  CREATE TABLE "SYS_ORGANIZATION" 
   (	"ID" NUMBER(19,0), 
	"NAME" VARCHAR2(100 CHAR), 
	"PARENT_ID" NUMBER(19,0), 
	"PARENT_IDS" VARCHAR2(200 CHAR), 
	"IS_SHOW" NUMBER(3,0), 
	"HAS_CHILDREN" NUMBER(3,0), 
	"WEIGHT" NUMBER(10,0)
   ) ;
--------------------------------------------------------
--  DDL for Table SYS_PERIOD_SALES_REPORT_TEMP
--------------------------------------------------------

  CREATE TABLE "SYS_PERIOD_SALES_REPORT_TEMP" 
   (	"ID" NUMBER, 
	"REPORT_DATE" DATE, 
	"TERMINAL_ID" NUMBER, 
	"USER_ID" NUMBER, 
	"SHOP_ID" NUMBER, 
	"AGENT_ID" NUMBER, 
	"CITY_ID" NUMBER, 
	"PROVINCE_ID" NUMBER, 
	"TERMINAL_NO" VARCHAR2(20), 
	"AGENT_NO" VARCHAR2(20), 
	"CITY_NO" VARCHAR2(20), 
	"PROVINCE_NO" VARCHAR2(20)
   ) ;
--------------------------------------------------------
--  DDL for Table SYS_PROVINCE
--------------------------------------------------------

  CREATE TABLE "SYS_PROVINCE" 
   (	"ID" NUMBER(19,0), 
	"PROVINCE_NO" VARCHAR2(6 CHAR), 
	"PROVINCE_NAME" VARCHAR2(30 CHAR), 
	"PROVINCE_FULL_NAME" VARCHAR2(60 CHAR), 
	"PROVINCE_SHORT_NAME" VARCHAR2(30 CHAR), 
	"ALPHABETIC_CODE" VARCHAR2(30 CHAR), 
	"COUNTRY_ID" NUMBER(10,0), 
	"DEL_FLAG" NUMBER(10,0), 
	"DEL_TIME" TIMESTAMP (6), 
	"SORT_ID" NUMBER(10,0), 
	"STATUS_CHANGE_TIME" TIMESTAMP (6), 
	"STATUS_CODE" VARCHAR2(45 CHAR)
   ) ;
--------------------------------------------------------
--  DDL for Table SYS_RESOURCE
--------------------------------------------------------

  CREATE TABLE "SYS_RESOURCE" 
   (	"ID" NUMBER(19,0), 
	"NAME" VARCHAR2(100 CHAR), 
	"IDENTIFIER" VARCHAR2(100 CHAR), 
	"URL" VARCHAR2(200 CHAR), 
	"PARENT_ID" NUMBER(19,0), 
	"PARENT_IDS" VARCHAR2(200 CHAR), 
	"WEIGHT" NUMBER(10,0), 
	"HAS_CHILDREN" NUMBER(3,0), 
	"IS_SHOW" NUMBER(3,0), 
	"ICON" VARCHAR2(200 CHAR), 
	"RESOURCE_TYPE_ID" NUMBER(19,0)
   ) ;
--------------------------------------------------------
--  DDL for Table SYS_RESOURCE_TYPE
--------------------------------------------------------

  CREATE TABLE "SYS_RESOURCE_TYPE" 
   (	"ID" NUMBER(19,0), 
	"CODE" VARCHAR2(10 CHAR), 
	"NAME" VARCHAR2(20 CHAR)
   ) ;
--------------------------------------------------------
--  DDL for Table SYS_ROLE
--------------------------------------------------------

  CREATE TABLE "SYS_ROLE" 
   (	"ID" NUMBER(19,0), 
	"NAME" VARCHAR2(100 CHAR), 
	"DESCRIPTION" VARCHAR2(200 CHAR), 
	"IS_SHOW" NUMBER(3,0)
   ) ;
--------------------------------------------------------
--  DDL for Table SYS_ROLE_RESOURCE
--------------------------------------------------------

  CREATE TABLE "SYS_ROLE_RESOURCE" 
   (	"ID" NUMBER(19,0), 
	"ROLE_ID" NUMBER(19,0), 
	"RESOURCE_ID" NUMBER(19,0)
   ) ;
--------------------------------------------------------
--  DDL for Table SYS_USER
--------------------------------------------------------

  CREATE TABLE "SYS_USER" 
   (	"ID" NUMBER(19,0), 
	"USER_NAME" VARCHAR2(100 CHAR), 
	"DESCRIPTION" VARCHAR2(200 CHAR), 
	"MOBILE_PHONE_NUMBER" VARCHAR2(20 CHAR), 
	"PASSWORD" VARCHAR2(100 CHAR), 
	"CREATE_DATE" TIMESTAMP (6), 
	"USER_FULL_NAME" VARCHAR2(100 CHAR), 
	"CREDENTIAL_TYPE_ID" NUMBER(19,0), 
	"CITY_ID" NUMBER(19,0), 
	"UMP_USER_ID" NUMBER(19,0), 
	"EMAIL" VARCHAR2(100 CHAR), 
	"CREDENTIAL_NO" VARCHAR2(50 CHAR), 
	"PROVINCE_ID" NUMBER(19,0), 
	"USER_STATE_ID" NUMBER(19,0), 
	"LAST_OPER_DATE" TIMESTAMP (6), 
	"CHANNEL_BRAND_ID" NUMBER
   ) ;
--------------------------------------------------------
--  DDL for Table SYS_USER_ORGANIZATION
--------------------------------------------------------

  CREATE TABLE "SYS_USER_ORGANIZATION" 
   (	"ID" NUMBER(19,0), 
	"ORGANIZATION_ID" NUMBER(19,0), 
	"USER_ID" NUMBER(19,0)
   ) ;
--------------------------------------------------------
--  DDL for Table SYS_USER_STATE
--------------------------------------------------------

  CREATE TABLE "SYS_USER_STATE" 
   (	"ID" NUMBER(19,0), 
	"CODE" VARCHAR2(20 CHAR), 
	"NAME" VARCHAR2(20 CHAR)
   ) ;
--------------------------------------------------------
--  DDL for Sequence AW_SEQUENCE
--------------------------------------------------------

   CREATE SEQUENCE  "AW_SEQUENCE"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 10000 CACHE 20 NOORDER  NOCYCLE  NOKEEP  GLOBAL ;
--------------------------------------------------------
--  DDL for Index SYS_AUTHORITY_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_AUTHORITY_PK" ON "SYS_AUTHORITY" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index SYS_CHANNEL_BRAND_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_CHANNEL_BRAND_PK" ON "SYS_CHANNEL_BRAND" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index SYS_CITY_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_CITY_PK" ON "SYS_CITY" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index SYS_CREDENTIAL_TYPE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_CREDENTIAL_TYPE_PK" ON "SYS_CREDENTIAL_TYPE" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index SYS_GAME_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_GAME_PK" ON "SYS_GAME" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index SYS_ORGANIZATION_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_ORGANIZATION_PK" ON "SYS_ORGANIZATION" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index S_P_S_R_T_INDEX_PROVINCE_NO
--------------------------------------------------------

  CREATE INDEX "S_P_S_R_T_INDEX_PROVINCE_NO" ON "SYS_PERIOD_SALES_REPORT_TEMP" ("PROVINCE_NO") 
  ;
--------------------------------------------------------
--  DDL for Index S_P_S_R_T_INDEX_AGENT_NO
--------------------------------------------------------

  CREATE INDEX "S_P_S_R_T_INDEX_AGENT_NO" ON "SYS_PERIOD_SALES_REPORT_TEMP" ("AGENT_NO") 
  ;
--------------------------------------------------------
--  DDL for Index S_P_S_R_T_INDEX_CITY_NO
--------------------------------------------------------

  CREATE INDEX "S_P_S_R_T_INDEX_CITY_NO" ON "SYS_PERIOD_SALES_REPORT_TEMP" ("CITY_NO") 
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
--  DDL for Index S_P_S_R_T_INDEX_TERMINAL_ID
--------------------------------------------------------

  CREATE INDEX "S_P_S_R_T_INDEX_TERMINAL_ID" ON "SYS_PERIOD_SALES_REPORT_TEMP" ("TERMINAL_ID") 
  ;
--------------------------------------------------------
--  DDL for Index SYS_PROVINCE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_PROVINCE_PK" ON "SYS_PROVINCE" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index SYS_RESOURCE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_RESOURCE_PK" ON "SYS_RESOURCE" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index SYS_RESOURCE_TYPE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_RESOURCE_TYPE_PK" ON "SYS_RESOURCE_TYPE" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index SYS_ROLE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_ROLE_PK" ON "SYS_ROLE" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index SYS_ROLE_RESOURCE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_ROLE_RESOURCE_PK" ON "SYS_ROLE_RESOURCE" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index SYS_USER_ORGANIZATION_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_USER_ORGANIZATION_PK" ON "SYS_USER_ORGANIZATION" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index SYS_USER_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_USER_PK" ON "SYS_USER" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index SYS_USER_STATE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_USER_STATE_PK" ON "SYS_USER_STATE" ("ID") 
  ;
--------------------------------------------------------
--  Constraints for Table SYS_AUTHORITY
--------------------------------------------------------

  ALTER TABLE "SYS_AUTHORITY" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "SYS_AUTHORITY" ADD CONSTRAINT "SYS_AUTHORITY_PK" PRIMARY KEY ("ID")
  USING INDEX  ENABLE;
--------------------------------------------------------
--  Constraints for Table SYS_CHANNEL_BRAND
--------------------------------------------------------

  ALTER TABLE "SYS_CHANNEL_BRAND" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "SYS_CHANNEL_BRAND" ADD CONSTRAINT "SYS_CHANNEL_BRAND_PK" PRIMARY KEY ("ID")
  USING INDEX  ENABLE;
--------------------------------------------------------
--  Constraints for Table SYS_CITY
--------------------------------------------------------

  ALTER TABLE "SYS_CITY" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "SYS_CITY" ADD CONSTRAINT "SYS_CITY_PK" PRIMARY KEY ("ID")
  USING INDEX  ENABLE;
--------------------------------------------------------
--  Constraints for Table SYS_CREDENTIAL_TYPE
--------------------------------------------------------

  ALTER TABLE "SYS_CREDENTIAL_TYPE" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "SYS_CREDENTIAL_TYPE" ADD CONSTRAINT "SYS_CREDENTIAL_TYPE_PK" PRIMARY KEY ("ID")
  USING INDEX  ENABLE;
--------------------------------------------------------
--  Constraints for Table SYS_GAME
--------------------------------------------------------

  ALTER TABLE "SYS_GAME" MODIFY ("GAME_NO" NOT NULL ENABLE);
  ALTER TABLE "SYS_GAME" MODIFY ("GAME_NAME" NOT NULL ENABLE);
  ALTER TABLE "SYS_GAME" MODIFY ("GAME_SHORT_NAME" NOT NULL ENABLE);
  ALTER TABLE "SYS_GAME" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "SYS_GAME" ADD CONSTRAINT "SYS_GAME_PK" PRIMARY KEY ("ID")
  USING INDEX  ENABLE;
  ALTER TABLE "SYS_GAME" MODIFY ("PROVINCE_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table SYS_ORGANIZATION
--------------------------------------------------------

  ALTER TABLE "SYS_ORGANIZATION" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "SYS_ORGANIZATION" ADD CONSTRAINT "SYS_ORGANIZATION_PK" PRIMARY KEY ("ID")
  USING INDEX  ENABLE;
--------------------------------------------------------
--  Constraints for Table SYS_PERIOD_SALES_REPORT_TEMP
--------------------------------------------------------

  ALTER TABLE "SYS_PERIOD_SALES_REPORT_TEMP" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "SYS_PERIOD_SALES_REPORT_TEMP" ADD CONSTRAINT "SYS_PERIOD_SALES_REPORT_PK" PRIMARY KEY ("ID")
  USING INDEX  ENABLE;
--------------------------------------------------------
--  Constraints for Table SYS_PROVINCE
--------------------------------------------------------

  ALTER TABLE "SYS_PROVINCE" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "SYS_PROVINCE" ADD CONSTRAINT "SYS_PROVINCE_PK" PRIMARY KEY ("ID")
  USING INDEX  ENABLE;
--------------------------------------------------------
--  Constraints for Table SYS_RESOURCE
--------------------------------------------------------

  ALTER TABLE "SYS_RESOURCE" MODIFY ("IDENTIFIER" NOT NULL ENABLE);
  ALTER TABLE "SYS_RESOURCE" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "SYS_RESOURCE" ADD CONSTRAINT "SYS_RESOURCE_PK" PRIMARY KEY ("ID")
  USING INDEX  ENABLE;
--------------------------------------------------------
--  Constraints for Table SYS_RESOURCE_TYPE
--------------------------------------------------------

  ALTER TABLE "SYS_RESOURCE_TYPE" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "SYS_RESOURCE_TYPE" ADD CONSTRAINT "SYS_RESOURCE_TYPE_PK" PRIMARY KEY ("ID")
  USING INDEX  ENABLE;
--------------------------------------------------------
--  Constraints for Table SYS_ROLE
--------------------------------------------------------

  ALTER TABLE "SYS_ROLE" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "SYS_ROLE" ADD CONSTRAINT "SYS_ROLE_PK" PRIMARY KEY ("ID")
  USING INDEX  ENABLE;
--------------------------------------------------------
--  Constraints for Table SYS_ROLE_RESOURCE
--------------------------------------------------------

  ALTER TABLE "SYS_ROLE_RESOURCE" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "SYS_ROLE_RESOURCE" ADD CONSTRAINT "SYS_ROLE_RESOURCE_PK" PRIMARY KEY ("ID")
  USING INDEX  ENABLE;
--------------------------------------------------------
--  Constraints for Table SYS_USER
--------------------------------------------------------

  ALTER TABLE "SYS_USER" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "SYS_USER" ADD CONSTRAINT "SYS_USER_PK" PRIMARY KEY ("ID")
  USING INDEX  ENABLE;
--------------------------------------------------------
--  Constraints for Table SYS_USER_ORGANIZATION
--------------------------------------------------------

  ALTER TABLE "SYS_USER_ORGANIZATION" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "SYS_USER_ORGANIZATION" ADD CONSTRAINT "SYS_USER_ORGANIZATION_PK" PRIMARY KEY ("ID")
  USING INDEX  ENABLE;
--------------------------------------------------------
--  Constraints for Table SYS_USER_STATE
--------------------------------------------------------

  ALTER TABLE "SYS_USER_STATE" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "SYS_USER_STATE" ADD CONSTRAINT "SYS_USER_STATE_PK" PRIMARY KEY ("ID")
  USING INDEX  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table SYS_AUTHORITY
--------------------------------------------------------

  ALTER TABLE "SYS_AUTHORITY" ADD CONSTRAINT "AUTHORITY_FK_USER" FOREIGN KEY ("USER_ID")
	  REFERENCES "SYS_USER" ("ID") ENABLE;
  ALTER TABLE "SYS_AUTHORITY" ADD CONSTRAINT "AUTHORITY_FK_ROLE" FOREIGN KEY ("ROLE_ID")
	  REFERENCES "SYS_ROLE" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table SYS_CITY
--------------------------------------------------------

  ALTER TABLE "SYS_CITY" ADD CONSTRAINT "CITY_FK_PROVINCE" FOREIGN KEY ("PROVINCE_ID")
	  REFERENCES "SYS_PROVINCE" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table SYS_GAME
--------------------------------------------------------

  ALTER TABLE "SYS_GAME" ADD CONSTRAINT "GAME_FK_PROVINCE" FOREIGN KEY ("PROVINCE_ID")
	  REFERENCES "SYS_PROVINCE" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table SYS_RESOURCE
--------------------------------------------------------

  ALTER TABLE "SYS_RESOURCE" ADD CONSTRAINT "RESOURCE_FK_RESOURCE_TYPE" FOREIGN KEY ("RESOURCE_TYPE_ID")
	  REFERENCES "SYS_RESOURCE_TYPE" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table SYS_ROLE_RESOURCE
--------------------------------------------------------

  ALTER TABLE "SYS_ROLE_RESOURCE" ADD CONSTRAINT "ROLE_RESOURCE_FK_ROLE" FOREIGN KEY ("ROLE_ID")
	  REFERENCES "SYS_ROLE" ("ID") ENABLE;
  ALTER TABLE "SYS_ROLE_RESOURCE" ADD CONSTRAINT "ROLE_RESOURCE_FK_RESOURCE" FOREIGN KEY ("RESOURCE_ID")
	  REFERENCES "SYS_RESOURCE" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table SYS_USER
--------------------------------------------------------

  ALTER TABLE "SYS_USER" ADD CONSTRAINT "USER_FK_CREDENTIAL_TYPE" FOREIGN KEY ("CREDENTIAL_TYPE_ID")
	  REFERENCES "SYS_CREDENTIAL_TYPE" ("ID") ENABLE;
  ALTER TABLE "SYS_USER" ADD CONSTRAINT "USER_FK_CITY" FOREIGN KEY ("CREDENTIAL_TYPE_ID")
	  REFERENCES "SYS_CITY" ("ID") ENABLE;
  ALTER TABLE "SYS_USER" ADD CONSTRAINT "USER_FK_PROVINCE" FOREIGN KEY ("PROVINCE_ID")
	  REFERENCES "SYS_PROVINCE" ("ID") ENABLE;
  ALTER TABLE "SYS_USER" ADD CONSTRAINT "USER_FK_USER_STATE" FOREIGN KEY ("USER_STATE_ID")
	  REFERENCES "SYS_USER_STATE" ("ID") ENABLE;
  ALTER TABLE "SYS_USER" ADD CONSTRAINT "USER_FK_CHANNNEL_BRAND" FOREIGN KEY ("CHANNEL_BRAND_ID")
	  REFERENCES "SYS_CHANNEL_BRAND" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table SYS_USER_ORGANIZATION
--------------------------------------------------------

  ALTER TABLE "SYS_USER_ORGANIZATION" ADD CONSTRAINT "USER_ORG_FK_USER" FOREIGN KEY ("USER_ID")
	  REFERENCES "SYS_USER" ("ID") ENABLE;
  ALTER TABLE "SYS_USER_ORGANIZATION" ADD CONSTRAINT "USER_ORG_FK_ORG" FOREIGN KEY ("ORGANIZATION_ID")
	  REFERENCES "SYS_ORGANIZATION" ("ID") ENABLE;