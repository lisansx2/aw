drop table B_PROVINCE_CENTER purge;
drop table B_BRANCH purge;
drop table B_TERMINAL purge;
drop table B_SHOP purge;
drop table B_DOMAIN purge;
drop table B_CITY_CENTER purge;
drop table B_AGENT purge;
drop table C_GAME_DRAW purge;
drop table C_GAME_DEF purge;
drop table R_BASE purge;

--创建表、索引和注释
-- Create table
create table B_PROVINCE_CENTER
(
  PROVINCE_CENTER_ID         NUMBER not null,
  PROVINCE_CENTER_NO         VARCHAR2(6) not null,
  PROVINCE_CENTER_NAME       VARCHAR2(30) not null,
  PROVINCE_CENTER_FULL_NAME  VARCHAR2(60) not null,
  PROVINCE_CENTER_SHORT_NAME VARCHAR2(30) not null,
  TELEPHONE                  VARCHAR2(50),
  ADDRESS                    VARCHAR2(300),
  OLD_RDC_ID                 NUMBER,
  HELIOS_SCHEMA              VARCHAR2(50),
  ALPHABETIC_CODE            VARCHAR2(30) not null,
  SORT_ID                    NUMBER not null,
  STATUS_CODE                NUMBER default 10 not null,
  STATUS_CHANGE_TIME         DATE default sysdate not null,
  DEL_FLAG                   NUMBER default 0 not null,
  DEL_TIME                   DATE
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
  )
compress for oltp;
-- Add comments to the table 
comment on table B_PROVINCE_CENTER
  is '省中心表VER=32.1#';
-- Add comments to the columns 
comment on column B_PROVINCE_CENTER.PROVINCE_CENTER_ID
  is '省中心ID';
comment on column B_PROVINCE_CENTER.PROVINCE_CENTER_NO
  is '省中心编码';
comment on column B_PROVINCE_CENTER.PROVINCE_CENTER_NAME
  is '省中心名称';
comment on column B_PROVINCE_CENTER.PROVINCE_CENTER_FULL_NAME
  is '省中心全称';
comment on column B_PROVINCE_CENTER.PROVINCE_CENTER_SHORT_NAME
  is '省中心简称';
comment on column B_PROVINCE_CENTER.TELEPHONE
  is '电话号码（固话或手机）';
comment on column B_PROVINCE_CENTER.ADDRESS
  is '联系地址';
comment on column B_PROVINCE_CENTER.OLD_RDC_ID
  is '旧系统RDC_ID';
comment on column B_PROVINCE_CENTER.HELIOS_SCHEMA
  is '旧系统结构SCHEMA';
comment on column B_PROVINCE_CENTER.ALPHABETIC_CODE
  is '字母码';
comment on column B_PROVINCE_CENTER.SORT_ID
  is '排列ID';
comment on column B_PROVINCE_CENTER.STATUS_CODE
  is '状态标志（已配值域）';
comment on column B_PROVINCE_CENTER.STATUS_CHANGE_TIME
  is '状态修改时间';
comment on column B_PROVINCE_CENTER.DEL_FLAG
  is '删除标志 0=未删除1=已删除';
comment on column B_PROVINCE_CENTER.DEL_TIME
  is '删除时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table B_PROVINCE_CENTER
  add constraint PK_B_PROVINCE_CENTER primary key (PROVINCE_CENTER_ID)
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
create table B_BRANCH
(
  PROVINCE_CENTER_ID             NUMBER not null,
  TECH_SYSTEM_ID                 NUMBER default 0 not null,
  BRANCH_ID                      NUMBER not null,
  BRANCH_NO                      VARCHAR2(7) not null,
  BRANCH_NAME                    VARCHAR2(30) not null,
  BRANCH_LEVEL                   NUMBER not null,
  TICKET_CANCEL_LIMIT            NUMBER not null,
  TICKET_PAY_LIMIT               NUMBER not null,
  PARENT_BRANCH_ID               NUMBER default 0 not null,
  CITY_CENTER_ID                 NUMBER,
  TELEPHONE                      VARCHAR2(50),
  ADDRESS                        VARCHAR2(300),
  DESCS                          VARCHAR2(4000),
  OLD_BRANCH_NO                  VARCHAR2(30),
  SORT_ID                        NUMBER not null,
  STATUS_CODE                    NUMBER default 10 not null,
  STATUS_CHANGE_TIME             DATE default sysdate not null,
  DEL_FLAG                       NUMBER default 0 not null,
  DEL_TIME                       DATE,
  STAKE_PAY_LIMIT                NUMBER,
  INST_TICKET_PAY_MAX_LIMIT      NUMBER,
  INST_TICKET_PAY_MIN_LIMIT      NUMBER,
  INST_CROSS_PROVINCE_PAY_FLAG   NUMBER default 0,
  INST_CROSS_PROVINCE_PAY_MX_LMT NUMBER,
  COUNTY_CENTER_ID               NUMBER,
  CITY_ID                        NUMBER,
  COUNTY_ID                      NUMBER,
  MAIN_CHANNEL_TYPE              NUMBER default 1 not null
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
  )
compress for oltp;
-- Add comments to the table 
comment on table B_BRANCH
  is '兑奖处表VER=32.1#81.2#200.3#339.3#';
-- Add comments to the columns 
comment on column B_BRANCH.PROVINCE_CENTER_ID
  is '省中心ID';
comment on column B_BRANCH.TECH_SYSTEM_ID
  is '技术系统ID';
comment on column B_BRANCH.BRANCH_ID
  is '兑奖处ID';
comment on column B_BRANCH.BRANCH_NO
  is '兑奖处编码';
comment on column B_BRANCH.BRANCH_NAME
  is '兑奖处名称';
comment on column B_BRANCH.BRANCH_LEVEL
  is '兑奖处级别';
comment on column B_BRANCH.TICKET_CANCEL_LIMIT
  is '取消单票限额';
comment on column B_BRANCH.TICKET_PAY_LIMIT
  is '兑奖单票限额';
comment on column B_BRANCH.PARENT_BRANCH_ID
  is '上级兑奖机构ID';
comment on column B_BRANCH.CITY_CENTER_ID
  is '市级分中心ID';
comment on column B_BRANCH.TELEPHONE
  is '电话号码（固话或手机）';
comment on column B_BRANCH.ADDRESS
  is '联系地址';
comment on column B_BRANCH.DESCS
  is '描述';
comment on column B_BRANCH.OLD_BRANCH_NO
  is '旧系统兑奖处编号';
comment on column B_BRANCH.SORT_ID
  is '排列ID';
comment on column B_BRANCH.STATUS_CODE
  is '状态标志（已配值域）';
comment on column B_BRANCH.STATUS_CHANGE_TIME
  is '状态修改时间';
comment on column B_BRANCH.DEL_FLAG
  is '删除标志 0=未删除1=已删除';
comment on column B_BRANCH.DEL_TIME
  is '删除时间';
comment on column B_BRANCH.STAKE_PAY_LIMIT
  is '兑奖单注限额';
comment on column B_BRANCH.INST_TICKET_PAY_MAX_LIMIT
  is '即开票兑奖最高限额';
comment on column B_BRANCH.INST_TICKET_PAY_MIN_LIMIT
  is '即开票兑奖最低限额';
comment on column B_BRANCH.INST_CROSS_PROVINCE_PAY_FLAG
  is '是否允许即开票跨省兑奖';
