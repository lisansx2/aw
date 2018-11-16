drop table LOG_OPERATION_AW;


-- Create table
create table LOG_OPERATION_AW
(
  LOG_ID             NUMBER not null,
  LOG_TYPE_CODE      NUMBER,
  LOG_TIME           DATE not null,
  REQUEST_URL        VARCHAR2(4000) not null,
  REQUEST_PARAM      VARCHAR2(4000) not null,
  LOG_DESC           VARCHAR2(4000) not null,
  RETURN_NO          VARCHAR2(20) not null,
  USER_ID            NUMBER,
  USER_NAME          VARCHAR2(45),
  USER_IP            VARCHAR2(200),
  SERVER_IP          VARCHAR2(200),
  PROVINCE_CENTER_ID VARCHAR2(45) not null,
  COST_TIME NUMBER
)
tablespace TS_OTHERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 80K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns
comment on column LOG_OPERATION_AW.LOG_TYPE_CODE
  is '日志类型（已配值域）';
comment on column LOG_OPERATION_AW.LOG_TIME
  is '记录时间';
comment on column LOG_OPERATION_AW.REQUEST_URL
  is '请求的url';
comment on column LOG_OPERATION_AW.REQUEST_PARAM
  is '请求的参数';
comment on column LOG_OPERATION_AW.LOG_DESC
  is '描述';
comment on column LOG_OPERATION_AW.RETURN_NO
  is '返回码';
comment on column LOG_OPERATION_AW.USER_ID
  is '用户ID';
comment on column LOG_OPERATION_AW.USER_NAME
  is '用户名';
comment on column LOG_OPERATION_AW.USER_IP
  is '用户登录客户端IP';
comment on column LOG_OPERATION_AW.SERVER_IP
  is '服务器的IP';
comment on column LOG_OPERATION_AW.PROVINCE_CENTER_ID
  is '用户所属省中心id';
comment on column LOG_OPERATION_AW.COST_TIME
  is '耗时 ms';
-- Create/Recreate indexes
create index IDX_LOG_OPERATION_AW on LOG_OPERATION_AW (LOG_TIME, USER_ID)
  tablespace TS_OTHERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 80K
    next 1M
    minextents 1
    maxextents unlimited
  );

commit;