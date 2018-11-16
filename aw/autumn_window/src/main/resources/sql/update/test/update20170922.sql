--4、创建新增golden gate同步表结构
drop table B_ACCOUNT_INFO;
drop table BC_ACCOUNT_BALANCE;
drop table TC_BANK_DEAL;
drop table RC_BALANCE_DAILY_FINANCE;
drop table TC_BANK_DEAL_AGENT;

-- Create table
create table TC_BANK_DEAL
(
  province_center_id   NUMBER not null,
  record_id            NUMBER not null,
  maintain_flag        NUMBER,
  record_no            VARCHAR2(100),
  bank_no              VARCHAR2(200) not null,
  account_id           NUMBER not null,
  account_no           VARCHAR2(60),
  deal_type_code       NUMBER,
  deal_amount          NUMBER,
  deal_time            DATE not null,
  bank_counter_no      VARCHAR2(100),
  log_time             DATE,
  succeed_flag         NUMBER not null,
  descs                VARCHAR2(4000),
  cy_account_type_code NUMBER,
  old_record_no        NUMBER,
  account_date         DATE,
  sub_deal_type_code   NUMBER
)
partition by range (DEAL_TIME)
subpartition by list (PROVINCE_CENTER_ID)
(
  partition P201612 values less than (TO_DATE(' 2017-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace TS_R
    pctfree 10
    initrans 1
    maxtrans 255
  (
    subpartition P201612_P11 values (11) tablespace TS_R,
    subpartition P201612_P12 values (12) tablespace TS_R,
    subpartition P201612_P13 values (13) tablespace TS_R,
    subpartition P201612_P14 values (14) tablespace TS_R,
    subpartition P201612_P15 values (15) tablespace TS_R,
    subpartition P201612_P21 values (21) tablespace TS_R,
    subpartition P201612_P22 values (22) tablespace TS_R,
    subpartition P201612_P23 values (23) tablespace TS_R,
    subpartition P201612_P31 values (31) tablespace TS_R,
    subpartition P201612_P32 values (32) tablespace TS_R,
    subpartition P201612_P33 values (33) tablespace TS_R,
    subpartition P201612_P34 values (34) tablespace TS_R,
    subpartition P201612_P35 values (35) tablespace TS_R,
    subpartition P201612_P36 values (36) tablespace TS_R,
    subpartition P201612_P37 values (37) tablespace TS_R,
    subpartition P201612_P41 values (41) tablespace TS_R,
    subpartition P201612_P42 values (42) tablespace TS_R,
    subpartition P201612_P43 values (43) tablespace TS_R,
    subpartition P201612_P44 values (44) tablespace TS_R,
    subpartition P201612_P45 values (45) tablespace TS_R,
    subpartition P201612_P46 values (46) tablespace TS_R,
    subpartition P201612_P50 values (50) tablespace TS_R,
    subpartition P201612_P51 values (51) tablespace TS_R,
    subpartition P201612_P52 values (52) tablespace TS_R,
    subpartition P201612_P53 values (53) tablespace TS_R,
    subpartition P201612_P54 values (54) tablespace TS_R,
    subpartition P201612_P61 values (61) tablespace TS_R,
    subpartition P201612_P62 values (62) tablespace TS_R,
    subpartition P201612_P63 values (63) tablespace TS_R,
    subpartition P201612_P64 values (64) tablespace TS_R,
    subpartition P201612_P65 values (65) tablespace TS_R
  ),
  partition PDEFAULT values less than (MAXVALUE)
    tablespace TS_R
    pctfree 10
    initrans 1
    maxtrans 255
  (
    subpartition PDEFAULT_P11 values (11) tablespace TS_R,
    subpartition PDEFAULT_P12 values (12) tablespace TS_R,
    subpartition PDEFAULT_P13 values (13) tablespace TS_R,
    subpartition PDEFAULT_P14 values (14) tablespace TS_R,
    subpartition PDEFAULT_P15 values (15) tablespace TS_R,
    subpartition PDEFAULT_P21 values (21) tablespace TS_R,
    subpartition PDEFAULT_P22 values (22) tablespace TS_R,
    subpartition PDEFAULT_P23 values (23) tablespace TS_R,
    subpartition PDEFAULT_P31 values (31) tablespace TS_R,
    subpartition PDEFAULT_P32 values (32) tablespace TS_R,
    subpartition PDEFAULT_P33 values (33) tablespace TS_R,
    subpartition PDEFAULT_P34 values (34) tablespace TS_R,
    subpartition PDEFAULT_P35 values (35) tablespace TS_R,
    subpartition PDEFAULT_P36 values (36) tablespace TS_R,
    subpartition PDEFAULT_P37 values (37) tablespace TS_R,
    subpartition PDEFAULT_P41 values (41) tablespace TS_R,
    subpartition PDEFAULT_P42 values (42) tablespace TS_R,
    subpartition PDEFAULT_P43 values (43) tablespace TS_R,
    subpartition PDEFAULT_P44 values (44) tablespace TS_R,
    subpartition PDEFAULT_P45 values (45) tablespace TS_R,
    subpartition PDEFAULT_P46 values (46) tablespace TS_R,
   subpartition PDEFAULT_P50 values (50) tablespace TS_R,
    subpartition PDEFAULT_P51 values (51) tablespace TS_R,
    subpartition PDEFAULT_P52 values (52) tablespace TS_R,
    subpartition PDEFAULT_P53 values (53) tablespace TS_R,
    subpartition PDEFAULT_P54 values (54) tablespace TS_R,
    subpartition PDEFAULT_P61 values (61) tablespace TS_R,
    subpartition PDEFAULT_P62 values (62) tablespace TS_R,
    subpartition PDEFAULT_P63 values (63) tablespace TS_R,
    subpartition PDEFAULT_P64 values (64) tablespace TS_R,
    subpartition PDEFAULT_P65 values (65) tablespace TS_R
  )
);
-- Add comments to the table 
comment on table TC_BANK_DEAL
  is '彩银系统流水VER=32.1#35.1#44.2#802.4#1069.5#';
-- Add comments to the columns 
comment on column TC_BANK_DEAL.province_center_id
  is '省中心ID';
comment on column TC_BANK_DEAL.record_id
  is '流水号';
comment on column TC_BANK_DEAL.maintain_flag
  is '系统维护标志 0：非系统维护产生 1：系统维护产生';
comment on column TC_BANK_DEAL.record_no
  is '银行流水编号';
comment on column TC_BANK_DEAL.bank_no
  is '银行编号';
comment on column TC_BANK_DEAL.account_id
  is '帐户ID';
comment on column TC_BANK_DEAL.account_no
  is '帐户编码';
comment on column TC_BANK_DEAL.deal_type_code
  is '彩银系统交易类型（已配值域）';
comment on column TC_BANK_DEAL.deal_amount
  is '彩银系统交易金额';
comment on column TC_BANK_DEAL.deal_time
  is '处理时间';
comment on column TC_BANK_DEAL.bank_counter_no
  is '银行柜台号';
comment on column TC_BANK_DEAL.log_time
  is '记录时间';
comment on column TC_BANK_DEAL.succeed_flag
  is '成功标志 0：不成功 1：成功';
comment on column TC_BANK_DEAL.descs
  is '描述';
comment on column TC_BANK_DEAL.cy_account_type_code
  is '旧彩银账户类型';
comment on column TC_BANK_DEAL.old_record_no
  is '移动支付手续费';
comment on column TC_BANK_DEAL.account_date
  is '帐户日期';
comment on column TC_BANK_DEAL.sub_deal_type_code
  is '彩银系统子交易类型（已配值域）';
-- Create/Recreate primary, unique and foreign key constraints 
alter table TC_BANK_DEAL
  add constraint PK_TC_BANK_DEAL primary key (PROVINCE_CENTER_ID, DEAL_TIME, RECORD_ID);
-- Create/Recreate indexes 
create index IND_TC_BANK_DEAL_DEAL on TC_BANK_DEAL (PROVINCE_CENTER_ID, DEAL_TIME, DEAL_TYPE_CODE);
create index IND_TC_BANK_DEAL_LOG on TC_BANK_DEAL (PROVINCE_CENTER_ID, LOG_TIME, DEAL_TYPE_CODE);

-- Create table
create table RC_BALANCE_DAILY_FINANCE
(
  report_date         DATE not null,
  province_center_id  NUMBER not null,
  account_id          NUMBER not null,
  prior_amount        NUMBER not null,
  load_time           DATE default SYSDATE not null,
  post_amount         NUMBER,
  deal_amount         NUMBER,
  log_deal_amount     NUMBER,
  sale_amount         NUMBER,
  cancel_amount       NUMBER,
  paid_amount         NUMBER,
  refund_amount       NUMBER,
  ctb_amount          NUMBER,
  settled_deal_amount NUMBER,
  void_amount         NUMBER,
  dealtype_10_amount  NUMBER,
  dealtype_20_amount  NUMBER,
  dealtype_30_amount  NUMBER,
  prior_credit_amount NUMBER,
  post_credit_amount  NUMBER,
  CANCEL_AMOUNT_SHOULDBE NUMBER, 
  CANCEL_AMOUNT_SETTLED NUMBER
)
partition by range (REPORT_DATE)
(
  partition P201602 values less than (TO_DATE(' 2016-03-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace TS_R
    pctfree 10
    initrans 1
    maxtrans 255
    storage
    (
      initial 64K
      next 1M
      minextents 1
      maxextents unlimited
    ),
  partition PDEFAULT values less than (MAXVALUE)
    tablespace TS_R
    pctfree 10
    initrans 1
    maxtrans 255
    storage
    (
      initial 64K
      next 1M
      minextents 1
      maxextents unlimited
    )
);
-- Add comments to the table 
comment on table RC_BALANCE_DAILY_FINANCE
  is '缴款账户余额变更统计（业务日维度）VER=411.3#';
-- Add comments to the columns 
comment on column RC_BALANCE_DAILY_FINANCE.report_date
  is '统计日期（到天）';
comment on column RC_BALANCE_DAILY_FINANCE.province_center_id
  is '省中心ID';
comment on column RC_BALANCE_DAILY_FINANCE.account_id
  is '帐户ID';
comment on column RC_BALANCE_DAILY_FINANCE.prior_amount
  is '变化前余额';
comment on column RC_BALANCE_DAILY_FINANCE.load_time
  is '插入时间';
comment on column RC_BALANCE_DAILY_FINANCE.post_amount
  is '变化后余额';
comment on column RC_BALANCE_DAILY_FINANCE.deal_amount
  is '彩银系统交易金额';
comment on column RC_BALANCE_DAILY_FINANCE.log_deal_amount
  is '自然时间的彩银系统交易金额';
comment on column RC_BALANCE_DAILY_FINANCE.sale_amount
  is '销售金额';
comment on column RC_BALANCE_DAILY_FINANCE.cancel_amount
  is '取消金额';
comment on column RC_BALANCE_DAILY_FINANCE.paid_amount
  is '兑奖金额（含税）';
comment on column RC_BALANCE_DAILY_FINANCE.refund_amount
  is '实退票金额';
comment on column RC_BALANCE_DAILY_FINANCE.ctb_amount
  is '佣金转账户余额金额';
comment on column RC_BALANCE_DAILY_FINANCE.settled_deal_amount
  is '银行结算的彩银系统交易金额';
comment on column RC_BALANCE_DAILY_FINANCE.void_amount
  is '应退金额';
comment on column RC_BALANCE_DAILY_FINANCE.dealtype_10_amount
  is '彩银纯缴款金额';
comment on column RC_BALANCE_DAILY_FINANCE.dealtype_20_amount
  is '彩银纯冲正金额';
comment on column RC_BALANCE_DAILY_FINANCE.dealtype_30_amount
  is '彩银纯对账金额';
comment on column RC_BALANCE_DAILY_FINANCE.prior_credit_amount
  is '天内起点(00:00:00)信用额度';
comment on column RC_BALANCE_DAILY_FINANCE.post_credit_amount
  is '天内终止(23:59:59)信用额度';
-- Create/Recreate primary, unique and foreign key constraints 
alter table RC_BALANCE_DAILY_FINANCE
  add constraint PK_RC_BALANCE_DAILY_FINANCE primary key (REPORT_DATE, PROVINCE_CENTER_ID, ACCOUNT_ID);
  
  -- Create table
create table BC_ACCOUNT_BALANCE
(
  PROVINCE_CENTER_ID     NUMBER not null,
  ACCOUNT_ID             NUMBER not null,
  BALANCE_AMOUNT         NUMBER not null,
  BALANCE_AMOUNT_WARNING NUMBER not null
)
tablespace TS_ABC
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
-- Add comments to the table 
comment on table BC_ACCOUNT_BALANCE
  is '缴款帐户余额信息表VER=32.1#';
-- Add comments to the columns 
comment on column BC_ACCOUNT_BALANCE.PROVINCE_CENTER_ID
  is '省中心ID';
comment on column BC_ACCOUNT_BALANCE.ACCOUNT_ID
  is '帐户ID';
comment on column BC_ACCOUNT_BALANCE.BALANCE_AMOUNT
  is '缴款账户余额';
comment on column BC_ACCOUNT_BALANCE.BALANCE_AMOUNT_WARNING
  is '缴款账户余额预警阀值（元）';
-- Create/Recreate primary, unique and foreign key constraints 
alter table BC_ACCOUNT_BALANCE
  add constraint PK_BC_ACCOUNT_BALANCE primary key (ACCOUNT_ID)
  using index 
  tablespace TS_INDEX
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
 
-- Create table
create table B_ACCOUNT_INFO
(
  PROVINCE_CENTER_ID           NUMBER not null,
  ACCOUNT_ID                   NUMBER not null,
  ACCOUNT_NO                   VARCHAR2(60) not null,
  ACCOUNT_TYPE_CODE            NUMBER default 10 not null,
  ACCOUNT_HOLDER_ID            NUMBER not null,
  FROZEN_AMOUNT                NUMBER default 0 not null,
  ATTACHED_BALANCE_AMOUNT      NUMBER default 0 not null,
  CREDIT_AMOUNT                NUMBER not null,
  TEMP_CREDIT_AMOUNT           NUMBER not null,
  ACCOUNT_STATUS_CODE          NUMBER default 10 not null,
  STATUS_CHANGE_TIME           DATE default sysdate not null,
  DEL_FLAG                     NUMBER default 0 not null,
  SALE_BANK                    VARCHAR2(200),
  SALE_ACCOUNT                 VARCHAR2(200),
  COMMISSION_BANK              VARCHAR2(200),
  COMMISSION_ACCOUNT           VARCHAR2(200),
  TEMP_CREDIT_BEGIN_TIME       DATE,
  TEMP_CREDIT_AVAILABLE_LENGTH NUMBER,
  DEL_TIME                     DATE,
  MAIN_CHANNEL_TYPE            NUMBER default 1 not null
)
tablespace TS_ABC
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
-- Add comments to the table 
comment on table B_ACCOUNT_INFO
  is '帐户信息表VER=32.1#339.3#';
-- Add comments to the columns 
comment on column B_ACCOUNT_INFO.PROVINCE_CENTER_ID
  is '省中心ID';
comment on column B_ACCOUNT_INFO.ACCOUNT_ID
  is '帐户ID';
comment on column B_ACCOUNT_INFO.ACCOUNT_NO
  is '帐户编码';
comment on column B_ACCOUNT_INFO.ACCOUNT_TYPE_CODE
  is '账户模式（已配值域）';
comment on column B_ACCOUNT_INFO.ACCOUNT_HOLDER_ID
  is '账户所有者ID（账户模式为业主时=owner_id 账户模式为门店时=shop_id 账户模式为终端时=terminal_id 账户模式为销售员时=clerk_id）';
comment on column B_ACCOUNT_INFO.FROZEN_AMOUNT
  is '冻结金额';
comment on column B_ACCOUNT_INFO.ATTACHED_BALANCE_AMOUNT
  is '附属账户余额';
comment on column B_ACCOUNT_INFO.CREDIT_AMOUNT
  is '信用额度';
comment on column B_ACCOUNT_INFO.TEMP_CREDIT_AMOUNT
  is '临时信用余额';
comment on column B_ACCOUNT_INFO.ACCOUNT_STATUS_CODE
  is '账户状态（已配值域）';
comment on column B_ACCOUNT_INFO.STATUS_CHANGE_TIME
  is '状态修改时间';
comment on column B_ACCOUNT_INFO.DEL_FLAG
  is '删除标志 0=未删除1=已删除';
comment on column B_ACCOUNT_INFO.SALE_BANK
  is '销售款银行';
comment on column B_ACCOUNT_INFO.SALE_ACCOUNT
  is '销售款账号';
comment on column B_ACCOUNT_INFO.COMMISSION_BANK
  is '佣金银行';
comment on column B_ACCOUNT_INFO.COMMISSION_ACCOUNT
  is '佣金账号';
comment on column B_ACCOUNT_INFO.TEMP_CREDIT_BEGIN_TIME
  is '临时信用余额生效时间';
comment on column B_ACCOUNT_INFO.TEMP_CREDIT_AVAILABLE_LENGTH
  is '临时信用余额有效时长（秒）';
comment on column B_ACCOUNT_INFO.DEL_TIME
  is '删除时间';
comment on column B_ACCOUNT_INFO.MAIN_CHANNEL_TYPE
  is '主要投注渠道类型';
-- Create/Recreate primary, unique and foreign key constraints 
alter table B_ACCOUNT_INFO
  add constraint PK_B_ACCOUNT_INFO primary key (ACCOUNT_ID)
  using index 
  tablespace TS_INDEX
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
-- Create/Recreate indexes 
create index IND_B_ACCOUNT_INFO_HOLDER on B_ACCOUNT_INFO (PROVINCE_CENTER_ID, ACCOUNT_HOLDER_ID)
  tablespace TS_INDEX
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
create index IND_B_ACCOUNT_INFO_NO on B_ACCOUNT_INFO (PROVINCE_CENTER_ID, ACCOUNT_NO)
  tablespace TS_INDEX
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
create unique index UNQ_B_ACCOUNT_INFO_NO on B_ACCOUNT_INFO (PROVINCE_CENTER_ID, DECODE(DEL_FLAG,0,LPAD(ACCOUNT_NO,20,'0'),TO_CHAR(ACCOUNT_ID)))
  tablespace TS_INDEX
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

  --------------------------------------------------------
--  文件已创建 - 星期五-十月-27-2017   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table TC_BANK_DEAL_AGENT
--------------------------------------------------------

  CREATE TABLE "TC_BANK_DEAL_AGENT" 
   (	"PROVINCE_CENTER_ID" NUMBER, 
	"RECORD_ID" NUMBER, 
	"ACCOUNT_ID" NUMBER, 
	"REPORT_DATE" DATE, 
	"DEAL_AMOUNT" NUMBER, 
	"SHOP_ID" NUMBER, 
	"RELATED_RECORD_ID" NUMBER, 
	"RESERVED_A" NUMBER, 
	"RESERVED_B" VARCHAR2(30), 
	"RESERVED_C" DATE
   ) 
  PARTITION BY RANGE ("REPORT_DATE") 
  SUBPARTITION BY LIST ("PROVINCE_CENTER_ID") 
 (PARTITION "PDEFAULT"  VALUES LESS THAN (MAXVALUE) 
 ( SUBPARTITION "PDEFAULT_P11"  VALUES (11) , 
  SUBPARTITION "PDEFAULT_P12"  VALUES (12) , 
  SUBPARTITION "PDEFAULT_P13"  VALUES (13) , 
  SUBPARTITION "PDEFAULT_P14"  VALUES (14) , 
  SUBPARTITION "PDEFAULT_P15"  VALUES (15) , 
  SUBPARTITION "PDEFAULT_P21"  VALUES (21) , 
  SUBPARTITION "PDEFAULT_P22"  VALUES (22) , 
  SUBPARTITION "PDEFAULT_P23"  VALUES (23) , 
  SUBPARTITION "PDEFAULT_P31"  VALUES (31) , 
  SUBPARTITION "PDEFAULT_P32"  VALUES (32) , 
  SUBPARTITION "PDEFAULT_P33"  VALUES (33) , 
  SUBPARTITION "PDEFAULT_P34"  VALUES (34) , 
  SUBPARTITION "PDEFAULT_P35"  VALUES (35) , 
  SUBPARTITION "PDEFAULT_P36"  VALUES (36) , 
  SUBPARTITION "PDEFAULT_P37"  VALUES (37) , 
  SUBPARTITION "PDEFAULT_P41"  VALUES (41) , 
  SUBPARTITION "PDEFAULT_P42"  VALUES (42) , 
  SUBPARTITION "PDEFAULT_P43"  VALUES (43) , 
  SUBPARTITION "PDEFAULT_P44"  VALUES (44) , 
  SUBPARTITION "PDEFAULT_P45"  VALUES (45) , 
  SUBPARTITION "PDEFAULT_P46"  VALUES (46) , 
  SUBPARTITION "PDEFAULT_P50"  VALUES (50) , 
  SUBPARTITION "PDEFAULT_P51"  VALUES (51) , 
  SUBPARTITION "PDEFAULT_P52"  VALUES (52) , 
  SUBPARTITION "PDEFAULT_P53"  VALUES (53) , 
  SUBPARTITION "PDEFAULT_P54"  VALUES (54) , 
  SUBPARTITION "PDEFAULT_P61"  VALUES (61) , 
  SUBPARTITION "PDEFAULT_P62"  VALUES (62) , 
  SUBPARTITION "PDEFAULT_P63"  VALUES (63) , 
  SUBPARTITION "PDEFAULT_P64"  VALUES (64) , 
  SUBPARTITION "PDEFAULT_P65"  VALUES (65) ) ) ;

   COMMENT ON COLUMN "TC_BANK_DEAL_AGENT"."PROVINCE_CENTER_ID" IS '省中心ID';
   COMMENT ON COLUMN "TC_BANK_DEAL_AGENT"."RECORD_ID" IS '交易ID，同TC_BANK_DEAL.RECORD_ID';
   COMMENT ON COLUMN "TC_BANK_DEAL_AGENT"."ACCOUNT_ID" IS '代理账户ID';
   COMMENT ON COLUMN "TC_BANK_DEAL_AGENT"."REPORT_DATE" IS '划拨日期';
   COMMENT ON COLUMN "TC_BANK_DEAL_AGENT"."DEAL_AMOUNT" IS '划拨金额，冗余字段，交易性能需要';
   COMMENT ON COLUMN "TC_BANK_DEAL_AGENT"."SHOP_ID" IS '划拨门店ID，冗余字段，交易性能需要';
   COMMENT ON COLUMN "TC_BANK_DEAL_AGENT"."RELATED_RECORD_ID" IS '关联门店转账交易RECORD_ID';
   COMMENT ON COLUMN "TC_BANK_DEAL_AGENT"."RESERVED_A" IS '预留字段A，NUMBER';
   COMMENT ON COLUMN "TC_BANK_DEAL_AGENT"."RESERVED_B" IS '预留字段B，VARCHAR2(30)';
   COMMENT ON COLUMN "TC_BANK_DEAL_AGENT"."RESERVED_C" IS '预留字段C，DATE';
   COMMENT ON TABLE "TC_BANK_DEAL_AGENT"  IS '渠道代理账户转账明细表VER=1087.5#';
--------------------------------------------------------
--  DDL for Index PK_TC_BANK_DEAL_AGENT
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_TC_BANK_DEAL_AGENT" ON "TC_BANK_DEAL_AGENT" ("PROVINCE_CENTER_ID", "RECORD_ID", "REPORT_DATE") 
  ;
--------------------------------------------------------
--  DDL for Index IND_TC_BANK_DEAL_AGENT_ACCOUNT
--------------------------------------------------------

  CREATE INDEX "IND_TC_BANK_DEAL_AGENT_ACCOUNT" ON "TC_BANK_DEAL_AGENT" ("ACCOUNT_ID", "REPORT_DATE") 
  ;
--------------------------------------------------------
--  Constraints for Table TC_BANK_DEAL_AGENT
--------------------------------------------------------

  ALTER TABLE "TC_BANK_DEAL_AGENT" ADD CONSTRAINT "PK_TC_BANK_DEAL_AGENT" PRIMARY KEY ("PROVINCE_CENTER_ID", "RECORD_ID", "REPORT_DATE")
  USING INDEX  ENABLE;
  ALTER TABLE "TC_BANK_DEAL_AGENT" MODIFY ("RELATED_RECORD_ID" NOT NULL ENABLE);
  ALTER TABLE "TC_BANK_DEAL_AGENT" MODIFY ("SHOP_ID" NOT NULL ENABLE);
  ALTER TABLE "TC_BANK_DEAL_AGENT" MODIFY ("DEAL_AMOUNT" NOT NULL ENABLE);
  ALTER TABLE "TC_BANK_DEAL_AGENT" MODIFY ("REPORT_DATE" NOT NULL ENABLE);
  ALTER TABLE "TC_BANK_DEAL_AGENT" MODIFY ("ACCOUNT_ID" NOT NULL ENABLE);
  ALTER TABLE "TC_BANK_DEAL_AGENT" MODIFY ("RECORD_ID" NOT NULL ENABLE);
  ALTER TABLE "TC_BANK_DEAL_AGENT" MODIFY ("PROVINCE_CENTER_ID" NOT NULL ENABLE);