comment on column B_BRANCH.INST_CROSS_PROVINCE_PAY_MX_LMT
  is '即开票跨省兑奖最高限额';
comment on column B_BRANCH.COUNTY_CENTER_ID
  is '县级分中心ID';
comment on column B_BRANCH.CITY_ID
  is '市区划ID';
comment on column B_BRANCH.COUNTY_ID
  is '县区划ID';
comment on column B_BRANCH.MAIN_CHANNEL_TYPE
  is '主要投注渠道类型';
-- Create/Recreate primary, unique and foreign key constraints 
alter table B_BRANCH
  add constraint PK_B_BRANCH primary key (PROVINCE_CENTER_ID, BRANCH_ID)
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
create table B_TERMINAL
(
  PROVINCE_CENTER_ID          NUMBER not null,
  SHOP_ID                     NUMBER not null,
  TERMINAL_ID                 NUMBER not null,
  BRANCH_ID                   NUMBER not null,
  TERMINAL_NO                 VARCHAR2(16) not null,
  CHANNEL_TYPE_CODE           NUMBER not null,
  TERMINAL_STATUS_CODE        NUMBER not null,
  STATUS_CHANGE_TIME          DATE default sysdate not null,
  SUSPENDED_FLAG              NUMBER default 0 not null,
  DEL_FLAG                    NUMBER default 0 not null,
  SALE_FLAG                   NUMBER not null,
  PAY_FLAG                    NUMBER not null,
  CROSS_PAY_FLAG              NUMBER not null,
  CANCEL_FLAG                 NUMBER not null,
  REFUND_FLAG                 NUMBER not null,
  CROSS_REFUND_FLAG           NUMBER not null,
  STAKE_PAY_LIMIT             NUMBER default 0 not null,
  TICKET_PAY_LIMIT            NUMBER not null,
  TOTAL_PAY_LIMIT             NUMBER not null,
  STAKE_CROSS_PAY_LIMIT       NUMBER default 0 not null,
  TICKET_CROSS_PAY_LIMIT      NUMBER not null,
  TOTAL_CROSS_PAY_LIMIT       NUMBER not null,
  TICKET_REFUND_LIMIT         NUMBER not null,
  TOTAL_REFUND_LIMIT          NUMBER not null,
  TICKET_CROSS_REFUND_LIMIT   NUMBER not null,
  TOTAL_CROSS_REFUND_LIMIT    NUMBER not null,
  MACHINE_TYPE_ID             NUMBER not null,
  REQUIRED_BASELINE_NO        NUMBER not null,
  PAY_CONFIRM_LIMIT           NUMBER not null,
  LARGE_LOTTERY_CONFIRM_LIMIT NUMBER not null,
  UPGRADED_FLAG               NUMBER default 1 not null,
  FTP_ID_MASTER               NUMBER not null,
  FTP_ID_MIRROR               NUMBER not null,
  COMMUNICATION_TYPE_CODE     NUMBER not null,
  TERMINAL_PASSWORD           VARCHAR2(50),
  COMMUNICATION_TOKEN         VARCHAR2(4000),
  INST_RETAILER_NO            VARCHAR2(10),
  INST_SOCKET_NO              VARCHAR2(4),
  INST_TERMINAL_SERIAL_NO     VARCHAR2(20),
  INST_RETAILER_IDX           VARCHAR2(24),
  INST_SOCKET_STATE           VARCHAR2(2),
  SUSPENDED_FLAG_CHANGE_TIME  DATE,
  TERMINAL_PRE_STATUS_CODE    NUMBER,
  DEL_TIME                    DATE,
  TERMINAL_NO_RESERVED        VARCHAR2(16),
  UPGRADED_TIME               DATE,
  OLD_SITE_NO                 VARCHAR2(100)
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
  )
compress for oltp;
-- Add comments to the table 
comment on table B_TERMINAL
  is '客户端表VER=32.1#198.3#';
-- Add comments to the columns 
comment on column B_TERMINAL.PROVINCE_CENTER_ID
  is '省中心ID';
comment on column B_TERMINAL.SHOP_ID
  is '门店ID';
comment on column B_TERMINAL.TERMINAL_ID
  is '客户端ID';
comment on column B_TERMINAL.BRANCH_ID
  is '兑奖处ID';
comment on column B_TERMINAL.TERMINAL_NO
  is '客户端编码';
comment on column B_TERMINAL.CHANNEL_TYPE_CODE
  is '投注渠道类型（已配值域）';
comment on column B_TERMINAL.TERMINAL_STATUS_CODE
  is '终端状态（已配值域）';
comment on column B_TERMINAL.STATUS_CHANGE_TIME
  is '状态修改时间';
comment on column B_TERMINAL.SUSPENDED_FLAG
  is '暂停标志 0=未暂停 1=已暂停';
comment on column B_TERMINAL.DEL_FLAG
  is '删除标志 0=未删除1=已删除';
comment on column B_TERMINAL.SALE_FLAG
  is '销售许可标志 0:禁止销售 1:允许销售';
comment on column B_TERMINAL.PAY_FLAG
  is '兑奖许可标志 0:禁止兑奖 1:允许兑奖';
comment on column B_TERMINAL.CROSS_PAY_FLAG
  is '通兑许可标志 0:禁止通兑 1:允许通兑';
comment on column B_TERMINAL.CANCEL_FLAG
  is '取消许可标志 0:禁止取消 1:允许取消';
comment on column B_TERMINAL.REFUND_FLAG
  is '退票许可标志 0:禁止退票 1:允许退票';
comment on column B_TERMINAL.CROSS_REFUND_FLAG
  is '通退许可标志 0:禁止通退 1:允许通退';
comment on column B_TERMINAL.STAKE_PAY_LIMIT
  is '兑奖单注限额';
comment on column B_TERMINAL.TICKET_PAY_LIMIT
  is '兑奖单票限额';
comment on column B_TERMINAL.TOTAL_PAY_LIMIT
  is '兑奖总量限额';
comment on column B_TERMINAL.STAKE_CROSS_PAY_LIMIT
  is '通兑单注限额';
comment on column B_TERMINAL.TICKET_CROSS_PAY_LIMIT
  is '通兑单票限额';
comment on column B_TERMINAL.TOTAL_CROSS_PAY_LIMIT
  is '通兑总量限额';
comment on column B_TERMINAL.TICKET_REFUND_LIMIT
  is '退票单票限额';
comment on column B_TERMINAL.TOTAL_REFUND_LIMIT
  is '退票总票限额';
comment on column B_TERMINAL.TICKET_CROSS_REFUND_LIMIT
  is '通退单票限额';
comment on column B_TERMINAL.TOTAL_CROSS_REFUND_LIMIT
  is '通退总量限额';
comment on column B_TERMINAL.MACHINE_TYPE_ID
  is '终端设备型号ID 0：所有型号';
comment on column B_TERMINAL.REQUIRED_BASELINE_NO
  is '要求应用软件基线';
comment on column B_TERMINAL.PAY_CONFIRM_LIMIT
  is '兑奖确认限制';
comment on column B_TERMINAL.LARGE_LOTTERY_CONFIRM_LIMIT
  is '大额销售确认限制';
comment on column B_TERMINAL.UPGRADED_FLAG
  is '客户端升级标示 0:未升级 1:已升级';
comment on column B_TERMINAL.FTP_ID_MASTER
  is '主更新服务器ID';
