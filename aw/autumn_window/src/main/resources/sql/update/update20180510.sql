--1、删除原有表结构


--2、新建的表结构

-- Create table
create table RC_SHOP_JC_DAILY
(
  PROVINCE_CENTER_ID         NUMBER not null,
  SHOP_ID                    NUMBER not null,
  REPORT_DATE                           DATE,
  VOID_LOTTERY_CNT                    NUMBER,
  VOID_AMOUNT                         NUMBER,
  PAID_LOTTERY_CNT                    NUMBER,
  PAID_AMOUNT                          NUMBER,
  PAID_AMOUNT_ROUND                    NUMBER,
  PAID_AMOUNT_ABANDON                  NUMBER,
  PAID_AMOUNT_CARRY                    NUMBER
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
comment on table RC_SHOP_JC_DAILY
  is '门店竞彩销售表#';
-- Add comments to the columns 
comment on column RC_SHOP_JC_DAILY.PROVINCE_CENTER_ID
  is '省中心ID';
comment on column RC_SHOP_JC_DAILY.SHOP_ID
  is '门店ID';
comment on column RC_SHOP_JC_DAILY.REPORT_DATE
  is '兑奖日期';
comment on column RC_SHOP_JC_DAILY.VOID_LOTTERY_CNT
  is '实退票数';
comment on column RC_SHOP_JC_DAILY.VOID_AMOUNT
  is '实体金额';
comment on column RC_SHOP_JC_DAILY.PAID_LOTTERY_CNT
  is '兑奖票数';
comment on column RC_SHOP_JC_DAILY.PAID_AMOUNT
  is '兑奖金额';
comment on column RC_SHOP_JC_DAILY.PAID_AMOUNT_ROUND
  is '四舍五入（保留1位小数，小数点第2位四舍五入)计算后兑奖金额';
comment on column RC_SHOP_JC_DAILY.PAID_AMOUNT_ABANDON
  is '舍尾取整（保留1位小数，小数点第2位舍尾取整）计算后兑奖金额';
comment on column RC_SHOP_JC_DAILY.PAID_AMOUNT_CARRY
  is '进位取整（保留1位小数，小数点第2位进位取整）计算后兑奖金额';
-- Create/Recreate primary, unique and foreign key constraints 
alter table RC_SHOP_JC_DAILY
  add constraint PK_RC_SHOP_JC_DAILY primary key (PROVINCE_CENTER_ID,SHOP_ID,REPORT_DATE)
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




--3、初始化数据


--竞彩兑奖报表

commit;