comment on column B_TERMINAL.FTP_ID_MIRROR
  is '镜像更新服务器ID';
comment on column B_TERMINAL.COMMUNICATION_TYPE_CODE
  is '通讯类型（已配值域）';
comment on column B_TERMINAL.TERMINAL_PASSWORD
  is '终端登录密码';
comment on column B_TERMINAL.COMMUNICATION_TOKEN
  is '终端与运营商通讯令牌';
comment on column B_TERMINAL.INST_RETAILER_NO
  is '即开票代理编号';
comment on column B_TERMINAL.INST_SOCKET_NO
  is '即开系统接入的接入端口';
comment on column B_TERMINAL.INST_TERMINAL_SERIAL_NO
  is '即开设备硬件序列号';
comment on column B_TERMINAL.INST_RETAILER_IDX
  is '即开网点的内部编码';
comment on column B_TERMINAL.INST_SOCKET_STATE
  is '即开系统SocketNo的状态';
comment on column B_TERMINAL.SUSPENDED_FLAG_CHANGE_TIME
  is '暂停标志修改时间';
comment on column B_TERMINAL.TERMINAL_PRE_STATUS_CODE
  is '销售终端之前状态';
comment on column B_TERMINAL.DEL_TIME
  is '删除时间';
comment on column B_TERMINAL.TERMINAL_NO_RESERVED
  is '预约客户端编号(旧终端升级后的新规则编号)';
comment on column B_TERMINAL.UPGRADED_TIME
  is '客户端升级时间';
comment on column B_TERMINAL.OLD_SITE_NO
  is '老系统终端编号';
-- Create/Recreate primary, unique and foreign key constraints 
alter table B_TERMINAL
  add constraint PK_B_TERMINAL primary key (TERMINAL_ID)
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
create index IND_B_TERMINAL_NO on B_TERMINAL (PROVINCE_CENTER_ID, TERMINAL_NO)
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
create index IND_B_TERMINAL_SHOP on B_TERMINAL (SHOP_ID)
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
create index IND_B_TERMINAL_STATUS on B_TERMINAL (PROVINCE_CENTER_ID, TERMINAL_STATUS_CODE, DEL_FLAG)
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
create unique index UNQ_B_TERMINAL_NO on B_TERMINAL (PROVINCE_CENTER_ID, DECODE(DEL_FLAG,0,LPAD(TERMINAL_NO,13,'0'),TO_CHAR(TERMINAL_ID)))
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
create table B_SHOP
(
  PROVINCE_CENTER_ID         NUMBER not null,
  SHOP_ID                    NUMBER not null,
  SHOP_NO                    VARCHAR2(10) not null,
  SHOP_NAME                  VARCHAR2(30) not null,
  OWNER_ID                   NUMBER not null,
  SHOP_TYPE_CODE             NUMBER not null,
  SHOP_COMPETITION_TYPE_CODE NUMBER not null,
  SHOP_LEVEL_CODE            NUMBER default 30 not null,
  CITY_CENTER_ID             NUMBER not null,
  CITY_ID                    NUMBER not null,
  SUSPENDED_FLAG             NUMBER default 0 not null,
  SHOP_STATUS_CODE           NUMBER default 10 not null,
  STATUS_CHANGE_TIME         DATE default sysdate not null,
  DEL_FLAG                   NUMBER default 0 not null,
  SALE_DEPUTY_FLAG           NUMBER default 0 not null,
  MANAGER_NAME               VARCHAR2(100) not null,
  MANAGER_CREDENTIALS_NO     VARCHAR2(200),
  SHOP_FULL_NAME             VARCHAR2(60),
  SALE_DEPUTY_ID             NUMBER,
  AGENT_ID                   NUMBER,
  SECTION_ID                 NUMBER,
  TELEPHONE                  VARCHAR2(50),
  ADDRESS                    VARCHAR2(300),
  DESCS                      VARCHAR2(4000),
  COUNTY_CENTER_ID           NUMBER,
  COUNTY_ID                  NUMBER,
  WAREHOUSE_ID               NUMBER,
  GPS_INFO                   VARCHAR2(30),
  SHOP_PRE_STATUS_CODE       NUMBER,
  DEL_TIME                   DATE,
  SHOP_NO_RESERVED           VARCHAR2(10),
  EMAIL                      VARCHAR2(100),
  ZIP_CODE                   VARCHAR2(100),
  FAX                        VARCHAR2(100),
  MAIN_CHANNEL_TYPE          NUMBER default 1 not null
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
  )
compress for oltp;
-- Add comments to the table 
comment on table B_SHOP
  is '门店表VER=32.1#339.3#';
-- Add comments to the columns 
comment on column B_SHOP.PROVINCE_CENTER_ID
  is '省中心ID';
comment on column B_SHOP.SHOP_ID
  is '门店ID';
comment on column B_SHOP.SHOP_NO
  is '门店编码';
comment on column B_SHOP.SHOP_NAME
  is '门店名称';
comment on column B_SHOP.OWNER_ID
  is '业主ID';
comment on column B_SHOP.SHOP_TYPE_CODE
  is '门店业务类型（已配值域）';
comment on column B_SHOP.SHOP_COMPETITION_TYPE_CODE
  is '门店竞争关系类型（已配值域）';
comment on column B_SHOP.SHOP_LEVEL_CODE
  is '门店级别分类（已配值域）';
comment on column B_SHOP.CITY_CENTER_ID
  is '市级分中心ID';
comment on column B_SHOP.CITY_ID
  is '市ID';
comment on column B_SHOP.SUSPENDED_FLAG
  is '暂停标志 0=未暂停 1=已暂停';
comment on column B_SHOP.SHOP_STATUS_CODE
  is '门店状态标志（已配值域）';
comment on column B_SHOP.STATUS_CHANGE_TIME
  is '状态修改时间';
comment on column B_SHOP.DEL_FLAG
  is '删除标志 0=未删除1=已删除';
comment on column B_SHOP.SALE_DEPUTY_FLAG
  is '销售代表标识';
comment on column B_SHOP.MANAGER_NAME
  is '店长姓名';
comment on column B_SHOP.MANAGER_CREDENTIALS_NO
  is '店长证件编号';
comment on column B_SHOP.SHOP_FULL_NAME
  is '门店全称';
comment on column B_SHOP.SALE_DEPUTY_ID
  is '销售代表ID';
comment on column B_SHOP.AGENT_ID
  is '代理ID';
comment on column B_SHOP.SECTION_ID
  is '片区ID';
comment on column B_SHOP.TELEPHONE
  is '电话号码（固话或手机）';
comment on column B_SHOP.ADDRESS
  is '联系地址';
comment on column B_SHOP.DESCS
  is '描述';
comment on column B_SHOP.COUNTY_CENTER_ID
  is '县级分中心ID';
comment on column B_SHOP.COUNTY_ID
  is '县ID';
comment on column B_SHOP.WAREHOUSE_ID
  is '仓库ID';
comment on column B_SHOP.GPS_INFO
  is 'GPS信息';
comment on column B_SHOP.SHOP_PRE_STATUS_CODE
  is '之前门店状态标志';
comment on column B_SHOP.DEL_TIME
  is '删除时间';
comment on column B_SHOP.SHOP_NO_RESERVED
  is '预约门店编号';
comment on column B_SHOP.EMAIL
  is '电子邮箱';
comment on column B_SHOP.ZIP_CODE
  is '邮政编码';
comment on column B_SHOP.FAX
  is '传真';
comment on column B_SHOP.MAIN_CHANNEL_TYPE
  is '主要投注渠道类型';
-- Create/Recreate primary, unique and foreign key constraints 
alter table B_SHOP
  add constraint PK_B_SHOP primary key (SHOP_ID)
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
create index IND_B_SHOP_SHOPNO on B_SHOP (PROVINCE_CENTER_ID, SHOP_NO)
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
create index IND_B_SHOP_STATUS on B_SHOP (PROVINCE_CENTER_ID, CITY_CENTER_ID, SHOP_STATUS_CODE, DEL_FLAG)
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
create unique index UNQ_B_SHOP_NO on B_SHOP (PROVINCE_CENTER_ID, DECODE(DEL_FLAG,0,LPAD(SHOP_NO,10,'0'),TO_CHAR(SHOP_ID)))
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
create table B_DOMAIN
(
  DOM_TYPE_CODE VARCHAR2(50) not null,
  DOM_KEY       NUMBER not null,
  DOM_VALUE     VARCHAR2(200) not null,
  DOM_TYPE      VARCHAR2(50) not null,
  DOM_DESC      VARCHAR2(4000)
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
  )
compress for oltp;
-- Add comments to the table 
comment on table B_DOMAIN
  is '值域表VER=32.1#200.2#';
-- Add comments to the columns 
comment on column B_DOMAIN.DOM_TYPE_CODE
  is '值域类型编码';
comment on column B_DOMAIN.DOM_KEY
  is '值域KEY';
comment on column B_DOMAIN.DOM_VALUE
  is '值域VALUE';
comment on column B_DOMAIN.DOM_TYPE
  is '值域类型中文名';
comment on column B_DOMAIN.DOM_DESC
  is '值域描述';
-- Create/Recreate primary, unique and foreign key constraints 
alter table B_DOMAIN
  add constraint PK_B_DOMAIN primary key (DOM_TYPE_CODE, DOM_KEY)
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
create table B_CITY_CENTER
(
  PROVINCE_CENTER_ID       NUMBER not null,
  CITY_CENTER_ID           NUMBER not null,
  CITY_CENTER_NO           VARCHAR2(6) not null,
  CITY_CENTER_NAME         VARCHAR2(30) not null,
  CITY_CENTER_FULL_NAME    VARCHAR2(60),
  COUNTY_FLAG              NUMBER default 0 not null,
  SORT_ID                  NUMBER not null,
  STATUS_CODE              NUMBER default 10 not null,
  STATUS_CHANGE_TIME       DATE default sysdate not null,
  TELEPHONE                VARCHAR2(50),
  ADDRESS                  VARCHAR2(300),
  OLD_LOCATION_NO          VARCHAR2(30),
  OLD_LOCATION_TYPE        NUMBER,
  DEL_FLAG                 NUMBER default 0 not null,
  DEL_TIME                 DATE,
  MAIN_CHANNEL_TYPE        NUMBER default 1 not null,
  PROV_DIRECT_CONTROL_FLAG NUMBER default 0
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
  )
compress for oltp;
-- Add comments to the table 
comment on table B_CITY_CENTER
  is '市级分中心表VER=32.1#339.3#363.3#';
-- Add comments to the columns 
comment on column B_CITY_CENTER.PROVINCE_CENTER_ID
  is '省中心ID';
comment on column B_CITY_CENTER.CITY_CENTER_ID
  is '市级分中心ID';
comment on column B_CITY_CENTER.CITY_CENTER_NO
  is '市级分中心编码';
comment on column B_CITY_CENTER.CITY_CENTER_NAME
  is '市级分中心名称';
comment on column B_CITY_CENTER.CITY_CENTER_FULL_NAME
  is '市级分中心全称';
comment on column B_CITY_CENTER.COUNTY_FLAG
  is '直管县标志 1:直管县 0:普通市中心';
comment on column B_CITY_CENTER.SORT_ID
  is '排列ID';
comment on column B_CITY_CENTER.STATUS_CODE
  is '状态标志（已配值域）';
comment on column B_CITY_CENTER.STATUS_CHANGE_TIME
  is '状态修改时间';
comment on column B_CITY_CENTER.TELEPHONE
  is '电话号码（固话或手机）';
comment on column B_CITY_CENTER.ADDRESS
  is '联系地址';
comment on column B_CITY_CENTER.OLD_LOCATION_NO
  is '旧系统LOCATION编号';
comment on column B_CITY_CENTER.OLD_LOCATION_TYPE
  is '旧系统LOCATION类型';
comment on column B_CITY_CENTER.DEL_FLAG
  is '删除标志 0=未删除1=已删除';
comment on column B_CITY_CENTER.DEL_TIME
  is '删除时间';
comment on column B_CITY_CENTER.MAIN_CHANNEL_TYPE
  is '主要投注渠道类型';
comment on column B_CITY_CENTER.PROV_DIRECT_CONTROL_FLAG
  is '省中心直管标记 1:省中心直管 0:普通';
-- Create/Recreate primary, unique and foreign key constraints 
alter table B_CITY_CENTER
  add constraint PK_B_CITY_CENTER primary key (CITY_CENTER_ID)
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
create table B_AGENT  (
   PROVINCE_CENTER_ID   NUMBER                          not null,
   AGENT_ID             NUMBER                          not null,
   AGENT_NO             VARCHAR2(8)                     not null,
   AGENT_NAME           VARCHAR2(30)                    not null,
   AGENT_FULL_NAME      VARCHAR2(100)                   not null,
   AGENT_TYPE_CODE      NUMBER                          not null,
   AGENT_LEVEL          NUMBER                          not null,
   PARENT_AGENT_ID      NUMBER,
   CITY_CENTER_ID       NUMBER,
   TELEPHONE            VARCHAR2(50),
   ADDRESS              VARCHAR2(300),
   DESCS                VARCHAR2(4000),
   STATUS_CODE          NUMBER                         default 10 not null,
   STATUS_CHANGE_TIME   DATE                           default sysdate not null,
   DEL_FLAG             NUMBER                         default 0 not null,
   DEL_TIME             DATE,
   MAIN_CHANNEL_TYPE    NUMBER                         default 1 not null,
   CORPORATION_NAME     VARCHAR2(60),
   CORPORATION_CREDENTIALS_NO VARCHAR2(100),
   GET_BALANCE_THRESHOLD_AMOUNT NUMBER,
   GET_BALANCE_AMOUNT   NUMBER,
   CREDENTIALS_TYPE_CODE NUMBER,
   CREDENTIALS_NO       VARCHAR2(100),
   DAILY_MAX_ADJUST_TIMES NUMBER                         default -1 not null
)
tablespace ts_abc
compress for oltp;

comment on table B_AGENT is
'销售渠道代理表VER=32.1#339.3#824.4#1087.5#';

comment on column B_AGENT.PROVINCE_CENTER_ID is
'省中心ID';

comment on column B_AGENT.AGENT_ID is
'代理ID';

comment on column B_AGENT.AGENT_NO is
'代理编码';

comment on column B_AGENT.AGENT_NAME is
'代理名称';

comment on column B_AGENT.AGENT_FULL_NAME is
'代理全称';

comment on column B_AGENT.AGENT_TYPE_CODE is
'代理类型（已配值域）';

comment on column B_AGENT.AGENT_LEVEL is
'代理级别';

comment on column B_AGENT.PARENT_AGENT_ID is
'上级代理ID';

comment on column B_AGENT.CITY_CENTER_ID is
'市级分中心ID';

comment on column B_AGENT.TELEPHONE is
'电话号码（固话或手机）';

comment on column B_AGENT.ADDRESS is
'联系地址';

comment on column B_AGENT.DESCS is
'描述';

comment on column B_AGENT.STATUS_CODE is
'状态标志（已配值域）';

comment on column B_AGENT.STATUS_CHANGE_TIME is
'状态修改时间';

comment on column B_AGENT.DEL_FLAG is
'删除标志 0=未删除1=已删除';

comment on column B_AGENT.DEL_TIME is
'删除时间';

comment on column B_AGENT.MAIN_CHANNEL_TYPE is
'主要投注渠道类型';

comment on column B_AGENT.CORPORATION_NAME is
'法人姓名';

comment on column B_AGENT.CORPORATION_CREDENTIALS_NO is
'法人身份证号';

comment on column B_AGENT.GET_BALANCE_THRESHOLD_AMOUNT is
'下属门店自动从代理转账阀值';

comment on column B_AGENT.GET_BALANCE_AMOUNT is
'下属门店自动从代理转入额度';

comment on column B_AGENT.CREDENTIALS_TYPE_CODE is
'证件类型（已配值域）';

comment on column B_AGENT.CREDENTIALS_NO is
'证件号码';

comment on column b_agent.DAILY_MAX_ADJUST_TIMES is '门店每日转入额度的最多次数，必须是-1或者大于等于0的整数，值为-1时表示对每日转入次数没有限制';

alter table B_AGENT
   add constraint PK_B_AGENT primary key (PROVINCE_CENTER_ID, AGENT_ID)
      using index tablespace ts_index;


-- Create table
create table C_GAME_DRAW
(
  PROVINCE_CENTER_ID   NUMBER not null,
  TECH_SYSTEM_ID       NUMBER default 0 not null,
  GAME_ID              NUMBER not null,
  DRAW_ID              NUMBER not null,
  DRAW_NO              VARCHAR2(10) not null,
  DRAW_STATUS_CODE     NUMBER not null,
  SALE_BEGIN_TIME      DATE not null,
  SALE_END_TIME        DATE not null,
  DRAW_TIME            DATE not null,
  PAY_BEGIN_TIME       DATE not null,
  PAY_END_TIME         DATE not null,
  PROMOTION_FLAG       NUMBER default 0 not null,
  TEST_FLAG            NUMBER default 0 not null,
  FREE_FLAG            NUMBER default 0 not null,
  RUN_FLAG             NUMBER not null,
  SUSPENDED_FLAG       NUMBER default 0 not null,
  VOID_FLAG            NUMBER default 0 not null,
  MULTI_BET_CNT        NUMBER not null,
  MULTI_DRAW_CNT       NUMBER not null,
  EXP_CNT              NUMBER default 0 not null,
  LAST_EXP_TIME        DATE,
  DRAW_RESULT          VARCHAR2(90),
  COUNT_RESULT         VARCHAR2(90),
  UNSORT_RESULT        VARCHAR2(90),
  TRIAL_NO             VARCHAR2(30),
  PRIZE_PIECES         NUMBER,
  DRAW_DESC            VARCHAR2(4000),
  TO_SALE_END_TIME     DATE,
  TO_PAY_END_TIME      DATE,
  MAIN_RELATED_GAME_ID NUMBER,
  MAIN_RELATED_DRAW_ID NUMBER,
  VOID_REASON_CODE     NUMBER
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
  )
compress for oltp;
-- Add comments to the table 
comment on table C_GAME_DRAW
  is '游戏奖期表VER=32.1#';
-- Add comments to the columns 
comment on column C_GAME_DRAW.PROVINCE_CENTER_ID
  is '省中心ID ';
comment on column C_GAME_DRAW.TECH_SYSTEM_ID
  is '技术系统ID';
comment on column C_GAME_DRAW.GAME_ID
  is '游戏ID';
comment on column C_GAME_DRAW.DRAW_ID
  is '奖期ID';
comment on column C_GAME_DRAW.DRAW_NO
  is '奖期编码';
comment on column C_GAME_DRAW.DRAW_STATUS_CODE
  is '奖期状态（已配值域）';
comment on column C_GAME_DRAW.SALE_BEGIN_TIME
  is '销售开始时间';
comment on column C_GAME_DRAW.SALE_END_TIME
  is '销售结束时间';
comment on column C_GAME_DRAW.DRAW_TIME
  is '开奖时间';
comment on column C_GAME_DRAW.PAY_BEGIN_TIME
  is '开始兑奖时间';
comment on column C_GAME_DRAW.PAY_END_TIME
  is '结束兑奖时间';
comment on column C_GAME_DRAW.PROMOTION_FLAG
  is '促销标志 0:非促销 1:促销';
comment on column C_GAME_DRAW.TEST_FLAG
  is '试卖标志 0:非试卖 1:试卖';
comment on column C_GAME_DRAW.FREE_FLAG
  is '赠票标志 0:非赠票 1:赠票';
comment on column C_GAME_DRAW.RUN_FLAG
  is '运行状态 0:异常 1:正常';
comment on column C_GAME_DRAW.SUSPENDED_FLAG
  is '暂停标志 0=未暂停 1=已暂停';
comment on column C_GAME_DRAW.VOID_FLAG
  is '退票标志 0无退票 1有退票';
comment on column C_GAME_DRAW.MULTI_BET_CNT
  is '注倍投数';
comment on column C_GAME_DRAW.MULTI_DRAW_CNT
  is '多期数';
comment on column C_GAME_DRAW.EXP_CNT
  is '数据封存计数';
comment on column C_GAME_DRAW.LAST_EXP_TIME
  is '最后一次数据封存时间';
comment on column C_GAME_DRAW.DRAW_RESULT
  is '开奖结果';
comment on column C_GAME_DRAW.COUNT_RESULT
  is '计奖用结果';
comment on column C_GAME_DRAW.UNSORT_RESULT
  is '未排序结果';
comment on column C_GAME_DRAW.TRIAL_NO
  is '试机号';
comment on column C_GAME_DRAW.PRIZE_PIECES
  is '开奖球套数';
comment on column C_GAME_DRAW.DRAW_DESC
  is '奖期描述';
comment on column C_GAME_DRAW.TO_SALE_END_TIME
  is '实际销售结束时间';
comment on column C_GAME_DRAW.TO_PAY_END_TIME
  is '操作结束兑奖时间';
comment on column C_GAME_DRAW.MAIN_RELATED_GAME_ID
  is '主关联游戏ID';
comment on column C_GAME_DRAW.MAIN_RELATED_DRAW_ID
  is '主关联游戏奖期ID';
comment on column C_GAME_DRAW.VOID_REASON_CODE
  is '退票原因类型（已配值域）';
-- Create/Recreate primary, unique and foreign key constraints 
alter table C_GAME_DRAW
  add constraint PK_C_GAME_DRAW primary key (PROVINCE_CENTER_ID, DRAW_ID)
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
alter table C_GAME_DRAW
  add constraint UNQ_DRAW_NO_IN_GAME unique (PROVINCE_CENTER_ID, GAME_ID, DRAW_NO)
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
create index IND_C_GAME_DRAW on C_GAME_DRAW (PROVINCE_CENTER_ID, DRAW_STATUS_CODE)
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
create index IND_C_GAME_DRAW2 on C_GAME_DRAW (PROVINCE_CENTER_ID, MAIN_RELATED_DRAW_ID)
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
create index IND_C_GAME_DRAW_SALEENDTIME on C_GAME_DRAW (PROVINCE_CENTER_ID, SALE_END_TIME)
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
create index IND_C_GAME_DRAW_TIME on C_GAME_DRAW (PROVINCE_CENTER_ID, GAME_ID, DRAW_TIME, DRAW_ID)
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
create table C_GAME_DEF
(
  PROVINCE_CENTER_ID        NUMBER not null,
  LOTTERY_ISSUER_ID         NUMBER not null,
  TECH_SYSTEM_ID            NUMBER default 0 not null,
  GAME_ID                   NUMBER not null,
  GAME_NO                   VARCHAR2(8) not null,
  GAME_NAME                 VARCHAR2(45) not null,
  GAME_SHORT_NAME           VARCHAR2(45) not null,
  GAME_TYPE_CODE            NUMBER not null,
  GAME_AREA_RANGE_TYPE_CODE NUMBER not null,
  GAME_FUND_RANGE_TYPE_CODE NUMBER not null,
  RUNNING_CENTER_ID         NUMBER not null,
  MULTI_BET_CNT             NUMBER not null,
  MULTI_DRAW_CNT            NUMBER not null,
  GAME_STATUS_CODE          NUMBER not null,
  STATUS_CHANGE_TIME        DATE default sysdate not null,
  RESULT_SORT_FLAG          NUMBER not null,
  AUTHORIZED_FLAG           NUMBER default 0 not null,
  SUSPENDED_FLAG            NUMBER default 0 not null,
  TEST_FLAG                 NUMBER default 0 not null,
  DISPLAY_FLAG              NUMBER default 1 not null,
  ADDTO_FLAG                NUMBER not null,
  ODDS_FLAG                 NUMBER not null,
  MULTI_ODDS_FLAG           NUMBER not null,
  STAKE_PRICE               NUMBER not null,
  MAX_LOTTERY_AMOUNT        NUMBER not null,
  RETURN_PCT                NUMBER not null,
  ADJUST_FUND_PCT           NUMBER not null,
  COMMONWEAL_FUND_PCT       NUMBER not null,
  ISSUE_COST_PCT            NUMBER not null,
  NDC_ISSUE_COST_PCT        NUMBER not null,
  ACCUMULATION_FUND         NUMBER not null,
  ADJUST_FUND               NUMBER not null,
  COUNT_PROCEDURE_NAME      VARCHAR2(100) not null,
  BONUS_PROCEDURE           VARCHAR2(100) not null,
  PLAY_CNT                  NUMBER not null,
  SALE_COMMISSION_BPCT      NUMBER not null,
  PAID_COMMISSION_BPCT      NUMBER not null,
  MANAGE_COMMISSION_BPCT    NUMBER default 0 not null,
  SET_VALUE                 NUMBER,
  WIN_PCT                   NUMBER,
  BEGIN_TIME                DATE,
  END_TIME                  DATE,
  MAIN_RELATED_GAME_ID      NUMBER
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
  )
compress for oltp;
-- Add comments to the table 
comment on table C_GAME_DEF
  is '游戏表VER=32.1#';
-- Add comments to the columns 
comment on column C_GAME_DEF.PROVINCE_CENTER_ID
  is '省中心ID';
comment on column C_GAME_DEF.LOTTERY_ISSUER_ID
  is '彩票发行机构ID';
comment on column C_GAME_DEF.TECH_SYSTEM_ID
  is '技术系统ID';
comment on column C_GAME_DEF.GAME_ID
  is '游戏ID';
comment on column C_GAME_DEF.GAME_NO
  is '游戏编码';
comment on column C_GAME_DEF.GAME_NAME
  is '游戏名称';
comment on column C_GAME_DEF.GAME_SHORT_NAME
  is '游戏简称';
comment on column C_GAME_DEF.GAME_TYPE_CODE
  is '游戏种类（已配值域）';
comment on column C_GAME_DEF.GAME_AREA_RANGE_TYPE_CODE
  is '游戏区域范围类型（已配值域）';
comment on column C_GAME_DEF.GAME_FUND_RANGE_TYPE_CODE
  is '游戏资金范围类型（已配值域）';
comment on column C_GAME_DEF.RUNNING_CENTER_ID
  is '本游戏开奖中心（开奖中心所在省中心ID）';
comment on column C_GAME_DEF.MULTI_BET_CNT
  is '注倍投数';
comment on column C_GAME_DEF.MULTI_DRAW_CNT
  is '多期数';
comment on column C_GAME_DEF.GAME_STATUS_CODE
  is '游戏状态（已配值域）';
comment on column C_GAME_DEF.STATUS_CHANGE_TIME
  is '状态修改时间';
comment on column C_GAME_DEF.RESULT_SORT_FLAG
  is '开奖结果排序标志 0:不排序 1:排序';
comment on column C_GAME_DEF.AUTHORIZED_FLAG
  is '授权标志 0:未授权 1:授权 无授权时，国家中心激活奖期时不会创建该省中心奖期记录。';
comment on column C_GAME_DEF.SUSPENDED_FLAG
  is '暂停标志 0=未暂停 1=已暂停';
comment on column C_GAME_DEF.TEST_FLAG
  is '试卖标志 0:非试卖 1:试卖';
comment on column C_GAME_DEF.DISPLAY_FLAG
  is '显示标志 0:不显示 1:显示';
comment on column C_GAME_DEF.ADDTO_FLAG
  is '投注标志 0:非追加 1:追加';
comment on column C_GAME_DEF.ODDS_FLAG
  is '限号标志 0:不限号 1:限号';
comment on column C_GAME_DEF.MULTI_ODDS_FLAG
  is '多期限号标志 0:不限号 1:限号';
comment on column C_GAME_DEF.STAKE_PRICE
  is '单注金额';
comment on column C_GAME_DEF.MAX_LOTTERY_AMOUNT
  is '最大单票金额';
comment on column C_GAME_DEF.RETURN_PCT
  is '返奖率(返奖率+公益金比例+发行费比例=1)';
comment on column C_GAME_DEF.ADJUST_FUND_PCT
  is '调节基金比例(包含在返奖率内)';
comment on column C_GAME_DEF.COMMONWEAL_FUND_PCT
  is '公益金比例(返奖率+公益金比例+发行费比例=1)';
comment on column C_GAME_DEF.ISSUE_COST_PCT
  is '发行费比例(返奖率+公益金比例+发行费比例=1)';
comment on column C_GAME_DEF.NDC_ISSUE_COST_PCT
  is '总局中心发行费比例(包含在发行费比例内)';
comment on column C_GAME_DEF.ACCUMULATION_FUND
  is '滚存奖池(联网游戏和区域游戏记在PCID=0上省中心为NULL；本地游戏记在PCID=0、省中心；排列3记在PCID=省中心、PCID=0为空)';
comment on column C_GAME_DEF.ADJUST_FUND
  is '调节基金(联网游戏和区域游戏记在PCID=0上省中心为NULL；本地游戏记在PCID=0、省中心；排列3记在PCID=省中心、PCID=0为空)';
comment on column C_GAME_DEF.COUNT_PROCEDURE_NAME
  is '计奖过程名称';
comment on column C_GAME_DEF.BONUS_PROCEDURE
  is '单注奖金计算过程名称';
comment on column C_GAME_DEF.PLAY_CNT
  is '游戏方式个数';
comment on column C_GAME_DEF.SALE_COMMISSION_BPCT
  is '销售佣金基础比例';
comment on column C_GAME_DEF.PAID_COMMISSION_BPCT
  is '兑奖佣金基础比例';
comment on column C_GAME_DEF.MANAGE_COMMISSION_BPCT
  is '管理佣金基础比例';
comment on column C_GAME_DEF.SET_VALUE
  is '奖组（即限号对象个数）';
comment on column C_GAME_DEF.WIN_PCT
  is '中奖率';
comment on column C_GAME_DEF.BEGIN_TIME
  is '开始时间';
comment on column C_GAME_DEF.END_TIME
  is '结束时间';
comment on column C_GAME_DEF.MAIN_RELATED_GAME_ID
  is '主关联游戏ID';
-- Create/Recreate primary, unique and foreign key constraints 
alter table C_GAME_DEF
  add constraint PK_C_GAME_DEF primary key (PROVINCE_CENTER_ID, GAME_ID)
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
create table R_BASE
(
  REPORT_DATE               DATE not null,
  FINANCE_DATE              DATE not null,
  PROVINCE_CENTER_ID        NUMBER not null,
  TERMINAL_ID               NUMBER not null,
  DRAW_ID                   NUMBER not null,
  BRANCH_ID                 NUMBER not null,
  SHOP_ID                   NUMBER not null,
  CITY_CENTER_ID            NUMBER not null,
  LOGON_ID                  NUMBER not null,
  ACCOUNT_ID                NUMBER not null,
  GAME_ID                   NUMBER not null,
  TECH_SYSTEM_ID            NUMBER default 0 not null,
  CHANNEL_TYPE_CODE         NUMBER not null,
  LAST_UPDATE_TIME          DATE default sysdate not null,
  COUNTY_CENTER_ID          NUMBER,
  SALE_LOTTERY_CNT          NUMBER,
  SALE_STAKE_CNT            NUMBER,
  SALE_AMOUNT               NUMBER,
  SALE_LOTTERY_CNT_SETTLED  NUMBER,
  SALE_STAKE_CNT_SETTLED    NUMBER,
  SALE_AMOUNT_SETTLED       NUMBER,
  SALE_LOTTERY_CNT_MULTI    NUMBER,
  SALE_STAKE_CNT_MULTI      NUMBER,
  SALE_AMOUNT_MULTI         NUMBER,
  CANCEL_LOTTERY_CNT        NUMBER,
  CANCEL_STAKE_CNT          NUMBER,
  CANCEL_AMOUNT             NUMBER,
  PROV_CANCEL_LOTTERY_CNT   NUMBER,
  PROV_CANCEL_STAKE_CNT     NUMBER,
  PROV_CANCEL_AMOUNT        NUMBER,
  BRANCH_CANCEL_LOTTERY_CNT NUMBER,
  BRANCH_CANCEL_STAKE_CNT   NUMBER,
  BRANCH_CANCEL_AMOUNT      NUMBER,
  STS_PAID_LOTTERY_CNT      NUMBER,
  STS_PAID_STAKE_CNT        NUMBER,
  STS_PAID_AMOUNT           NUMBER,
  STO_PAID_LOTTERY_CNT      NUMBER,
  STO_PAID_STAKE_CNT        NUMBER,
  STO_PAID_AMOUNT           NUMBER,
  OTS_PAID_LOTTERY_CNT      NUMBER,
  OTS_PAID_STAKE_CNT        NUMBER,
  OTS_PAID_AMOUNT           NUMBER,
  PTS_PAID_LOTTERY_CNT      NUMBER,
  PTS_PAID_STAKE_CNT        NUMBER,
  PTS_PAID_AMOUNT           NUMBER,
  BRANCH_PAID_LOTTERY_CNT   NUMBER,
  BRANCH_PAID_STAKE_CNT     NUMBER,
  BRANCH_PAID_AMOUNT        NUMBER,
  VOID_LOTTERY_CNT          NUMBER,
  VOID_STAKE_CNT            NUMBER,
  VOID_AMOUNT               NUMBER,
  STS_REFUND_LOTTERY_CNT    NUMBER,
  STS_REFUND_STAKE_CNT      NUMBER,
  STS_REFUND_AMOUNT         NUMBER,
  STO_REFUND_LOTTERY_CNT    NUMBER,
  STO_REFUND_STAKE_CNT      NUMBER,
  STO_REFUND_AMOUNT         NUMBER,
  OTS_REFUND_LOTTERY_CNT    NUMBER,
  OTS_REFUND_STAKE_CNT      NUMBER,
  OTS_REFUND_AMOUNT         NUMBER,
  PTS_REFUND_LOTTERY_CNT    NUMBER,
  PTS_REFUND_STAKE_CNT      NUMBER,
  PTS_REFUND_AMOUNT         NUMBER,
  BRANCH_REFUND_LOTTERY_CNT NUMBER,
  BRANCH_REFUND_STAKE_CNT   NUMBER,
  BRANCH_REFUND_AMOUNT      NUMBER,
  UNREFUND_LOTTERY_CNT      NUMBER,
  UNREFUND_STAKE_CNT        NUMBER,
  UNREFUND_AMOUNT           NUMBER,
  TAX_AMOUNT                NUMBER
)
partition by range (REPORT_DATE)
(
  partition P201608 values less than (TO_DATE(' 2016-09-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace TS_R2016
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
  partition P201609 values less than (TO_DATE(' 2016-10-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace TS_R2016
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
  partition P201610 values less than (TO_DATE(' 2016-11-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace TS_R2016
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
  partition P201611 values less than (TO_DATE(' 2016-12-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace TS_R2016
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
   partition P201612 values less than (TO_DATE(' 2017-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace TS_R2016
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
    partition P201701 values less than (TO_DATE(' 2017-02-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace TS_R2017
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
    partition P201702 values less than (TO_DATE(' 2017-03-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace TS_R2017
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
   partition P201703 values less than (TO_DATE(' 2017-04-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace TS_R2017
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
   partition P201704 values less than (TO_DATE(' 2017-05-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace TS_R2017
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
   partition P201705 values less than (TO_DATE(' 2017-06-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace TS_R2017
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
   partition P201706 values less than (TO_DATE(' 2017-07-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace TS_R2017
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
   partition P201707 values less than (TO_DATE(' 2017-08-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace TS_R2017
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
   partition P201708 values less than (TO_DATE(' 2017-09-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace TS_R2017
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
   partition P201709 values less than (TO_DATE(' 2017-10-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace TS_R2017
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
   partition P201710 values less than (TO_DATE(' 2017-11-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace TS_R2017
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
   partition P201711 values less than (TO_DATE(' 2017-12-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace TS_R2017
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
   partition P201712 values less than (TO_DATE(' 2018-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace TS_R2017
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

ALTER TABLE r_base COMPRESS FOR OLTP;

-- Add comments to the table 
comment on table R_BASE
  is '基础统计表VER=32.1#';
-- Add comments to the columns 
comment on column R_BASE.REPORT_DATE
  is '统计日期（到天）';
comment on column R_BASE.FINANCE_DATE
  is '业务统计日（到天）';
comment on column R_BASE.PROVINCE_CENTER_ID
  is '省中心ID';
comment on column R_BASE.TERMINAL_ID
  is '客户端ID';
comment on column R_BASE.DRAW_ID
  is '奖期ID';
comment on column R_BASE.BRANCH_ID
  is '兑奖处ID';
comment on column R_BASE.SHOP_ID
  is '门店ID';
comment on column R_BASE.CITY_CENTER_ID
  is '市级分中心ID';
comment on column R_BASE.LOGON_ID
  is '终端设备登录账号ID';
comment on column R_BASE.ACCOUNT_ID
  is '帐户ID';
comment on column R_BASE.GAME_ID
  is '游戏ID';
comment on column R_BASE.TECH_SYSTEM_ID
  is '技术系统ID';
comment on column R_BASE.CHANNEL_TYPE_CODE
  is '投注渠道类型（已配值域）';
comment on column R_BASE.LAST_UPDATE_TIME
  is '最后更新时间';
comment on column R_BASE.COUNTY_CENTER_ID
  is '县级分中心ID';
comment on column R_BASE.SALE_LOTTERY_CNT
  is '销售票数';
comment on column R_BASE.SALE_STAKE_CNT
  is '销售注数';
comment on column R_BASE.SALE_AMOUNT
  is '销售金额';
comment on column R_BASE.SALE_LOTTERY_CNT_SETTLED
  is '已结销售票数';
comment on column R_BASE.SALE_STAKE_CNT_SETTLED
  is '已结销售注数';
comment on column R_BASE.SALE_AMOUNT_SETTLED
  is '已结销售金额';
comment on column R_BASE.SALE_LOTTERY_CNT_MULTI
  is '多期后续销售票数';
comment on column R_BASE.SALE_STAKE_CNT_MULTI
  is '多期后续销售注数';
comment on column R_BASE.SALE_AMOUNT_MULTI
  is '多期后续销售金额';
comment on column R_BASE.CANCEL_LOTTERY_CNT
  is '取消票数';
comment on column R_BASE.CANCEL_STAKE_CNT
  is '取消注数';
comment on column R_BASE.CANCEL_AMOUNT
  is '取消金额';
comment on column R_BASE.PROV_CANCEL_LOTTERY_CNT
  is '省中心取消票数';
comment on column R_BASE.PROV_CANCEL_STAKE_CNT
  is '省中心取消注数';
comment on column R_BASE.PROV_CANCEL_AMOUNT
  is '省中心取消金额';
comment on column R_BASE.BRANCH_CANCEL_LOTTERY_CNT
  is '兑奖处取消票数';
comment on column R_BASE.BRANCH_CANCEL_STAKE_CNT
  is '兑奖处取消注数';
comment on column R_BASE.BRANCH_CANCEL_AMOUNT
  is '兑奖处取消金额';
comment on column R_BASE.STS_PAID_LOTTERY_CNT
  is '本销售点自兑奖票数';
comment on column R_BASE.STS_PAID_STAKE_CNT
  is '本销售点自兑奖注数';
comment on column R_BASE.STS_PAID_AMOUNT
  is '本销售点自兑奖金额';
comment on column R_BASE.STO_PAID_LOTTERY_CNT
  is '本销售点兑其他销售点中奖票数';
comment on column R_BASE.STO_PAID_STAKE_CNT
  is '本销售点兑其他销售点中奖注数';
comment on column R_BASE.STO_PAID_AMOUNT
  is '本销售点兑其他销售点中奖金额';
comment on column R_BASE.OTS_PAID_LOTTERY_CNT
  is '其他销售点兑本销售点中奖票数';
comment on column R_BASE.OTS_PAID_STAKE_CNT
  is '其他销售点兑本销售点中奖注数';
comment on column R_BASE.OTS_PAID_AMOUNT
  is '其他销售点兑本销售点中奖金额';
comment on column R_BASE.PTS_PAID_LOTTERY_CNT
  is '省中心兑本销售点中奖票数';
comment on column R_BASE.PTS_PAID_STAKE_CNT
  is '省中心兑本销售点中奖注数';
comment on column R_BASE.PTS_PAID_AMOUNT
  is '省中心兑本销售点中奖金额';
comment on column R_BASE.BRANCH_PAID_LOTTERY_CNT
  is '兑奖处兑奖票数';
comment on column R_BASE.BRANCH_PAID_STAKE_CNT
  is '兑奖处兑奖注数';
comment on column R_BASE.BRANCH_PAID_AMOUNT
  is '兑奖处兑奖金额';
comment on column R_BASE.VOID_LOTTERY_CNT
  is '应退票数';
comment on column R_BASE.VOID_STAKE_CNT
  is '应退注数';
comment on column R_BASE.VOID_AMOUNT
  is '应退金额';
comment on column R_BASE.STS_REFUND_LOTTERY_CNT
  is '本销售点自退票数';
comment on column R_BASE.STS_REFUND_STAKE_CNT
  is '本销售点自退票注数';
comment on column R_BASE.STS_REFUND_AMOUNT
  is '本销售点自退票金额';
comment on column R_BASE.STO_REFUND_LOTTERY_CNT
  is '本销售点退其他销售点票数';
comment on column R_BASE.STO_REFUND_STAKE_CNT
  is '本销售点退其他销售点注数';
comment on column R_BASE.STO_REFUND_AMOUNT
  is '本销售点退其他销售点金额';
comment on column R_BASE.OTS_REFUND_LOTTERY_CNT
  is '其他销售点退本销售点票数';
comment on column R_BASE.OTS_REFUND_STAKE_CNT
  is '其他销售点退本销售点注数';
comment on column R_BASE.OTS_REFUND_AMOUNT
  is '其他销售点退本销售点金额';
comment on column R_BASE.PTS_REFUND_LOTTERY_CNT
  is '省中心退本销售点票数';
comment on column R_BASE.PTS_REFUND_STAKE_CNT
  is '省中心退本销售点注数';
comment on column R_BASE.PTS_REFUND_AMOUNT
  is '省中心退本销售点金额';
comment on column R_BASE.BRANCH_REFUND_LOTTERY_CNT
  is '兑奖处退票票数';
comment on column R_BASE.BRANCH_REFUND_STAKE_CNT
  is '兑奖处退票注数';
comment on column R_BASE.BRANCH_REFUND_AMOUNT
  is '兑奖处退票金额';
comment on column R_BASE.UNREFUND_LOTTERY_CNT
  is '逾期未退票票数';
comment on column R_BASE.UNREFUND_STAKE_CNT
  is '逾期未退票注数';
comment on column R_BASE.UNREFUND_AMOUNT
  is '逾期未退票金额';
comment on column R_BASE.TAX_AMOUNT
  is '扣税金额';
-- Create/Recreate primary, unique and foreign key constraints 
alter table R_BASE
  add constraint PK_R_BASE primary key (REPORT_DATE, FINANCE_DATE, PROVINCE_CENTER_ID, TERMINAL_ID, DRAW_ID, BRANCH_ID, SHOP_ID, CITY_CENTER_ID, LOGON_ID, ACCOUNT_ID);
-- Create/Recreate indexes 
create index IND_R_BASE_NORMAL on R_BASE (REPORT_DATE, PROVINCE_CENTER_ID, GAME_ID, SHOP_ID, TERMINAL_ID, CITY_CENTER_ID, COUNTY_CENTER_ID);
create index IND_R_BASE_PCID on R_BASE (PROVINCE_CENTER_ID);