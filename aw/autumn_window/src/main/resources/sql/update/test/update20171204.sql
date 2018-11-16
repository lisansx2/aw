--创建新增golden gate同步表结构
drop table INSTANT_RP_BIZ_D_S_2P;
  CREATE TABLE "INSTANT_RP_BIZ_D_S_2P" 
   (	"BIZ_YEAR" NUMBER(8,0) DEFAULT 0, 
	"BIZ_MONTH" NUMBER(8,0) DEFAULT 0, 
	"BIZ_WEEK" NUMBER(8,0) DEFAULT 0, 
	"BIZ_DATE" DATE, 
	"F_ACTIVATE_DATE_P" DATE, 
	"WEEKNUM_P" NUMBER(8,0) DEFAULT 0, 
	"F_ACTIVATE_DATE_CITY" DATE, 
	"WEEKNUM_CITY" NUMBER(8,0) DEFAULT 0, 
	"F_ACTIVATE_DATE_COUNTY" DATE, 
	"WEEKNUM_COUNTY" NUMBER(8,0) DEFAULT 0, 
	"F_ACTIVATE_DATE_SHOP" DATE, 
	"WEEKNUM_SHOP" NUMBER(8,0) DEFAULT 0, 
	"CHANNEL_CUSTOMER_ID" NUMBER(18,0) DEFAULT -1, 
	"CHANNEL_CUSTOMER_CODE" VARCHAR2(32), 
	"CHANNEL_CUSTOMER_NAME" VARCHAR2(100), 
	"SHOP_ID" NUMBER(18,0) DEFAULT -1, 
	"SHOP_CODE" VARCHAR2(32), 
	"SHOP_NAME" VARCHAR2(100), 
	"SHOP_TYPE_CODE" VARCHAR2(2), 
	"SHOP_TYPE_NAME" VARCHAR2(100), 
	"SR_ID" NUMBER(18,0) DEFAULT -1, 
	"SR_CODE" VARCHAR2(32), 
	"SR_NAME" VARCHAR2(100), 
	"TF_SR_A_ID" NUMBER(18,0) DEFAULT -1, 
	"TF_SR_A_CODE" VARCHAR2(32), 
	"TF_SR_A_NAME" VARCHAR2(100), 
	"TF_SR_B_ID" NUMBER(18,0) DEFAULT -1, 
	"TF_SR_B_CODE" VARCHAR2(32), 
	"TF_SR_B_NAME" VARCHAR2(100), 
	"TF_SR_C_ID" NUMBER(18,0) DEFAULT -1, 
	"TF_SR_C_CODE" VARCHAR2(32), 
	"TF_SR_C_NAME" VARCHAR2(100), 
	"CENTER_P_ID" NUMBER(18,0) DEFAULT -1, 
	"CENTER_P_CODE" VARCHAR2(32), 
	"CENTER_P_NAME" VARCHAR2(100), 
	"CENTER_CITY_ID" NUMBER(18,0) DEFAULT -1, 
	"CENTER_CITY_CODE" VARCHAR2(32), 
	"CENTER_CITY_NAME" VARCHAR2(100), 
	"CENTER_COUNTY_ID" NUMBER(18,0) DEFAULT -1, 
	"CENTER_COUNTY_CODE" VARCHAR2(32), 
	"CENTER_COUNTY_NAME" VARCHAR2(100), 
	"DISTRICT_P_ID" NUMBER(18,0) DEFAULT -1, 
	"DISTRICT_P_CODE" VARCHAR2(32), 
	"DISTRICT_P_NAME" VARCHAR2(100), 
	"DISTRICT_CITY_ID" NUMBER(18,0) DEFAULT -1, 
	"DISTRICT_CITY_CODE" VARCHAR2(32), 
	"DISTRICT_CITY_NAME" VARCHAR2(100), 
	"DISTRICT_COUNTY_ID" NUMBER(18,0) DEFAULT -1, 
	"DISTRICT_COUNTY_CODE" VARCHAR2(32), 
	"DISTRICT_COUNTY_NAME" VARCHAR2(100), 
	"GAME_ID" NUMBER(18,0) DEFAULT -1, 
	"GAME_CODE" VARCHAR2(32), 
	"GAME_NAME" VARCHAR2(100), 
	"PRICE" NUMBER(22,6) DEFAULT 0, 
	"ACTIVATE_PKG_AMT" NUMBER(8,0) DEFAULT 0, 
	"ACTIVATE_PR" NUMBER(22,6) DEFAULT 0, 
	"CFM_PKG_AMT" NUMBER(8,0) DEFAULT 0, 
	"CFM_BOX_AMT" NUMBER(8,0) DEFAULT 0, 
	"CFM_PR" NUMBER(22,6) DEFAULT 0, 
	"CLAIM_TICKET_AMT" NUMBER(8,0) DEFAULT 0, 
	"CLAIM_PR" NUMBER(22,6) DEFAULT 0, 
	"ACTIVITY_FLAG" CHAR(1), 
	"WMS_PKG_AMT" NUMBER(8,0) DEFAULT 0, 
	"WMS_BOX_AMT" NUMBER(8,0) DEFAULT 0, 
	"WMS_PR" NUMBER(22,6) DEFAULT 0, 
	"R_WMS_PKG_AMT" NUMBER(8,0) DEFAULT 0, 
	"R_WMS_BOX_AMT" NUMBER(8,0) DEFAULT 0, 
	"R_WMS_PR" NUMBER(22,6) DEFAULT 0, 
	"TF_PKG_AMT" NUMBER(8,0) DEFAULT 0, 
	"TF_BOX_AMT" NUMBER(8,0) DEFAULT 0, 
	"TF_PR" NUMBER(22,6) DEFAULT 0, 
	"R_SR_PKG_AMT" NUMBER(8,0) DEFAULT 0, 
	"R_SR_BOX_AMT" NUMBER(8,0) DEFAULT 0, 
	"R_SR_PR" NUMBER(22,6) DEFAULT 0, 
	"R_WM_PKG_AMT" NUMBER(8,0) DEFAULT 0, 
	"R_WM_BOX_AMT" NUMBER(8,0) DEFAULT 0, 
	"R_WM_PR" NUMBER(22,6) DEFAULT 0, 
	"COMM_SALE" NUMBER(22,6) DEFAULT 0, 
	"COMM_MNG" NUMBER(22,6) DEFAULT 0, 
	"COMM_CLAIM" NUMBER(22,6) DEFAULT 0, 
	"CREATED_DATE" DATE, 
	"REMARK" VARCHAR2(255), 
	"ACTIVATE_UNSALE_AMT" NUMBER(8,0) DEFAULT 0, 
	"ACTIVATE_UNSALE_PR" NUMBER(22,6) DEFAULT 0, 
	"MACHINE_AMT" NUMBER(8,0) DEFAULT 0, 
	"ORDER_BOX_AMT" NUMBER(8,0) DEFAULT 0, 
	"ORDER_PKG_AMT" NUMBER(8,0) DEFAULT 0
   ) ;

   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."BIZ_YEAR" IS '年';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."BIZ_MONTH" IS '月';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."BIZ_WEEK" IS '周';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."BIZ_DATE" IS '日';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."F_ACTIVATE_DATE_P" IS '第一包票的激活时间（省）';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."WEEKNUM_P" IS '销量周（省）';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."F_ACTIVATE_DATE_CITY" IS '第一包票的激活时间（市）';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."WEEKNUM_CITY" IS '销量周（市）';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."F_ACTIVATE_DATE_COUNTY" IS '第一包票的激活时间（县）';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."WEEKNUM_COUNTY" IS '销量周（县）';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."F_ACTIVATE_DATE_SHOP" IS '第一包票的激活时间（门店）';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."WEEKNUM_SHOP" IS '销量周（门店）';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."CHANNEL_CUSTOMER_ID" IS '所属大客户ID';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."CHANNEL_CUSTOMER_CODE" IS '所属大客户CODE';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."CHANNEL_CUSTOMER_NAME" IS '所属大客户NAME';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."SHOP_ID" IS '门店ID';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."SHOP_CODE" IS '门店编号';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."SHOP_NAME" IS '门店NAME';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."SHOP_TYPE_CODE" IS '类型';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."SHOP_TYPE_NAME" IS '类型描述';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."SR_ID" IS '管理SRID';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."SR_CODE" IS '管理SRCODE';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."SR_NAME" IS '管理SRNAME';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."TF_SR_A_ID" IS '转移SR1-ID';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."TF_SR_A_CODE" IS '转移SR1-CODE';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."TF_SR_A_NAME" IS '转移SR1-NAME';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."TF_SR_B_ID" IS '转移SR2-ID';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."TF_SR_B_CODE" IS '转移SR2-CODE';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."TF_SR_B_NAME" IS '转移SR2-NAME';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."TF_SR_C_ID" IS '转移SR3-ID';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."TF_SR_C_CODE" IS '转移SR3-CODE';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."TF_SR_C_NAME" IS '转移SR3-NAME';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."CENTER_P_ID" IS '省中心（管理）ID';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."CENTER_P_CODE" IS '省中心（管理）CODE';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."CENTER_P_NAME" IS '省中心（管理）';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."CENTER_CITY_ID" IS '市中心（管理）ID';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."CENTER_CITY_CODE" IS '市中心（管理）CODE';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."CENTER_CITY_NAME" IS '市中心（管理）';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."CENTER_COUNTY_ID" IS '县中心（管理）ID';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."CENTER_COUNTY_CODE" IS '县中心（管理）CODE';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."CENTER_COUNTY_NAME" IS '县中心（管理）';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."DISTRICT_P_ID" IS '省（行政）ID';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."DISTRICT_P_CODE" IS '省（行政）CODE';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."DISTRICT_P_NAME" IS '省（行政）';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."DISTRICT_CITY_ID" IS '市（行政）ID';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."DISTRICT_CITY_CODE" IS '市（行政）CODE';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."DISTRICT_CITY_NAME" IS '市（行政）';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."DISTRICT_COUNTY_ID" IS '县（行政）ID';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."DISTRICT_COUNTY_CODE" IS '县（行政）CODE';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."DISTRICT_COUNTY_NAME" IS '县（行政）';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."GAME_ID" IS '游戏ID';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."GAME_CODE" IS '游戏号';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."GAME_NAME" IS '游戏名称';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."PRICE" IS '面值';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."ACTIVATE_PKG_AMT" IS '激活包数';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."ACTIVATE_PR" IS '激活金额';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."CFM_PKG_AMT" IS '确认包数';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."CFM_BOX_AMT" IS '确认箱数';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."CFM_PR" IS '确认金额';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."CLAIM_TICKET_AMT" IS '兑奖张数';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."CLAIM_PR" IS '兑奖金额';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."ACTIVITY_FLAG" IS '当日是否活动';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."WMS_PKG_AMT" IS '仓库领票包数（仓库到SR/仓库到门店）';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."WMS_BOX_AMT" IS '仓库领票箱数（仓库到SR/仓库到门店）';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."WMS_PR" IS '仓库领票金额（仓库到SR/仓库到门店）';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."R_WMS_PKG_AMT" IS '仓库退票包数（SR到仓库/门店到仓库）';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."R_WMS_BOX_AMT" IS '仓库退票箱数（SR到仓库/门店到仓库）';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."R_WMS_PR" IS '仓库退票金额（SR到仓库/门店到仓库）';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."TF_PKG_AMT" IS '转移包数（SR到门店）';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."TF_BOX_AMT" IS '转移箱数（SR到门店）';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."TF_PR" IS '转移金额（SR到门店）';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."R_SR_PKG_AMT" IS '门店退票到SR包数';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."R_SR_BOX_AMT" IS '门店退票到SR箱数';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."R_SR_PR" IS '门店退票到SR金额';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."R_WM_PKG_AMT" IS '门店退票到仓库包数';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."R_WM_BOX_AMT" IS '门店退票到仓库箱数';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."R_WM_PR" IS '门店退票到仓库金额';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."COMM_SALE" IS '销售佣金';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."COMM_MNG" IS '管理佣金';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."COMM_CLAIM" IS '兑奖佣金';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."CREATED_DATE" IS '记录创建时间';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."REMARK" IS '备注';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."ACTIVATE_UNSALE_AMT" IS '激活未售张数';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."ACTIVATE_UNSALE_PR" IS '激活未售金额';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."MACHINE_AMT" IS '设备数量';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."ORDER_BOX_AMT" IS '订包数量箱';
   COMMENT ON COLUMN "INSTANT_RP_BIZ_D_S_2P"."ORDER_PKG_AMT" IS '订单数量包';
   COMMENT ON TABLE "INSTANT_RP_BIZ_D_S_2P"  IS '门店/SR业务日结模型(周/月/年)';
REM INSERTING into INSTANT_RP_BIZ_D_S_2P
SET DEFINE OFF;
Insert into INSTANT_RP_BIZ_D_S_2P (BIZ_YEAR,BIZ_MONTH,BIZ_WEEK,BIZ_DATE,F_ACTIVATE_DATE_P,WEEKNUM_P,F_ACTIVATE_DATE_CITY,WEEKNUM_CITY,F_ACTIVATE_DATE_COUNTY,WEEKNUM_COUNTY,F_ACTIVATE_DATE_SHOP,WEEKNUM_SHOP,CHANNEL_CUSTOMER_ID,CHANNEL_CUSTOMER_CODE,CHANNEL_CUSTOMER_NAME,SHOP_ID,SHOP_CODE,SHOP_NAME,SHOP_TYPE_CODE,SHOP_TYPE_NAME,SR_ID,SR_CODE,SR_NAME,TF_SR_A_ID,TF_SR_A_CODE,TF_SR_A_NAME,TF_SR_B_ID,TF_SR_B_CODE,TF_SR_B_NAME,TF_SR_C_ID,TF_SR_C_CODE,TF_SR_C_NAME,CENTER_P_ID,CENTER_P_CODE,CENTER_P_NAME,CENTER_CITY_ID,CENTER_CITY_CODE,CENTER_CITY_NAME,CENTER_COUNTY_ID,CENTER_COUNTY_CODE,CENTER_COUNTY_NAME,DISTRICT_P_ID,DISTRICT_P_CODE,DISTRICT_P_NAME,DISTRICT_CITY_ID,DISTRICT_CITY_CODE,DISTRICT_CITY_NAME,DISTRICT_COUNTY_ID,DISTRICT_COUNTY_CODE,DISTRICT_COUNTY_NAME,GAME_ID,GAME_CODE,GAME_NAME,PRICE,ACTIVATE_PKG_AMT,ACTIVATE_PR,CFM_PKG_AMT,CFM_BOX_AMT,CFM_PR,CLAIM_TICKET_AMT,CLAIM_PR,ACTIVITY_FLAG,WMS_PKG_AMT,WMS_BOX_AMT,WMS_PR,R_WMS_PKG_AMT,R_WMS_BOX_AMT,R_WMS_PR,TF_PKG_AMT,TF_BOX_AMT,TF_PR,R_SR_PKG_AMT,R_SR_BOX_AMT,R_SR_PR,R_WM_PKG_AMT,R_WM_BOX_AMT,R_WM_PR,COMM_SALE,COMM_MNG,COMM_CLAIM,CREATED_DATE,REMARK,ACTIVATE_UNSALE_AMT,ACTIVATE_UNSALE_PR,MACHINE_AMT,ORDER_BOX_AMT,ORDER_PKG_AMT) values (2017,8,33,to_date('20-8月 -17','DD-MON-RR'),null,0,null,0,null,0,null,0,-1,null,null,144410460,'5201000086','师1UAT','1','门店',-1,null,null,null,null,null,null,null,null,null,null,null,52,'52','贵州',15,'5201','贵阳',-1,null,null,-1,null,null,520160,null,null,-1,null,null,300000000000051273,'9104','NBA',10,0,0,0,0,0,0,0,null,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,null,null,0,0,0,0,0);
Insert into INSTANT_RP_BIZ_D_S_2P (BIZ_YEAR,BIZ_MONTH,BIZ_WEEK,BIZ_DATE,F_ACTIVATE_DATE_P,WEEKNUM_P,F_ACTIVATE_DATE_CITY,WEEKNUM_CITY,F_ACTIVATE_DATE_COUNTY,WEEKNUM_COUNTY,F_ACTIVATE_DATE_SHOP,WEEKNUM_SHOP,CHANNEL_CUSTOMER_ID,CHANNEL_CUSTOMER_CODE,CHANNEL_CUSTOMER_NAME,SHOP_ID,SHOP_CODE,SHOP_NAME,SHOP_TYPE_CODE,SHOP_TYPE_NAME,SR_ID,SR_CODE,SR_NAME,TF_SR_A_ID,TF_SR_A_CODE,TF_SR_A_NAME,TF_SR_B_ID,TF_SR_B_CODE,TF_SR_B_NAME,TF_SR_C_ID,TF_SR_C_CODE,TF_SR_C_NAME,CENTER_P_ID,CENTER_P_CODE,CENTER_P_NAME,CENTER_CITY_ID,CENTER_CITY_CODE,CENTER_CITY_NAME,CENTER_COUNTY_ID,CENTER_COUNTY_CODE,CENTER_COUNTY_NAME,DISTRICT_P_ID,DISTRICT_P_CODE,DISTRICT_P_NAME,DISTRICT_CITY_ID,DISTRICT_CITY_CODE,DISTRICT_CITY_NAME,DISTRICT_COUNTY_ID,DISTRICT_COUNTY_CODE,DISTRICT_COUNTY_NAME,GAME_ID,GAME_CODE,GAME_NAME,PRICE,ACTIVATE_PKG_AMT,ACTIVATE_PR,CFM_PKG_AMT,CFM_BOX_AMT,CFM_PR,CLAIM_TICKET_AMT,CLAIM_PR,ACTIVITY_FLAG,WMS_PKG_AMT,WMS_BOX_AMT,WMS_PR,R_WMS_PKG_AMT,R_WMS_BOX_AMT,R_WMS_PR,TF_PKG_AMT,TF_BOX_AMT,TF_PR,R_SR_PKG_AMT,R_SR_BOX_AMT,R_SR_PR,R_WM_PKG_AMT,R_WM_BOX_AMT,R_WM_PR,COMM_SALE,COMM_MNG,COMM_CLAIM,CREATED_DATE,REMARK,ACTIVATE_UNSALE_AMT,ACTIVATE_UNSALE_PR,MACHINE_AMT,ORDER_BOX_AMT,ORDER_PKG_AMT) values (2017,6,24,to_date('12-6月 -17','DD-MON-RR'),to_date('12-6月 -17','DD-MON-RR'),0,to_date('12-6月 -17','DD-MON-RR'),0,null,0,null,0,-1,null,null,73912460,'1101000005','123','1','门店',null,null,null,null,null,null,null,null,null,null,null,null,11,'11','北京',10,'1101','东城',null,null,null,-1,null,null,110160,null,null,null,null,null,300000000000003000,'9104','NBA1',10,1,500,1,0,500,0,0,null,1,0,500,0,0,0,0,0,0,0,0,0,0,0,0,25,0,0.5,to_date('12-6月 -17','DD-MON-RR'),null,2598,20200,0,0,1);
Insert into INSTANT_RP_BIZ_D_S_2P (BIZ_YEAR,BIZ_MONTH,BIZ_WEEK,BIZ_DATE,F_ACTIVATE_DATE_P,WEEKNUM_P,F_ACTIVATE_DATE_CITY,WEEKNUM_CITY,F_ACTIVATE_DATE_COUNTY,WEEKNUM_COUNTY,F_ACTIVATE_DATE_SHOP,WEEKNUM_SHOP,CHANNEL_CUSTOMER_ID,CHANNEL_CUSTOMER_CODE,CHANNEL_CUSTOMER_NAME,SHOP_ID,SHOP_CODE,SHOP_NAME,SHOP_TYPE_CODE,SHOP_TYPE_NAME,SR_ID,SR_CODE,SR_NAME,TF_SR_A_ID,TF_SR_A_CODE,TF_SR_A_NAME,TF_SR_B_ID,TF_SR_B_CODE,TF_SR_B_NAME,TF_SR_C_ID,TF_SR_C_CODE,TF_SR_C_NAME,CENTER_P_ID,CENTER_P_CODE,CENTER_P_NAME,CENTER_CITY_ID,CENTER_CITY_CODE,CENTER_CITY_NAME,CENTER_COUNTY_ID,CENTER_COUNTY_CODE,CENTER_COUNTY_NAME,DISTRICT_P_ID,DISTRICT_P_CODE,DISTRICT_P_NAME,DISTRICT_CITY_ID,DISTRICT_CITY_CODE,DISTRICT_CITY_NAME,DISTRICT_COUNTY_ID,DISTRICT_COUNTY_CODE,DISTRICT_COUNTY_NAME,GAME_ID,GAME_CODE,GAME_NAME,PRICE,ACTIVATE_PKG_AMT,ACTIVATE_PR,CFM_PKG_AMT,CFM_BOX_AMT,CFM_PR,CLAIM_TICKET_AMT,CLAIM_PR,ACTIVITY_FLAG,WMS_PKG_AMT,WMS_BOX_AMT,WMS_PR,R_WMS_PKG_AMT,R_WMS_BOX_AMT,R_WMS_PR,TF_PKG_AMT,TF_BOX_AMT,TF_PR,R_SR_PKG_AMT,R_SR_BOX_AMT,R_SR_PR,R_WM_PKG_AMT,R_WM_BOX_AMT,R_WM_PR,COMM_SALE,COMM_MNG,COMM_CLAIM,CREATED_DATE,REMARK,ACTIVATE_UNSALE_AMT,ACTIVATE_UNSALE_PR,MACHINE_AMT,ORDER_BOX_AMT,ORDER_PKG_AMT) values (2017,6,24,to_date('12-6月 -17','DD-MON-RR'),to_date('12-6月 -17','DD-MON-RR'),0,to_date('12-6月 -17','DD-MON-RR'),0,null,0,null,0,-1,null,null,63410760,'1102000007','北单测试门店key10','1','门店',null,null,null,null,null,null,null,null,null,null,null,null,11,'11','北京',110,'1102','西城',-1,null,null,-1,null,null,110160,null,null,-1,null,null,300000000000003000,'9104','NBA1',10,49,24500,49,0,24500,2,5800,null,49,0,24500,0,0,0,0,0,0,0,0,0,0,0,0,1225,0,0.5,to_date('12-6月 -17','DD-MON-RR'),null,2598,20200,2,0,49);
Insert into INSTANT_RP_BIZ_D_S_2P (BIZ_YEAR,BIZ_MONTH,BIZ_WEEK,BIZ_DATE,F_ACTIVATE_DATE_P,WEEKNUM_P,F_ACTIVATE_DATE_CITY,WEEKNUM_CITY,F_ACTIVATE_DATE_COUNTY,WEEKNUM_COUNTY,F_ACTIVATE_DATE_SHOP,WEEKNUM_SHOP,CHANNEL_CUSTOMER_ID,CHANNEL_CUSTOMER_CODE,CHANNEL_CUSTOMER_NAME,SHOP_ID,SHOP_CODE,SHOP_NAME,SHOP_TYPE_CODE,SHOP_TYPE_NAME,SR_ID,SR_CODE,SR_NAME,TF_SR_A_ID,TF_SR_A_CODE,TF_SR_A_NAME,TF_SR_B_ID,TF_SR_B_CODE,TF_SR_B_NAME,TF_SR_C_ID,TF_SR_C_CODE,TF_SR_C_NAME,CENTER_P_ID,CENTER_P_CODE,CENTER_P_NAME,CENTER_CITY_ID,CENTER_CITY_CODE,CENTER_CITY_NAME,CENTER_COUNTY_ID,CENTER_COUNTY_CODE,CENTER_COUNTY_NAME,DISTRICT_P_ID,DISTRICT_P_CODE,DISTRICT_P_NAME,DISTRICT_CITY_ID,DISTRICT_CITY_CODE,DISTRICT_CITY_NAME,DISTRICT_COUNTY_ID,DISTRICT_COUNTY_CODE,DISTRICT_COUNTY_NAME,GAME_ID,GAME_CODE,GAME_NAME,PRICE,ACTIVATE_PKG_AMT,ACTIVATE_PR,CFM_PKG_AMT,CFM_BOX_AMT,CFM_PR,CLAIM_TICKET_AMT,CLAIM_PR,ACTIVITY_FLAG,WMS_PKG_AMT,WMS_BOX_AMT,WMS_PR,R_WMS_PKG_AMT,R_WMS_BOX_AMT,R_WMS_PR,TF_PKG_AMT,TF_BOX_AMT,TF_PR,R_SR_PKG_AMT,R_SR_BOX_AMT,R_SR_PR,R_WM_PKG_AMT,R_WM_BOX_AMT,R_WM_PR,COMM_SALE,COMM_MNG,COMM_CLAIM,CREATED_DATE,REMARK,ACTIVATE_UNSALE_AMT,ACTIVATE_UNSALE_PR,MACHINE_AMT,ORDER_BOX_AMT,ORDER_PKG_AMT) values (2017,6,24,to_date('12-6月 -17','DD-MON-RR'),to_date('12-6月 -17','DD-MON-RR'),0,to_date('12-6月 -17','DD-MON-RR'),0,null,0,null,0,-1,null,null,63310560,'1102000006','北单测试门店key9','1','门店',-1,null,null,null,null,null,null,null,null,null,null,null,11,'11','北京',110,'1102','西城',-1,null,null,-1,null,null,110160,null,null,11010260,null,null,300000000000003000,'9104','NBA1',10,2,1000,2,0,1000,0,0,null,2,0,1000,0,0,0,0,0,0,0,0,0,0,0,0,50,0,5,to_date('12-6月 -17','DD-MON-RR'),null,2598,20200,2,0,2);
Insert into INSTANT_RP_BIZ_D_S_2P (BIZ_YEAR,BIZ_MONTH,BIZ_WEEK,BIZ_DATE,F_ACTIVATE_DATE_P,WEEKNUM_P,F_ACTIVATE_DATE_CITY,WEEKNUM_CITY,F_ACTIVATE_DATE_COUNTY,WEEKNUM_COUNTY,F_ACTIVATE_DATE_SHOP,WEEKNUM_SHOP,CHANNEL_CUSTOMER_ID,CHANNEL_CUSTOMER_CODE,CHANNEL_CUSTOMER_NAME,SHOP_ID,SHOP_CODE,SHOP_NAME,SHOP_TYPE_CODE,SHOP_TYPE_NAME,SR_ID,SR_CODE,SR_NAME,TF_SR_A_ID,TF_SR_A_CODE,TF_SR_A_NAME,TF_SR_B_ID,TF_SR_B_CODE,TF_SR_B_NAME,TF_SR_C_ID,TF_SR_C_CODE,TF_SR_C_NAME,CENTER_P_ID,CENTER_P_CODE,CENTER_P_NAME,CENTER_CITY_ID,CENTER_CITY_CODE,CENTER_CITY_NAME,CENTER_COUNTY_ID,CENTER_COUNTY_CODE,CENTER_COUNTY_NAME,DISTRICT_P_ID,DISTRICT_P_CODE,DISTRICT_P_NAME,DISTRICT_CITY_ID,DISTRICT_CITY_CODE,DISTRICT_CITY_NAME,DISTRICT_COUNTY_ID,DISTRICT_COUNTY_CODE,DISTRICT_COUNTY_NAME,GAME_ID,GAME_CODE,GAME_NAME,PRICE,ACTIVATE_PKG_AMT,ACTIVATE_PR,CFM_PKG_AMT,CFM_BOX_AMT,CFM_PR,CLAIM_TICKET_AMT,CLAIM_PR,ACTIVITY_FLAG,WMS_PKG_AMT,WMS_BOX_AMT,WMS_PR,R_WMS_PKG_AMT,R_WMS_BOX_AMT,R_WMS_PR,TF_PKG_AMT,TF_BOX_AMT,TF_PR,R_SR_PKG_AMT,R_SR_BOX_AMT,R_SR_PR,R_WM_PKG_AMT,R_WM_BOX_AMT,R_WM_PR,COMM_SALE,COMM_MNG,COMM_CLAIM,CREATED_DATE,REMARK,ACTIVATE_UNSALE_AMT,ACTIVATE_UNSALE_PR,MACHINE_AMT,ORDER_BOX_AMT,ORDER_PKG_AMT) values (2017,6,24,to_date('12-6月 -17','DD-MON-RR'),null,0,null,0,null,0,null,0,-1,null,null,180110460,'5201091188','大是大非','1','门店',null,null,null,null,null,null,null,null,null,null,null,null,52,'52','贵州',15,'5201','贵阳',-1,null,null,-1,null,null,520160,null,null,-1,null,null,300000000059688000,'0312','TEN TIMES LUCKY',10,2,1200,2,0,1200,0,0,null,2,0,1200,0,0,0,0,0,0,0,0,0,0,0,0,60,0,0.5,to_date('12-6月 -17','DD-MON-RR'),null,120,1200,0,0,2);
--------------------------------------------------------
--  DDL for Index ID21X_RBDS_CITY
--------------------------------------------------------

  CREATE INDEX "ID21X_RBDS_CITY" ON "INSTANT_RP_BIZ_D_S_2P" ("BIZ_DATE", "CENTER_CITY_CODE", "GAME_CODE") 
  ;
--------------------------------------------------------
--  DDL for Index IDX21_RBDS_COUNTY
--------------------------------------------------------

  CREATE INDEX "IDX21_RBDS_COUNTY" ON "INSTANT_RP_BIZ_D_S_2P" ("BIZ_DATE", "CENTER_COUNTY_CODE", "GAME_CODE") 
  ;
--------------------------------------------------------
--  DDL for Index IDX21_RBDS_DATE
--------------------------------------------------------

  CREATE INDEX "IDX21_RBDS_DATE" ON "INSTANT_RP_BIZ_D_S_2P" ("BIZ_DATE", "GAME_CODE") 
  ;
--------------------------------------------------------
--  DDL for Index IDX21_RBDS_PROV
--------------------------------------------------------

  CREATE INDEX "IDX21_RBDS_PROV" ON "INSTANT_RP_BIZ_D_S_2P" ("BIZ_DATE", "CENTER_P_CODE", "GAME_CODE") 
  ;
--------------------------------------------------------
--  DDL for Index IDX21_RBDS_SHOP_ID
--------------------------------------------------------

  CREATE INDEX "IDX21_RBDS_SHOP_ID" ON "INSTANT_RP_BIZ_D_S_2P" ("BIZ_DATE", "SHOP_ID", "GAME_CODE") 
  ;
--------------------------------------------------------
--  Constraints for Table INSTANT_RP_BIZ_D_S_2P
--------------------------------------------------------

  ALTER TABLE "INSTANT_RP_BIZ_D_S_2P" MODIFY ("BIZ_YEAR" NOT NULL ENABLE);
  ALTER TABLE "INSTANT_RP_BIZ_D_S_2P" MODIFY ("BIZ_MONTH" NOT NULL ENABLE);
  ALTER TABLE "INSTANT_RP_BIZ_D_S_2P" MODIFY ("BIZ_WEEK" NOT NULL ENABLE);
  ALTER TABLE "INSTANT_RP_BIZ_D_S_2P" MODIFY ("BIZ_DATE" NOT NULL ENABLE);
  
  
 
 
 -- Create table
 drop table INSTANT_GM_GAME;
create table INSTANT_GM_GAME
(
  row_id               NUMBER(18) default -1 not null,
  game_code            VARCHAR2(10),
  game_name            VARCHAR2(100),
  game_bar_code        VARCHAR2(32),
  game_class_code      VARCHAR2(2),
  game_class_name      VARCHAR2(32),
  game_type_code       VARCHAR2(2),
  game_type_name       VARCHAR2(32),
  game_topic_code      VARCHAR2(2),
  game_topic_name      VARCHAR2(100),
  play_type_code       VARCHAR2(2),
  play_type_name       VARCHAR2(100),
  prize_level_fea_code VARCHAR2(2),
  prize_level_fea_name VARCHAR2(100),
  game_sup_id          NUMBER(18) default -1 not null,
  game_sup_code        VARCHAR2(32),
  game_sup_name        VARCHAR2(100),
  lottery_sup_id       NUMBER(18) default -1 not null,
  lottery_sup_code     VARCHAR2(32),
  lottery_sup_name     VARCHAR2(100),
  award_amt            VARCHAR2(100) not null,
  package_size         NUMBER(8) default 0 not null,
  box_size             NUMBER(8) default 0 not null,
  award_size           NUMBER(8) default 0 not null,
  package_weight       NUMBER(20,6) default 0 not null,
  price                NUMBER(22,6) default 0 not null,
  win_rate             NUMBER(10,6) default 0 not null,
  return_rate          NUMBER(10,6) default 0 not null,
  description          VARCHAR2(255),
  game_status_code     VARCHAR2(32),
  game_status_name     VARCHAR2(100),
  front_pic_id         NUMBER(18) default -1 not null,
  back_pic_id          NUMBER(18) default -1 not null,
  deleted_flag         CHAR(1) default '0' not null,
  origin_flag          CHAR(1),
  origin_app           VARCHAR2(32),
  created_user_name    VARCHAR2(32),
  created_org_id       NUMBER(18) default -1 not null,
  created_org_name     VARCHAR2(100),
  created_user_id      NUMBER(18) default -1 not null,
  created_date         DATE,
  last_upd_org_id      NUMBER(18) default -1 not null,
  last_upd_by          NUMBER(18) default -1 not null,
  last_upd_date        DATE,
  modification_num     NUMBER(8) default 0 not null,
  remark               VARCHAR2(255),
  package_amt          NUMBER(22,6) default 0 not null,
  front_minpic_id      NUMBER(18),
  back_minpic_id       NUMBER(18),
  poolsize             NUMBER(12) default 0,
  seed1                NUMBER(12) default 0,
  seed2                NUMBER(12) default 0,
  booksize             NUMBER(12) default 0,
  firstbooknumber      NUMBER(12) default 0
)
tablespace TS_OTHERS
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the columns 
comment on column INSTANT_GM_GAME.game_code
  is '游戏编码';
comment on column INSTANT_GM_GAME.game_name
  is '游戏名称';
comment on column INSTANT_GM_GAME.game_bar_code
  is '游戏商品条码';
comment on column INSTANT_GM_GAME.game_class_code
  is '游戏性质代码（现指发行范围） 【LOV_CODE：GM_GAME_CLASS】';
comment on column INSTANT_GM_GAME.game_class_name
  is '游戏性质名称（现指发行范围） 【LOV_CODE：GM_GAME_CLASS】';
comment on column INSTANT_GM_GAME.game_type_code
  is '游戏类型代码 【LOV_CODE：GM_GAME_TYPE】';
comment on column INSTANT_GM_GAME.game_type_name
  is '游戏类型名称 【LOV_CODE：GM_GAME_TYPE】';
comment on column INSTANT_GM_GAME.game_topic_code
  is '游戏主题代码 【LOV_CODE：GM_GAME_TOPIC】';
comment on column INSTANT_GM_GAME.game_topic_name
  is '游戏主题名称 【LOV_CODE：GM_GAME_TOPIC】';
comment on column INSTANT_GM_GAME.play_type_code
  is '玩法类型代码 【LOV_CODE：GM_PLAY_TYPE】';
comment on column INSTANT_GM_GAME.play_type_name
  is '玩法类型名称 【LOV_CODE：GM_PLAY_TYPE】';
comment on column INSTANT_GM_GAME.prize_level_fea_code
  is '奖级特点代码 【LOV_CODE：GM_PRIZE_LEVEL_FEA】';
comment on column INSTANT_GM_GAME.prize_level_fea_name
  is '奖级特点名称 【LOV_CODE：GM_PRIZE_LEVEL_FEA】';
comment on column INSTANT_GM_GAME.game_sup_id
  is '游戏供应商ID';
comment on column INSTANT_GM_GAME.game_sup_code
  is '游戏供应商编码';
comment on column INSTANT_GM_GAME.game_sup_name
  is '游戏供应商名称';
comment on column INSTANT_GM_GAME.lottery_sup_id
  is '彩票供应商ID 【保留字段】';
comment on column INSTANT_GM_GAME.lottery_sup_code
  is '彩票供应商编码 【保留字段】';
comment on column INSTANT_GM_GAME.lottery_sup_name
  is '彩票供应商名称 【保留字段】';
comment on column INSTANT_GM_GAME.award_amt
  is '奖组金额（奖组大小）';
comment on column INSTANT_GM_GAME.package_size
  is '包大小   张/包';
comment on column INSTANT_GM_GAME.box_size
  is '箱大小   包/箱';
comment on column INSTANT_GM_GAME.award_size
  is '奖组/箱换算';
comment on column INSTANT_GM_GAME.package_weight
  is '包重量。单位：KG';
comment on column INSTANT_GM_GAME.price
  is '面值。单位：元';
comment on column INSTANT_GM_GAME.win_rate
  is '中奖率';
comment on column INSTANT_GM_GAME.return_rate
  is '返奖率';
comment on column INSTANT_GM_GAME.description
  is '游戏说明';
comment on column INSTANT_GM_GAME.game_status_code
  is '游戏状态编码 【LOV_CODE：GM_STATUS_CODE】';
comment on column INSTANT_GM_GAME.game_status_name
  is '游戏状态名称 【LOV_CODE：GM_STATUS_CODE】';
comment on column INSTANT_GM_GAME.front_pic_id
  is '游戏图片ID（正面）。对应附件表ROW_ID';
comment on column INSTANT_GM_GAME.back_pic_id
  is '游戏图片ID（背面）。对应附件表ROW_ID';
comment on column INSTANT_GM_GAME.deleted_flag
  is '记录删除标志 [0]-未删除;[1]-逻辑删除';
comment on column INSTANT_GM_GAME.origin_flag
  is '数据来源的标志：[]或[I]-(Input)系统录入;[O]-(Out)外部接口导入;[S]-(System)系统保留。本标志不能挪为它用。';
comment on column INSTANT_GM_GAME.origin_app
  is '数据来源应用的代码';
comment on column INSTANT_GM_GAME.created_user_name
  is '记录创建人名称';
comment on column INSTANT_GM_GAME.created_org_id
  is '记录创建人所在业务单元';
comment on column INSTANT_GM_GAME.created_org_name
  is '记录创建人所在业务单元名称';
comment on column INSTANT_GM_GAME.created_user_id
  is '记录创建人ID';
comment on column INSTANT_GM_GAME.created_date
  is '记录创建时间';
comment on column INSTANT_GM_GAME.last_upd_org_id
  is '记录修改人所在业务单元';
comment on column INSTANT_GM_GAME.last_upd_by
  is '记录最近修改人ID';
comment on column INSTANT_GM_GAME.last_upd_date
  is '记录最近修改日期';
comment on column INSTANT_GM_GAME.modification_num
  is '记录修改次数';
comment on column INSTANT_GM_GAME.remark
  is '备注';
comment on column INSTANT_GM_GAME.poolsize
  is '每奖池的票数';
comment on column INSTANT_GM_GAME.seed1
  is '兑奖种子1';
comment on column INSTANT_GM_GAME.seed2
  is '兑奖种子2';
comment on column INSTANT_GM_GAME.booksize
  is '每奖池的包数';
comment on column INSTANT_GM_GAME.firstbooknumber
  is '起始包号';
-- Create/Recreate indexes 
create index INSTANT_IDX_GM_GAME_GCC on INSTANT_GM_GAME (GAME_CLASS_CODE)
  tablespace TS_OTHERS
  pctfree 10
  initrans 2
  maxtrans 255;
create index INSTANT_IDX_GM_GAME_GCODE on INSTANT_GM_GAME (GAME_CODE)
  tablespace TS_OTHERS
  pctfree 10
  initrans 2
  maxtrans 255;
create index INSTANT_IDX_GM_GAME_GSC on INSTANT_GM_GAME (GAME_STATUS_CODE)
  tablespace TS_OTHERS
  pctfree 10
  initrans 2
  maxtrans 255;
create index INSTANT_IDX_GM_GAME_GTC on INSTANT_GM_GAME (GAME_TYPE_CODE)
  tablespace TS_OTHERS
  pctfree 10
  initrans 2
  maxtrans 255;
-- Create/Recreate primary, unique and foreign key constraints 
alter table INSTANT_GM_GAME
  add constraint PK_GM_GAME primary key (ROW_ID)
  using index 
  tablespace TS_OTHERS
  pctfree 10
  initrans 2
  maxtrans 255;
  
  
  
  
  
  
  -- Create table
 drop table INSTANT_GM_GAME_AWARD_PROVINCE;
create table INSTANT_GM_GAME_AWARD_PROVINCE
(
  row_id            NUMBER(18) default -1 not null,
  game_id           NUMBER(18) default -1 not null,
  game_code         VARCHAR2(10),
  game_name         VARCHAR2(100),
  province_org_id   NUMBER(18) default -1 not null,
  province_org_code VARCHAR2(32),
  province_org_name VARCHAR2(100),
  award_setting_id  NUMBER(18) default -1 not null,
  active_flag       CHAR(1) default 'Y' not null,
  deleted_flag      CHAR(1) default '0' not null,
  origin_flag       CHAR(1),
  origin_app        VARCHAR2(32),
  created_user_name VARCHAR2(32),
  created_org_id    NUMBER(18) default -1 not null,
  created_org_name  VARCHAR2(100),
  created_user_id   NUMBER(18) default -1 not null,
  created_date      DATE,
  last_upd_org_id   NUMBER(18) default -1 not null,
  last_upd_by       NUMBER(18) default -1 not null,
  last_upd_date     DATE,
  modification_num  NUMBER(8) default 0 not null,
  remark            VARCHAR2(255)
)
tablespace TS_OTHERS
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the columns 
comment on column INSTANT_GM_GAME_AWARD_PROVINCE.game_id
  is '游戏ID';
comment on column INSTANT_GM_GAME_AWARD_PROVINCE.game_code
  is '游戏编码';
comment on column INSTANT_GM_GAME_AWARD_PROVINCE.game_name
  is '游戏名称';
comment on column INSTANT_GM_GAME_AWARD_PROVINCE.province_org_id
  is '通兑省中心ID';
comment on column INSTANT_GM_GAME_AWARD_PROVINCE.province_org_code
  is '省中心编码';
comment on column INSTANT_GM_GAME_AWARD_PROVINCE.province_org_name
  is '通兑省中心名称';
comment on column INSTANT_GM_GAME_AWARD_PROVINCE.award_setting_id
  is '游戏通兑奖组设定ID';
comment on column INSTANT_GM_GAME_AWARD_PROVINCE.active_flag
  is '启用状态标志：I：未启用，Y：启用，N：停用';
comment on column INSTANT_GM_GAME_AWARD_PROVINCE.deleted_flag
  is '记录删除标志 [0]-未删除;[1]-逻辑删除';
comment on column INSTANT_GM_GAME_AWARD_PROVINCE.origin_flag
  is '数据来源的标志：[]或[I]-(Input)系统录入;[O]-(Out)外部接口导入;[S]-(System)系统保留。本标志不能挪为它用。';
comment on column INSTANT_GM_GAME_AWARD_PROVINCE.origin_app
  is '数据来源应用的代码';
comment on column INSTANT_GM_GAME_AWARD_PROVINCE.created_user_name
  is '记录创建人名称';
comment on column INSTANT_GM_GAME_AWARD_PROVINCE.created_org_id
  is '记录创建人所在业务单元';
comment on column INSTANT_GM_GAME_AWARD_PROVINCE.created_org_name
  is '记录创建人所在业务单元名称';
comment on column INSTANT_GM_GAME_AWARD_PROVINCE.created_user_id
  is '记录创建人ID';
comment on column INSTANT_GM_GAME_AWARD_PROVINCE.created_date
  is '记录创建时间';
comment on column INSTANT_GM_GAME_AWARD_PROVINCE.last_upd_org_id
  is '记录修改人所在业务单元';
comment on column INSTANT_GM_GAME_AWARD_PROVINCE.last_upd_by
  is '记录最近修改人ID';
comment on column INSTANT_GM_GAME_AWARD_PROVINCE.last_upd_date
  is '记录最近修改日期';
comment on column INSTANT_GM_GAME_AWARD_PROVINCE.modification_num
  is '记录修改次数';
comment on column INSTANT_GM_GAME_AWARD_PROVINCE.remark
  is '备注';
-- Create/Recreate indexes 
create index INSTANT_IDX_GM_GAP_AWSEID on INSTANT_GM_GAME_AWARD_PROVINCE (AWARD_SETTING_ID)
  tablespace TS_OTHERS
  pctfree 10
  initrans 2
  maxtrans 255;
create index INSTANT_IDX_GM_GAP_GAID on INSTANT_GM_GAME_AWARD_PROVINCE (GAME_ID)
  tablespace TS_OTHERS
  pctfree 10
  initrans 2
  maxtrans 255;
create index INSTANT_IDX_GM_GAP_GAODE on INSTANT_GM_GAME_AWARD_PROVINCE (GAME_CODE)
  tablespace TS_OTHERS
  pctfree 10
  initrans 2
  maxtrans 255;
create index INSTANT_IDX_GM_GAP_PRORGID on INSTANT_GM_GAME_AWARD_PROVINCE (PROVINCE_ORG_ID)
  tablespace TS_OTHERS
  pctfree 10
  initrans 2
  maxtrans 255;
-- Create/Recreate primary, unique and foreign key constraints 
alter table INSTANT_GM_GAME_AWARD_PROVINCE
  add constraint PK_GM_GAME_AWARD_PROVINCE primary key (ROW_ID)
  using index 
  tablespace TS_OTHERS
  pctfree 10
  initrans 2
  maxtrans 255;
  
  
  
  
 -- Create table
 drop table INSTANT_MD_CHANNEL_CUSTOMER;
create table INSTANT_MD_CHANNEL_CUSTOMER
(
  row_id                     NUMBER(18) default -1 not null,
  customer_code              VARCHAR2(32),
  customer_name              VARCHAR2(100),
  org_type_code              VARCHAR2(2),
  org_type_name              VARCHAR2(32),
  org_id                     NUMBER(18) default -1 not null,
  org_code                   VARCHAR2(32),
  org_name                   VARCHAR2(100),
  channel_type_code          VARCHAR2(2),
  channel_type_name          VARCHAR2(32),
  contact_person             VARCHAR2(32),
  tel                        VARCHAR2(32),
  address                    VARCHAR2(255),
  post_code                  VARCHAR2(32),
  active_flag                CHAR(1) default 'Y' not null,
  deleted_flag               CHAR(1) default '0' not null,
  origin_flag                CHAR(1),
  origin_app                 VARCHAR2(32),
  created_org_id             NUMBER(18) default -1 not null,
  created_org_name           VARCHAR2(100),
  created_user_id            NUMBER(18) default -1 not null,
  created_user_name          VARCHAR2(32),
  created_date               DATE,
  last_upd_org_id            NUMBER(18) default -1 not null,
  last_upd_by                NUMBER(18) default -1 not null,
  last_upd_date              DATE,
  modification_num           NUMBER(8) default 0 not null,
  remark                     VARCHAR2(255),
  shop_draw_return_flag      CHAR(1) default 'Y' not null,
  shop_order_submit_flag     CHAR(1) default 'Y' not null,
  shop_confirm_activate_flag CHAR(1) default 'Y' not null,
  shop_manual_activate_flag  CHAR(1) default 'Y' not null,
  shop_trade_out_flag        CHAR(1) default 'Y' not null,
  shop_wh_id                 NUMBER(18) default -1 not null,
  shop_wh_name               VARCHAR2(100),
  shop_s_settle_type_code    VARCHAR2(32),
  shop_s_settle_type_name    VARCHAR2(100),
  shop_s_style_code          VARCHAR2(32),
  shop_s_style_name          VARCHAR2(100),
  shop_s_per_setting_id      NUMBER(18) default -1 not null,
  shop_s_percent             NUMBER(10,6) default 0 not null,
  shop_s_rule_setting_id     NUMBER(18) default -1 not null,
  shop_s_settle_period_code  VARCHAR2(2),
  shop_s_settle_period_name  VARCHAR2(100),
  shop_a_settle_type_code    VARCHAR2(32),
  shop_a_settle_type_name    VARCHAR2(100),
  shop_a_style_code          VARCHAR2(32),
  shop_a_style_name          VARCHAR2(100),
  shop_a_per_setting_id      NUMBER(18) default -1 not null,
  shop_a_percent             NUMBER(10,6) default 0 not null,
  shop_a_rule_setting_id     NUMBER(18) default -1 not null,
  shop_a_settle_period_code  VARCHAR2(2),
  shop_a_settle_period_name  VARCHAR2(100),
  shop_c_max_amt             NUMBER(22,6) default 0 not null,
  shop_c_min_amt             NUMBER(22,6) default 0 not null,
  shop_c_confirm_amt         NUMBER(22,6) default 0 not null,
  shop_ua_lock_period        NUMBER(8) default 0 not null,
  shop_ua_lock_times         NUMBER(8) default 0 not null,
  shop_ua_lock_minutes       NUMBER(8) default 0 not null,
  sr_m_settle_type_code      VARCHAR2(32),
  sr_m_settle_type_name      VARCHAR2(100),
  sr_m_style_code            VARCHAR2(32),
  sr_m_style_name            VARCHAR2(100),
  sr_m_per_setting_id        NUMBER(18) default -1 not null,
  sr_m_percent               NUMBER(10,6) default 0 not null,
  sr_m_rule_setting_id       NUMBER(18) default -1 not null,
  sr_m_settle_period_code    VARCHAR2(2),
  sr_m_settle_period_name    VARCHAR2(100),
  sr_order_submit_flag       CHAR(1) default 'Y' not null,
  sr_trade_out_flag          CHAR(1) default 'Y' not null,
  sr_wh_id                   NUMBER(18) default -1 not null,
  sr_wh_name                 VARCHAR2(100),
  sr_s_per_setting_id        NUMBER(18) default -1 not null,
  sr_s_percent               NUMBER(10,6) default 0 not null,
  sr_a_settle_type_code      VARCHAR2(32),
  sr_a_settle_type_name      VARCHAR2(100),
  sr_a_style_code            VARCHAR2(32),
  sr_a_style_name            VARCHAR2(100),
  sr_a_per_setting_id        NUMBER(18) default -1 not null,
  sr_a_percent               NUMBER(10,6) default 0 not null,
  sr_a_rule_setting_id       NUMBER(18) default -1 not null,
  sr_a_settle_period_code    VARCHAR2(2),
  sr_a_settle_period_name    VARCHAR2(100),
  sr_c_max_amt               NUMBER(22,6) default 0 not null,
  sr_c_min_amt               NUMBER(22,6) default 0 not null,
  sr_c_confirm_amt           NUMBER(22,6) default 0 not null,
  sr_ua_lock_period          NUMBER(8) default 0 not null,
  sr_ua_lock_times           NUMBER(8) default 0 not null,
  sr_ua_lock_minutes         NUMBER(8) default 0 not null,
  sr_acc_out_flag            CHAR(1) default 'Y' not null,
  sr_acc_in_flag             CHAR(1) default 'Y' not null,
  sr_fc_auto_flag            CHAR(1) default 'Y' not null,
  distribute_type_code       VARCHAR2(2),
  distribute_type_name       VARCHAR2(32)
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
comment on column INSTANT_MD_CHANNEL_CUSTOMER.customer_code
  is '大客户编码';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.customer_name
  is '大客户名称';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.org_type_code
  is '所属中心类型编码 【LOV_CODE：ORG_TYPE】';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.org_type_name
  is '所属中心类型名称 【LOV_CODE：ORG_TYPE】';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.org_id
  is '所属中心ID';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.org_code
  is '所属中心代码';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.org_name
  is '所属中心名称';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.channel_type_code
  is '渠道类型编码 【LOV_CODE：MD_CHANNEL_TYPE】';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.channel_type_name
  is '渠道类型名称 【LOV_CODE：MD_CHANNEL_TYPE】';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.contact_person
  is '联系人';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.tel
  is '联系电话';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.address
  is '联系地址';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.post_code
  is '邮政编码';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.active_flag
  is '启用状态标志：I：未启用，Y：启用，N：停用';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.deleted_flag
  is '记录删除标志 [0]-未删除;[1]-逻辑删除';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.origin_flag
  is '数据来源的标志：[]或[I]-(Input)系统录入;[O]-(Out)外部接口导入;[S]-(System)系统保留。本标志不能挪为它用。';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.origin_app
  is '数据来源应用的代码';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.created_org_id
  is '记录创建人所在业务单元ID';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.created_org_name
  is '记录创建人所在业务单元名称';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.created_user_id
  is '记录创建人ID';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.created_user_name
  is '记录创建人名称';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.created_date
  is '记录创建时间';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.last_upd_org_id
  is '记录修改人所在业务单元';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.last_upd_by
  is '记录最近修改人ID';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.last_upd_date
  is '记录最近修改日期';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.modification_num
  is '记录修改次数';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.remark
  is '备注';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.shop_draw_return_flag
  is '门店领退票许可 [Y] - 可以; [N] - 不可以';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.shop_order_submit_flag
  is '门店订单提交许可 [Y] - 可以; [N] - 不可以';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.shop_confirm_activate_flag
  is '门店确认激活许可 [Y] - 可以; [N] - 不可以';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.shop_manual_activate_flag
  is '门店手工激活许可 [Y] - 可以; [N] - 不可以';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.shop_trade_out_flag
  is '门店出库确认许可 [Y] - 可以; [N] - 不可以';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.shop_wh_id
  is '门店订货仓库ID';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.shop_wh_name
  is '门店订货仓库名称';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.shop_s_settle_type_code
  is '门店销售佣金结算方式代码 【LOV_CODE：MD_SETTLE_TYPE】';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.shop_s_settle_type_name
  is '门店销售佣金结算方式名称 【LOV_CODE：MD_SETTLE_TYPE】';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.shop_s_style_code
  is '门店销售佣金模式代码 【LOV_CODE：MD_COMM_STYLE】';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.shop_s_style_name
  is '门店销售佣金模式名称 【LOV_CODE：MD_COMM_STYLE】';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.shop_s_per_setting_id
  is '门店销售佣金结算比例ID';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.shop_s_percent
  is '门店销售佣金结算比例';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.shop_s_rule_setting_id
  is '门店销售佣金结算规则ID。对应MD_COMM_RULE_SETTING表ROW_ID字段值';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.shop_s_settle_period_code
  is '门店销售佣金结算周期代码。对应MD_COMM_RULE_SETTING表SETTLE_PERIOD_CODE字段值';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.shop_s_settle_period_name
  is '门店销售佣金结算周期名称。对应MD_COMM_RULE_SETTING表SETTLE_PERIOD_NAME字段值';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.shop_a_settle_type_code
  is '门店兑奖佣金结算方式代码 【LOV_CODE：MD_SETTLE_TYPE】';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.shop_a_settle_type_name
  is '门店兑奖佣金结算方式名称 【LOV_CODE：MD_SETTLE_TYPE】';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.shop_a_style_code
  is '门店兑奖佣金模式代码 【LOV_CODE：MD_COMM_STYLE】';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.shop_a_style_name
  is '门店兑奖佣金模式名称 【LOV_CODE：MD_COMM_STYLE】';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.shop_a_per_setting_id
  is '门店兑奖佣金结算比例ID';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.shop_a_percent
  is '门店兑奖佣金结算比例';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.shop_a_rule_setting_id
  is '门店兑奖佣金结算规则ID。对应MD_COMM_RULE_SETTING表ROW_ID字段值';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.shop_a_settle_period_code
  is '门店兑奖佣金结算周期代码。对应MD_COMM_RULE_SETTING表SETTLE_PERIOD_CODE字段值';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.shop_a_settle_period_name
  is '门店兑奖佣金结算周期名称。对应MD_COMM_RULE_SETTING表SETTLE_PERIOD_NAME字段值';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.shop_c_max_amt
  is '门店兑奖最高限额';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.shop_c_min_amt
  is '门店兑奖最低限额';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.shop_c_confirm_amt
  is '门店兑奖支付确认金额';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.shop_ua_lock_period
  is '门店未中奖票兑奖锁定周期 单位为分钟';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.shop_ua_lock_times
  is '门店未中奖票兑奖锁定次数';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.shop_ua_lock_minutes
  is '门店未中奖票兑奖锁定时长 单位为分钟';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.sr_m_settle_type_code
  is 'SR佣金结算方式代码 【LOV_CODE：MD_SETTLE_TYPE】';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.sr_m_settle_type_name
  is 'SR佣金结算方式名称 【LOV_CODE：MD_SETTLE_TYPE】';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.sr_m_style_code
  is 'SR佣金模式代码 【LOV_CODE：MD_COMM_STYLE】';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.sr_m_style_name
  is 'SR佣金模式名称 【LOV_CODE：MD_COMM_STYLE】';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.sr_m_per_setting_id
  is 'SR佣金结算比例ID';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.sr_m_percent
  is 'SR佣金结算比例';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.sr_m_rule_setting_id
  is 'SR佣金结算规则ID。对应MD_COMM_RULE_SETTING表ROW_ID字段值';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.sr_m_settle_period_code
  is 'SR管理佣金结算周期代码。对应MD_COMM_RULE_SETTING表SETTLE_PERIOD_CODE字段值';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.sr_m_settle_period_name
  is 'SR佣金结算周期名称。对应MD_COMM_RULE_SETTING表SETTLE_PERIOD_NAME字段值';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.sr_order_submit_flag
  is 'SR订单提交许可 [Y] - 可以; [N] - 不可以';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.sr_trade_out_flag
  is 'SR出库确认许可 [Y] - 可以; [N] - 不可以';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.sr_wh_id
  is 'SR订货仓库ID';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.sr_wh_name
  is 'SR订货仓库名称';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.sr_s_per_setting_id
  is 'SR销售佣金结算比例ID';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.sr_s_percent
  is 'SR销售佣金结算比例';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.sr_a_settle_type_code
  is 'SR兑奖佣金结算方式代码 【LOV_CODE：MD_SETTLE_TYPE】';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.sr_a_settle_type_name
  is 'SR兑奖佣金结算方式名称 【LOV_CODE：MD_SETTLE_TYPE】';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.sr_a_style_code
  is 'SR兑奖佣金模式代码 【LOV_CODE：MD_COMM_STYLE】';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.sr_a_style_name
  is 'SR兑奖佣金模式名称 【LOV_CODE：MD_COMM_STYLE】';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.sr_a_per_setting_id
  is 'SR兑奖佣金结算比例ID';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.sr_a_percent
  is 'SR兑奖佣金结算比例';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.sr_a_rule_setting_id
  is 'SR兑奖佣金结算规则ID。对应MD_COMM_RULE_SETTING表ROW_ID字段值';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.sr_a_settle_period_code
  is 'SR兑奖佣金结算周期代码。对应MD_COMM_RULE_SETTING表SETTLE_PERIOD_CODE字段值';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.sr_a_settle_period_name
  is 'SR兑奖佣金结算周期名称。对应MD_COMM_RULE_SETTING表SETTLE_PERIOD_NAME字段值';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.sr_c_max_amt
  is 'SR兑奖最高限额';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.sr_c_min_amt
  is 'SR兑奖最低限额';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.sr_c_confirm_amt
  is 'SR兑奖支付确认金额';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.sr_ua_lock_period
  is 'SR未中奖票兑奖锁定周期 单位为分钟';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.sr_ua_lock_times
  is 'SR未中奖票兑奖锁定次数';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.sr_ua_lock_minutes
  is 'SR未中奖票兑奖锁定时长 单位为分钟';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.sr_acc_out_flag
  is 'SR账户允许转出标志 [Y] - 允许; [N] - 不允许。针对销售代表有效';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.sr_acc_in_flag
  is 'SR账户允许转入标志 [Y] - 允许; [N] - 不允许。针对销售代表有效';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.sr_fc_auto_flag
  is 'SR是否自动归集。大客户模式下有效，且对SR有效';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.distribute_type_code
  is '大客户分发模式编码 【LOV_CODE：MD_CHANNEL_DISTRIBUTE_TYPE】';
comment on column INSTANT_MD_CHANNEL_CUSTOMER.distribute_type_name
  is '大客户分发模式名称 【LOV_CODE：MD_CHANNEL_DISTRIBUTE_TYPE】';
-- Create/Recreate primary, unique and foreign key constraints 
alter table INSTANT_MD_CHANNEL_CUSTOMER
  add constraint PK_MD_CHANNEL_CUSTOMER primary key (ROW_ID)
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
  
  
  
  drop table instant_SP_PROMOTION_AWARD;
  create table instant_SP_PROMOTION_AWARD
(
  ROW_ID               NUMBER(18) default -1 not null,
  SP_AWARD_NO          VARCHAR2(32),
  PROMOTION_ID         NUMBER(18) default -1 not null,
  PROMOTION_CODE       VARCHAR2(32),
  PROMOTION_NAME       VARCHAR2(100),
  PROMOTION_LEVEL_ID   NUMBER(18) default -1,
  PROMOTION_LEVEL      NUMBER(8) default 0 not null,
  COMP_TYPE_CODE       VARCHAR2(2),
  COMP_TYPE_NAME       VARCHAR2(32),
  BONUS                NUMBER(22,6) default 0 not null,
  MATERIAL_ID          NUMBER(18) default -1 not null,
  MATERIAL_NAME        VARCHAR2(100),
  TAX_AMT              NUMBER(22,6) default 0 not null,
  OPT_CENTER_TYPE_CODE VARCHAR2(2),
  OPT_CENTER_TYPE_NAME VARCHAR2(100),
  OPT_CENTER_ID        NUMBER(18) default -1,
  OPT_CENTER_CODE      VARCHAR2(32),
  OPT_CENTER_NAME      VARCHAR2(100),
  CLAIM_TYPE_CODE      VARCHAR2(2),
  CLAIM_TYPE_NAME      VARCHAR2(32),
  OPT_ORG_TYPE_CODE    VARCHAR2(2),
  OPT_ORG_TYPE_NAME    VARCHAR2(32),
  OPT_ORG_ID           NUMBER(18) default -1 not null,
  OPT_ORG_CODE         VARCHAR2(32),
  OPT_ORG_NAME         VARCHAR2(100),
  OPT_USER_ID          NUMBER(18) default -1 not null,
  OPT_USER_CODE        VARCHAR2(32),
  OPT_USER_NAME        VARCHAR2(100),
  AWARD_DATE           DATE,
  TRANS_ID             VARCHAR2(32),
  HELP_CLAIM_FLAG      CHAR(1) default 'N' not null,
  HELP_USER_CODE       VARCHAR2(32),
  HELP_ORG_CODE        VARCHAR2(32),
  BOOK_DATE            DATE,
  WINNER_NAME          VARCHAR2(100),
  WINNER_TEL           VARCHAR2(100),
  IDENTITY_TYPE_CODE   VARCHAR2(32),
  IDENTITY_TYPE_NAME   VARCHAR2(100),
  CRED_NO              VARCHAR2(100),
  CANCEL_FLAG          CHAR(1),
  CANCEL_ORG_TYPE_CODE VARCHAR2(2),
  CANCEL_ORG_TYPE_NAME VARCHAR2(100),
  CANCEL_ORG_ID        NUMBER(18) default -1,
  CANCEL_ORG_CODE      VARCHAR2(32),
  CANCEL_ORG_NAME      VARCHAR2(100),
  CANCEL_USER_ID       NUMBER(18) default -1,
  CANCEL_USER_CODE     VARCHAR2(32),
  CANCEL_USER_NAME     VARCHAR2(100),
  DELETED_FLAG         CHAR(1) default '0' not null,
  ORIGIN_FLAG          CHAR(1),
  ORIGIN_APP           VARCHAR2(32),
  CREATED_ORG_ID       NUMBER(18) default -1,
  CREATED_ORG_NAME     VARCHAR2(100),
  CREATED_USER_ID      NUMBER(18) default -1 not null,
  CREATED_USER_NAME    VARCHAR2(32),
  CREATED_DATE         DATE,
  LAST_UPD_ORG_ID      NUMBER(18) default -1,
  LAST_UPD_BY          NUMBER(18) default -1 not null,
  LAST_UPD_DATE        DATE,
  MODIFICATION_NUM     NUMBER(8) default 0 not null,
  REMARK               VARCHAR2(255)
)
;
comment on table instant_SP_PROMOTION_AWARD
  is '促销兑奖记录';
comment on column instant_SP_PROMOTION_AWARD.ROW_ID
  is 'ROW_ID';
comment on column instant_SP_PROMOTION_AWARD.SP_AWARD_NO
  is '促销兑奖记录编号';
comment on column instant_SP_PROMOTION_AWARD.PROMOTION_ID
  is '促销活动ID';
comment on column instant_SP_PROMOTION_AWARD.PROMOTION_CODE
  is '促销编号';
comment on column instant_SP_PROMOTION_AWARD.PROMOTION_NAME
  is '促销活动名称';
comment on column instant_SP_PROMOTION_AWARD.PROMOTION_LEVEL_ID
  is '促销奖级设置ID';
comment on column instant_SP_PROMOTION_AWARD.PROMOTION_LEVEL
  is '促销奖级';
comment on column instant_SP_PROMOTION_AWARD.COMP_TYPE_CODE
  is '组合类型代码';
comment on column instant_SP_PROMOTION_AWARD.COMP_TYPE_NAME
  is '组合类型名称';
comment on column instant_SP_PROMOTION_AWARD.BONUS
  is '奖金';
comment on column instant_SP_PROMOTION_AWARD.MATERIAL_ID
  is '奖励物品ID';
comment on column instant_SP_PROMOTION_AWARD.MATERIAL_NAME
  is '奖励物品名称';
comment on column instant_SP_PROMOTION_AWARD.TAX_AMT
  is '税金';
comment on column instant_SP_PROMOTION_AWARD.OPT_CENTER_TYPE_CODE
  is '兑奖中心类型编码';
comment on column instant_SP_PROMOTION_AWARD.OPT_CENTER_TYPE_NAME
  is '兑奖中心类型名称';
comment on column instant_SP_PROMOTION_AWARD.OPT_CENTER_ID
  is '兑奖中心ID';
comment on column instant_SP_PROMOTION_AWARD.OPT_CENTER_CODE
  is '兑奖中心CODE';
comment on column instant_SP_PROMOTION_AWARD.OPT_CENTER_NAME
  is '兑奖中心NAME';
comment on column instant_SP_PROMOTION_AWARD.CLAIM_TYPE_CODE
  is '兑奖类型编码';
comment on column instant_SP_PROMOTION_AWARD.CLAIM_TYPE_NAME
  is '兑奖类型名称';
comment on column instant_SP_PROMOTION_AWARD.OPT_ORG_TYPE_CODE
  is '兑奖单位类型编码';
comment on column instant_SP_PROMOTION_AWARD.OPT_ORG_TYPE_NAME
  is '兑奖单位类型名称';
comment on column instant_SP_PROMOTION_AWARD.OPT_ORG_ID
  is '兑奖单位ID';
comment on column instant_SP_PROMOTION_AWARD.OPT_ORG_CODE
  is '兑奖单位代码';
comment on column instant_SP_PROMOTION_AWARD.OPT_ORG_NAME
  is '兑奖单位名称';
comment on column instant_SP_PROMOTION_AWARD.OPT_USER_ID
  is '兑奖操作人ID';
comment on column instant_SP_PROMOTION_AWARD.OPT_USER_CODE
  is '兑奖操作人代码';
comment on column instant_SP_PROMOTION_AWARD.OPT_USER_NAME
  is '兑奖操作人';
comment on column instant_SP_PROMOTION_AWARD.AWARD_DATE
  is '兑奖日期';
comment on column instant_SP_PROMOTION_AWARD.TRANS_ID
  is '事务ID';
comment on column instant_SP_PROMOTION_AWARD.HELP_CLAIM_FLAG
  is '协助兑奖标志';
comment on column instant_SP_PROMOTION_AWARD.HELP_USER_CODE
  is '协助者账号';
comment on column instant_SP_PROMOTION_AWARD.HELP_ORG_CODE
  is '协助者组织编码';
comment on column instant_SP_PROMOTION_AWARD.BOOK_DATE
  is '登记日期';
comment on column instant_SP_PROMOTION_AWARD.WINNER_NAME
  is '中奖人姓名';
comment on column instant_SP_PROMOTION_AWARD.WINNER_TEL
  is '联系电话';
comment on column instant_SP_PROMOTION_AWARD.IDENTITY_TYPE_CODE
  is '证件类型编码';
comment on column instant_SP_PROMOTION_AWARD.IDENTITY_TYPE_NAME
  is '证件类型名称';
comment on column instant_SP_PROMOTION_AWARD.CRED_NO
  is '证件号码';
comment on column instant_SP_PROMOTION_AWARD.CANCEL_FLAG
  is '取消标志';
comment on column instant_SP_PROMOTION_AWARD.CANCEL_ORG_TYPE_CODE
  is '取消人单位类型';
comment on column instant_SP_PROMOTION_AWARD.CANCEL_ORG_TYPE_NAME
  is '取消人单位名称';
comment on column instant_SP_PROMOTION_AWARD.CANCEL_ORG_ID
  is '取消人单位ID';
comment on column instant_SP_PROMOTION_AWARD.CANCEL_ORG_CODE
  is '取消人单位CODE';
comment on column instant_SP_PROMOTION_AWARD.CANCEL_ORG_NAME
  is '取消人单位NAME';
comment on column instant_SP_PROMOTION_AWARD.CANCEL_USER_ID
  is '取消人ID';
comment on column instant_SP_PROMOTION_AWARD.CANCEL_USER_CODE
  is '取消人CODE';
comment on column instant_SP_PROMOTION_AWARD.CANCEL_USER_NAME
  is '取消人NAME';
comment on column instant_SP_PROMOTION_AWARD.DELETED_FLAG
  is '记录删除标志';
comment on column instant_SP_PROMOTION_AWARD.ORIGIN_FLAG
  is '记录来源标志';
comment on column instant_SP_PROMOTION_AWARD.ORIGIN_APP
  is '记录来源应用';
comment on column instant_SP_PROMOTION_AWARD.CREATED_ORG_ID
  is '记录创建人所在业务单元ID';
comment on column instant_SP_PROMOTION_AWARD.CREATED_ORG_NAME
  is '记录创建人所在业务单元名称';
comment on column instant_SP_PROMOTION_AWARD.CREATED_USER_ID
  is '记录创建人ID';
comment on column instant_SP_PROMOTION_AWARD.CREATED_USER_NAME
  is '记录创建人名称';
comment on column instant_SP_PROMOTION_AWARD.CREATED_DATE
  is '记录创建时间';
comment on column instant_SP_PROMOTION_AWARD.LAST_UPD_ORG_ID
  is '记录修改人所在业务单元';
comment on column instant_SP_PROMOTION_AWARD.LAST_UPD_BY
  is '记录最近修改人ID';
comment on column instant_SP_PROMOTION_AWARD.LAST_UPD_DATE
  is '记录最近修改时间';
comment on column instant_SP_PROMOTION_AWARD.MODIFICATION_NUM
  is '记录修改次数';
comment on column instant_SP_PROMOTION_AWARD.REMARK
  is '备注';
alter table instant_SP_PROMOTION_AWARD
  add constraint PK_SP_PROMOTION_AWARD primary key (ROW_ID);
create index instant_IDX_SP_PA_OPOID on instant_SP_PROMOTION_AWARD (OPT_ORG_ID);
create index instant_IDX_SP_PA_PROID on instant_SP_PROMOTION_AWARD (PROMOTION_ID);





drop table INSTANT_SP_PROMOTION;
create table INSTANT_SP_PROMOTION
(
  row_id               NUMBER(18) default -1 not null,
  promotion_code       VARCHAR2(32),
  promotion_name       VARCHAR2(100),
  begin_date           DATE,
  end_date             DATE,
  type_code            VARCHAR2(2),
  type_name            VARCHAR2(32),
  status_code          VARCHAR2(2),
  status_name          VARCHAR2(32),
  district_scope_type  CHAR(1) not null,
  scope_limited_flag   CHAR(1) not null,
  shop_flag            CHAR(1),
  sr_flag              CHAR(1),
  first_pur_flag       CHAR(1),
  first_pur_amt        NUMBER(22,6) default 0 not null,
  settle_type_code     VARCHAR2(2),
  settle_type_name     VARCHAR2(100),
  settle_period_code   VARCHAR2(2),
  settle_period_name   VARCHAR2(100),
  settle_day           NUMBER(8) default 0 not null,
  settle_flag          CHAR(1),
  settle_amt           NUMBER(8) default 0 not null,
  settle_mod_type_code VARCHAR2(2),
  settle_mod_type_name VARCHAR2(100),
  proj_type_code       VARCHAR2(2),
  proj_type_name       VARCHAR2(100),
  cfm_date_flag        CHAR(1),
  cfm_date_b           DATE,
  cfm_date_e           DATE,
  activate_date_flag   CHAR(1),
  activate_date_b      DATE,
  activate_date_e      DATE,
  org_type             VARCHAR2(2),
  org_type_name        VARCHAR2(100),
  org_id               NUMBER(18) default -1 not null,
  org_code             VARCHAR2(32),
  org_name             VARCHAR2(100),
  start_date           DATE,
  start_org_type       VARCHAR2(2),
  start_org_type_name  VARCHAR2(100),
  start_org_id         NUMBER(18) default -1,
  start_org_code       VARCHAR2(32),
  start_org_name       VARCHAR2(100),
  start_user_id        NUMBER(18) default -1,
  start_user_name      VARCHAR2(100),
  parse_date           DATE,
  parse_org_type_code  VARCHAR2(2),
  parse_org_type_name  VARCHAR2(100),
  parse_org_id         NUMBER(18) default -1,
  parse_org_code       VARCHAR2(32),
  parse_org_name       VARCHAR2(100),
  parse_user_id        NUMBER(18) default -1,
  parse_user_name      VARCHAR2(100),
  t_date               DATE,
  t_org_type_code      VARCHAR2(2),
  t_org_type_name      VARCHAR2(100),
  t_org_id             NUMBER(18) default -1,
  t_org_code           VARCHAR2(32),
  t_org_name           VARCHAR2(100),
  t_user_id            NUMBER(18) default -1,
  t_user_name          VARCHAR2(100),
  deleted_flag         CHAR(1) default '0' not null,
  origin_flag          CHAR(1),
  origin_app           VARCHAR2(32),
  created_org_id       NUMBER(18) default -1,
  created_org_name     VARCHAR2(100),
  created_user_id      NUMBER(18) default -1 not null,
  created_user_name    VARCHAR2(32),
  created_date         DATE,
  last_upd_org_id      NUMBER(18) default -1,
  last_upd_by          NUMBER(18) default -1 not null,
  last_upd_date        DATE,
  modification_num     NUMBER(8) default 0 not null,
  remark               VARCHAR2(255)
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
-- Add comments to the table 
comment on table INSTANT_SP_PROMOTION
  is '促销活动';
-- Add comments to the columns 
comment on column INSTANT_SP_PROMOTION.row_id
  is 'ROW_ID';
comment on column INSTANT_SP_PROMOTION.promotion_code
  is '促销编号';
comment on column INSTANT_SP_PROMOTION.promotion_name
  is '促销名称';
comment on column INSTANT_SP_PROMOTION.begin_date
  is '开始日期';
comment on column INSTANT_SP_PROMOTION.end_date
  is '结束日期';
comment on column INSTANT_SP_PROMOTION.type_code
  is '促销类型编号';
comment on column INSTANT_SP_PROMOTION.type_name
  is '促销类型名称';
comment on column INSTANT_SP_PROMOTION.status_code
  is '状态编码';
comment on column INSTANT_SP_PROMOTION.status_name
  is '状态名称';
comment on column INSTANT_SP_PROMOTION.district_scope_type
  is '区域范围类型';
comment on column INSTANT_SP_PROMOTION.scope_limited_flag
  is '是否允许跨地市兑奖';
comment on column INSTANT_SP_PROMOTION.shop_flag
  is '包含门店';
comment on column INSTANT_SP_PROMOTION.sr_flag
  is '包含SR';
comment on column INSTANT_SP_PROMOTION.first_pur_flag
  is '首次购票限制标志';
comment on column INSTANT_SP_PROMOTION.first_pur_amt
  is '首次购票量';
comment on column INSTANT_SP_PROMOTION.settle_type_code
  is '结算方式类型';
comment on column INSTANT_SP_PROMOTION.settle_type_name
  is '结算方式类型名称';
comment on column INSTANT_SP_PROMOTION.settle_period_code
  is '结算周期类型';
comment on column INSTANT_SP_PROMOTION.settle_period_name
  is '结算周期类型名称';
comment on column INSTANT_SP_PROMOTION.settle_day
  is '结算日';
comment on column INSTANT_SP_PROMOTION.settle_flag
  is '结算完毕标志';
comment on column INSTANT_SP_PROMOTION.settle_amt
  is '已结算次数';
comment on column INSTANT_SP_PROMOTION.settle_mod_type_code
  is '比例计算方式类型';
comment on column INSTANT_SP_PROMOTION.settle_mod_type_name
  is '比例计算方式类型名称';
comment on column INSTANT_SP_PROMOTION.proj_type_code
  is '促销项目类型';
comment on column INSTANT_SP_PROMOTION.proj_type_name
  is '促销项目类型名称';
comment on column INSTANT_SP_PROMOTION.cfm_date_flag
  is '确认时间标志';
comment on column INSTANT_SP_PROMOTION.cfm_date_b
  is '确认开始时间';
comment on column INSTANT_SP_PROMOTION.cfm_date_e
  is '确认结算时间';
comment on column INSTANT_SP_PROMOTION.activate_date_flag
  is '激活时间标志';
comment on column INSTANT_SP_PROMOTION.activate_date_b
  is '激活开始时间';
comment on column INSTANT_SP_PROMOTION.activate_date_e
  is '激活结算时间';
comment on column INSTANT_SP_PROMOTION.org_type
  is '促销机构类型编码';
comment on column INSTANT_SP_PROMOTION.org_type_name
  is '促销机构类型名称';
comment on column INSTANT_SP_PROMOTION.org_id
  is '促销机构ID';
comment on column INSTANT_SP_PROMOTION.org_code
  is '促销机构编码';
comment on column INSTANT_SP_PROMOTION.org_name
  is '促销机构名称';
comment on column INSTANT_SP_PROMOTION.start_date
  is '活动启动日期';
comment on column INSTANT_SP_PROMOTION.start_org_type
  is '活动启动人机构类型编码';
comment on column INSTANT_SP_PROMOTION.start_org_type_name
  is '活动启动人机构类型名称';
comment on column INSTANT_SP_PROMOTION.start_org_id
  is '活动启动人机构ID';
comment on column INSTANT_SP_PROMOTION.start_org_code
  is '活动启动人机构CODE';
comment on column INSTANT_SP_PROMOTION.start_org_name
  is '活动启动人机构NAME';
comment on column INSTANT_SP_PROMOTION.start_user_id
  is '活动启动人ID';
comment on column INSTANT_SP_PROMOTION.start_user_name
  is '活动启动人NAME';
comment on column INSTANT_SP_PROMOTION.parse_date
  is '暂停时间';
comment on column INSTANT_SP_PROMOTION.parse_org_type_code
  is '暂停人所属中心类型编码';
comment on column INSTANT_SP_PROMOTION.parse_org_type_name
  is '暂停人所属中心类型名称';
comment on column INSTANT_SP_PROMOTION.parse_org_id
  is '暂停人所属中心ID';
comment on column INSTANT_SP_PROMOTION.parse_org_code
  is '暂停人所属中心CODE';
comment on column INSTANT_SP_PROMOTION.parse_org_name
  is '暂停人所属中心NAME';
comment on column INSTANT_SP_PROMOTION.parse_user_id
  is '暂停人ID';
comment on column INSTANT_SP_PROMOTION.parse_user_name
  is '暂停人NAME';
comment on column INSTANT_SP_PROMOTION.t_date
  is '终止时间';
comment on column INSTANT_SP_PROMOTION.t_org_type_code
  is '终止人所属中心类型编码';
comment on column INSTANT_SP_PROMOTION.t_org_type_name
  is '终止人所属中心类型名称';
comment on column INSTANT_SP_PROMOTION.t_org_id
  is '终止人所属中心ID';
comment on column INSTANT_SP_PROMOTION.t_org_code
  is '终止人所属中心代码';
comment on column INSTANT_SP_PROMOTION.t_org_name
  is '终止人所属中心名称';
comment on column INSTANT_SP_PROMOTION.t_user_id
  is '终止人ID';
comment on column INSTANT_SP_PROMOTION.t_user_name
  is '终止人';
comment on column INSTANT_SP_PROMOTION.deleted_flag
  is '记录删除标志';
comment on column INSTANT_SP_PROMOTION.origin_flag
  is '记录来源标志';
comment on column INSTANT_SP_PROMOTION.origin_app
  is '记录来源应用';
comment on column INSTANT_SP_PROMOTION.created_org_id
  is '记录创建人所在业务单元ID';
comment on column INSTANT_SP_PROMOTION.created_org_name
  is '记录创建人所在业务单元名称';
comment on column INSTANT_SP_PROMOTION.created_user_id
  is '记录创建人ID';
comment on column INSTANT_SP_PROMOTION.created_user_name
  is '记录创建人名称';
comment on column INSTANT_SP_PROMOTION.created_date
  is '记录创建时间';
comment on column INSTANT_SP_PROMOTION.last_upd_org_id
  is '记录修改人所在业务单元';
comment on column INSTANT_SP_PROMOTION.last_upd_by
  is '记录最近修改人ID';
comment on column INSTANT_SP_PROMOTION.last_upd_date
  is '记录最近修改时间';
comment on column INSTANT_SP_PROMOTION.modification_num
  is '记录修改次数';
comment on column INSTANT_SP_PROMOTION.remark
  is '备注';
-- Create/Recreate indexes 
create index IDX_SP_PRO_ORGID on INSTANT_SP_PROMOTION (ORG_ID)
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
create index IDX_SP_PRO_STCODE on INSTANT_SP_PROMOTION (STATUS_CODE)
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
create index IDX_SP_PRO_TYCODE on INSTANT_SP_PROMOTION (TYPE_CODE)
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
-- Create/Recreate primary, unique and foreign key constraints 
alter table INSTANT_SP_PROMOTION
  add constraint PK_INSTANT_SP_PROMOTION primary key (ROW_ID)
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

  
  -- Create table
drop table INSTANT_B_SHOP;
create table INSTANT_B_SHOP
(
  province_center_id         NUMBER not null,
  shop_id                    NUMBER not null,
  shop_no                    VARCHAR2(10) not null,
  shop_name                  VARCHAR2(30) not null,
  owner_id                   NUMBER not null,
  shop_type_code             NUMBER,
  shop_competition_type_code NUMBER not null,
  shop_level_code            NUMBER default 30 not null,
  city_center_id             NUMBER not null,
  city_id                    NUMBER not null,
  suspended_flag             NUMBER default 0 not null,
  shop_status_code           NUMBER default 10 not null,
  status_change_time         DATE default SYSDATE not null,
  del_flag                   NUMBER default 0 not null,
  sale_deputy_flag           NUMBER default 0 not null,
  manager_name               VARCHAR2(100) not null,
  manager_credentials_no     VARCHAR2(200),
  shop_full_name             VARCHAR2(60),
  sale_deputy_id             NUMBER,
  agent_id                   NUMBER,
  section_id                 NUMBER,
  telephone                  VARCHAR2(50),
  address                    VARCHAR2(300),
  descs                      VARCHAR2(4000),
  county_center_id           NUMBER,
  county_id                  NUMBER,
  warehouse_id               NUMBER,
  gps_info                   VARCHAR2(30),
  shop_pre_status_code       NUMBER,
  del_time                   DATE,
  shop_no_reserved           VARCHAR2(10),
  email                      VARCHAR2(100),
  zip_code                   VARCHAR2(100),
  fax                        VARCHAR2(100),
  sr_id                      NUMBER,
  sr_code                    VARCHAR2(32),
  distribute_type_code       VARCHAR2(32),
  distribute_type_name       VARCHAR2(100),
  sale_channel_code1         VARCHAR2(32),
  sale_channel_name1         VARCHAR2(100),
  sale_channel_code2         VARCHAR2(32),
  sale_channel_name2         VARCHAR2(100),
  sale_channel_code3         VARCHAR2(32),
  sale_channel_name3         VARCHAR2(100),
  draw_return_flag           CHAR(1) default 'Y' not null,
  order_submit_flag          CHAR(1) default 'Y' not null,
  confirm_activate_flag      CHAR(1) default 'Y' not null,
  manual_activate_flag       CHAR(1) default 'Y' not null,
  trade_out_flag             CHAR(1) default 'Y' not null,
  wh_id                      NUMBER(18) default -1 not null,
  wh_name                    VARCHAR2(100),
  receiver_name              VARCHAR2(32) default '0',
  receiver_tel               VARCHAR2(32) default '0',
  receiver_address           VARCHAR2(255) default '0',
  interface_param            VARCHAR2(255),
  s_settle_type_code         VARCHAR2(32),
  s_settle_type_name         VARCHAR2(100),
  s_style_code               VARCHAR2(32),
  s_style_name               VARCHAR2(100),
  s_per_setting_id           NUMBER(18) default -1 not null,
  s_percent                  NUMBER(10,6) default 0 not null,
  s_rule_setting_id          NUMBER(18) default -1 not null,
  s_settle_period_name       VARCHAR2(100),
  s_start_date               DATE,
  s_end_date                 DATE,
  a_settle_type_code         VARCHAR2(32),
  a_settle_type_name         VARCHAR2(100),
  a_style_code               VARCHAR2(32),
  a_style_name               VARCHAR2(100),
  a_per_setting_id           NUMBER(18) default -1 not null,
  a_percent                  NUMBER(10,6) default 0 not null,
  a_rule_setting_id          NUMBER(18) default -1 not null,
  a_settle_period_name       VARCHAR2(100),
  a_start_date               DATE,
  a_end_date                 DATE,
  c_max_amt                  NUMBER(22,6) default 0 not null,
  c_min_amt                  NUMBER(22,6) default 0 not null,
  c_confirm_amt              NUMBER(22,6) default 0 not null,
  ua_lock_period             NUMBER(8) default 0 not null,
  ua_lock_times              NUMBER(8) default 0 not null,
  ua_lock_minutes            NUMBER(8) default 0 not null,
  wac_lock_period_code       VARCHAR2(32),
  wac_lock_period_name       VARCHAR2(100),
  wac_lock_times             NUMBER(8) default 0 not null,
  con_wac_time               NUMBER(8) default 0 not null,
  channel_customer_id        NUMBER(18) default -1 not null,
  fc_auto_flag               CHAR(1) default 'Y' not null,
  fc_sr_id                   NUMBER(18) default -1 not null,
  fc_sr_name                 VARCHAR2(100),
  fc_valid_date              DATE,
  fc_period_code             VARCHAR2(32),
  fc_period_name             VARCHAR2(100),
  fc_settle_date             NUMBER(2) default 0 not null,
  fc_min_amt                 NUMBER(22,6) default 0 not null,
  m_settle_type_code         VARCHAR2(32),
  m_settle_type_name         VARCHAR2(100),
  m_style_code               VARCHAR2(32),
  m_style_name               VARCHAR2(100),
  m_per_setting_id           NUMBER(18) default -1 not null,
  m_percent                  NUMBER(10,6) default 0 not null,
  m_rule_setting_id          NUMBER(18) default -1 not null,
  m_settle_period_name       VARCHAR2(100),
  m_start_date               DATE,
  m_end_date                 DATE,
  acc_out_flag               CHAR(1) default 'Y' not null,
  acc_in_flag                CHAR(1) default 'Y' not null,
  acc_out_remain_amt         NUMBER(22,6) default 0 not null,
  acc_trans_remain_amt       NUMBER(22,6) default 0 not null,
  per_trans_max_amt          NUMBER(22,6) default 0 not null,
  day_trans_min_amt          NUMBER(22,6) default 0 not null,
  active_flag                CHAR(1) default 'Y' not null,
  deleted_flag               CHAR(1) default '0' not null,
  origin_flag                CHAR(1),
  origin_app                 VARCHAR2(32),
  created_org_id             NUMBER(18) default -1,
  created_org_name           VARCHAR2(100),
  created_user_id            NUMBER(18) default -1 not null,
  created_user_name          VARCHAR2(100),
  created_date               DATE,
  last_upd_org_id            NUMBER(18) default -1,
  last_upd_by                NUMBER(18) default -1 not null,
  last_upd_date              DATE,
  modification_num           NUMBER(8) default 0 not null,
  remark                     VARCHAR2(255),
  sr_name                    VARCHAR2(32),
  s_settle_period_code       VARCHAR2(32),
  a_settle_period_code       VARCHAR2(32),
  m_settle_period_code       VARCHAR2(32),
  business_psw               VARCHAR2(32)
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
-- Add comments to the table 
comment on table INSTANT_B_SHOP
  is '门店表VER=32.1#
包括门店及销售代表数据';
-- Add comments to the columns 
comment on column INSTANT_B_SHOP.province_center_id
  is '省中心ID';
comment on column INSTANT_B_SHOP.shop_id
  is '门店ID';
comment on column INSTANT_B_SHOP.shop_no
  is '门店编码';
comment on column INSTANT_B_SHOP.shop_name
  is '门店名称';
comment on column INSTANT_B_SHOP.owner_id
  is '业主ID';
comment on column INSTANT_B_SHOP.shop_type_code
  is '门店业务类型（已配值域）';
comment on column INSTANT_B_SHOP.shop_competition_type_code
  is '门店竞争关系类型（已配值域）';
comment on column INSTANT_B_SHOP.shop_level_code
  is '门店级别分类（已配值域）';
comment on column INSTANT_B_SHOP.city_center_id
  is '市级分中心ID';
comment on column INSTANT_B_SHOP.city_id
  is '市ID';
comment on column INSTANT_B_SHOP.suspended_flag
  is '暂停标志 0=未暂停 1=已暂停';
comment on column INSTANT_B_SHOP.shop_status_code
  is '门店状态标志（已配值域）';
comment on column INSTANT_B_SHOP.status_change_time
  is '状态修改时间';
comment on column INSTANT_B_SHOP.del_flag
  is '删除标志 0=未删除1=已删除';
comment on column INSTANT_B_SHOP.sale_deputy_flag
  is '销售代表标识';
comment on column INSTANT_B_SHOP.manager_name
  is '店长姓名';
comment on column INSTANT_B_SHOP.manager_credentials_no
  is '店长证件编号';
comment on column INSTANT_B_SHOP.shop_full_name
  is '门店全称';
comment on column INSTANT_B_SHOP.sale_deputy_id
  is '销售代表ID';
comment on column INSTANT_B_SHOP.agent_id
  is '代理ID';
comment on column INSTANT_B_SHOP.section_id
  is '片区ID';
comment on column INSTANT_B_SHOP.telephone
  is '电话号码（固话或手机）';
comment on column INSTANT_B_SHOP.address
  is '联系地址';
comment on column INSTANT_B_SHOP.descs
  is '描述';
comment on column INSTANT_B_SHOP.county_center_id
  is '县级分中心ID';
comment on column INSTANT_B_SHOP.county_id
  is '县ID';
comment on column INSTANT_B_SHOP.warehouse_id
  is '仓库ID';
comment on column INSTANT_B_SHOP.gps_info
  is 'GPS信息';
comment on column INSTANT_B_SHOP.shop_pre_status_code
  is '之前门店状态标志';
comment on column INSTANT_B_SHOP.del_time
  is '删除时间';
comment on column INSTANT_B_SHOP.shop_no_reserved
  is '预约门店编号';
comment on column INSTANT_B_SHOP.email
  is '电子邮箱';
comment on column INSTANT_B_SHOP.zip_code
  is '邮政编码';
comment on column INSTANT_B_SHOP.fax
  is '传真';
comment on column INSTANT_B_SHOP.sr_id
  is '销售代表ID，针对门店有效。直销模式下，指管理销售代表ID；分销模式下，指销售代表ID。';
comment on column INSTANT_B_SHOP.sr_code
  is '销售代表编码，针对门店有效。直销模式下，指管理销售代表编码；分销模式下，指销售代表编码。';
comment on column INSTANT_B_SHOP.distribute_type_code
  is '分发模式代码 【LOV_CODE：MD_DISTRIBUTE_TYPE】';
comment on column INSTANT_B_SHOP.distribute_type_name
  is '分发模式名称 【LOV_CODE：MD_DISTRIBUTE_TYPE】';
comment on column INSTANT_B_SHOP.sale_channel_code1
  is '销售渠道代码 【LOV_CODE：MD_SALE_CHANNEL1】';
comment on column INSTANT_B_SHOP.sale_channel_name1
  is '销售渠道名称 【LOV_CODE：MD_SALE_CHANNEL1】';
comment on column INSTANT_B_SHOP.sale_channel_code2
  is '销售渠道代码2【LOV_CODE：MD_SALE_CHANNEL2】';
comment on column INSTANT_B_SHOP.sale_channel_name2
  is '销售渠道名称2 【LOV_CODE：MD_SALE_CHANNEL2】';
comment on column INSTANT_B_SHOP.sale_channel_code3
  is '销售渠道代码3【LOV_CODE：MD_SALE_CHANNEL3】';
comment on column INSTANT_B_SHOP.sale_channel_name3
  is '销售渠道名称3 【LOV_CODE：MD_SALE_CHANNEL3】';
comment on column INSTANT_B_SHOP.draw_return_flag
  is '领退票许可 [Y] - 可以; [N] - 不可以';
comment on column INSTANT_B_SHOP.order_submit_flag
  is '订单提交许可 [Y] - 可以; [N] - 不可以';
comment on column INSTANT_B_SHOP.confirm_activate_flag
  is '确认激活许可 [Y] - 可以; [N] - 不可以';
comment on column INSTANT_B_SHOP.manual_activate_flag
  is '手工激活许可 [Y] - 可以; [N] - 不可以';
comment on column INSTANT_B_SHOP.trade_out_flag
  is '出库确认许可 [Y] - 可以; [N] - 不可以';
comment on column INSTANT_B_SHOP.wh_id
  is '订货仓库ID';
comment on column INSTANT_B_SHOP.wh_name
  is '订货仓库名称';
comment on column INSTANT_B_SHOP.receiver_name
  is '收货人';
comment on column INSTANT_B_SHOP.receiver_tel
  is '收货人联系电话';
comment on column INSTANT_B_SHOP.receiver_address
  is '收货地址';
comment on column INSTANT_B_SHOP.interface_param
  is '接口参数';
comment on column INSTANT_B_SHOP.s_settle_type_code
  is '销售佣金结算方式代码 【LOV_CODE：MD_SETTLE_TYPE】';
comment on column INSTANT_B_SHOP.s_settle_type_name
  is '销售佣金结算方式名称 【LOV_CODE：MD_SETTLE_TYPE】';
comment on column INSTANT_B_SHOP.s_style_code
  is '销售佣金模式代码 【LOV_CODE：MD_COMM_STYLE】';
comment on column INSTANT_B_SHOP.s_style_name
  is '销售佣金模式名称 【LOV_CODE：MD_COMM_STYLE】';
comment on column INSTANT_B_SHOP.s_per_setting_id
  is '销售佣金结算比例ID';
comment on column INSTANT_B_SHOP.s_percent
  is '销售佣金结算比例';
comment on column INSTANT_B_SHOP.s_rule_setting_id
  is '销售佣金结算规则ID。对应MD_COMM_RULE_SETTING表ROW_ID字段值';
comment on column INSTANT_B_SHOP.s_settle_period_name
  is '销售佣金结算周期名称。对应MD_COMM_RULE_SETTING表SETTLE_PERIOD_NAME字段值';
comment on column INSTANT_B_SHOP.s_start_date
  is '销售佣金生效时间';
comment on column INSTANT_B_SHOP.s_end_date
  is '销售佣金失效时间';
comment on column INSTANT_B_SHOP.a_settle_type_code
  is '兑奖佣金结算方式代码 【LOV_CODE：MD_SETTLE_TYPE】';
comment on column INSTANT_B_SHOP.a_settle_type_name
  is '兑奖佣金结算方式名称 【LOV_CODE：MD_SETTLE_TYPE】';
comment on column INSTANT_B_SHOP.a_style_code
  is '兑奖佣金模式代码 【LOV_CODE：MD_COMM_STYLE】';
comment on column INSTANT_B_SHOP.a_style_name
  is '兑奖佣金模式名称 【LOV_CODE：MD_COMM_STYLE】';
comment on column INSTANT_B_SHOP.a_per_setting_id
  is '兑奖佣金结算比例ID';
comment on column INSTANT_B_SHOP.a_percent
  is '兑奖佣金结算比例';
comment on column INSTANT_B_SHOP.a_rule_setting_id
  is '兑奖佣金结算规则ID。对应MD_COMM_RULE_SETTING表ROW_ID字段值';
comment on column INSTANT_B_SHOP.a_settle_period_name
  is '兑奖佣金结算周期名称。对应MD_COMM_RULE_SETTING表SETTLE_PERIOD_NAME字段值';
comment on column INSTANT_B_SHOP.a_start_date
  is '兑奖佣金生效时间';
comment on column INSTANT_B_SHOP.a_end_date
  is '兑奖佣金失效时间';
comment on column INSTANT_B_SHOP.c_max_amt
  is '兑奖最高限额';
comment on column INSTANT_B_SHOP.c_min_amt
  is '兑奖最低限额';
comment on column INSTANT_B_SHOP.c_confirm_amt
  is '兑奖支付确认金额';
comment on column INSTANT_B_SHOP.ua_lock_period
  is '未中奖票兑奖锁定周期 单位为分钟';
comment on column INSTANT_B_SHOP.ua_lock_times
  is '未中奖票兑奖锁定次数';
comment on column INSTANT_B_SHOP.ua_lock_minutes
  is '未中奖票兑奖锁定时长 单位为分钟';
comment on column INSTANT_B_SHOP.wac_lock_period_code
  is '【不要了，暂时保留】错误兑奖码锁定周期代码 【LOV_CODE：MD_LOCK_PERIOD】';
comment on column INSTANT_B_SHOP.wac_lock_period_name
  is '【不要了，暂时保留】错误兑奖码锁定周期名称 【LOV_CODE：MD_LOCK_PERIOD】';
comment on column INSTANT_B_SHOP.wac_lock_times
  is '【不要了，暂时保留】错误兑奖码锁定次数';
comment on column INSTANT_B_SHOP.con_wac_time
  is '连续错误兑奖码次数';
comment on column INSTANT_B_SHOP.channel_customer_id
  is '所属大客户ID';
comment on column INSTANT_B_SHOP.fc_auto_flag
  is '是否自动归集。大客户模式下有效，且对SR有效';
comment on column INSTANT_B_SHOP.fc_sr_id
  is '【不要了，暂时保留】资金归集销售代表ID';
comment on column INSTANT_B_SHOP.fc_sr_name
  is '【不要了，暂时保留】资金归集销售代表名称';
comment on column INSTANT_B_SHOP.fc_valid_date
  is '【不要了，暂时保留】资金归集生效时间';
comment on column INSTANT_B_SHOP.fc_period_code
  is '【不要了，暂时保留】资金归集周期代码 【LOV_CODE：MD_ACCUMULATE_PERIOD】';
comment on column INSTANT_B_SHOP.fc_period_name
  is '【不要了，暂时保留】资金归集周期名称 【LOV_CODE：MD_ACCUMULATE_PERIOD】';
comment on column INSTANT_B_SHOP.fc_settle_date
  is '【不要了，暂时保留】资金归集结算日';
comment on column INSTANT_B_SHOP.fc_min_amt
  is '资金归集最低结算额';
comment on column INSTANT_B_SHOP.m_settle_type_code
  is '佣金结算方式代码 【LOV_CODE：MD_SETTLE_TYPE】';
comment on column INSTANT_B_SHOP.m_settle_type_name
  is '佣金结算方式名称 【LOV_CODE：MD_SETTLE_TYPE】';
comment on column INSTANT_B_SHOP.m_style_code
  is '佣金模式代码 【LOV_CODE：MD_COMM_STYLE】';
comment on column INSTANT_B_SHOP.m_style_name
  is '佣金模式名称 【LOV_CODE：MD_COMM_STYLE】';
comment on column INSTANT_B_SHOP.m_per_setting_id
  is '佣金结算比例ID';
comment on column INSTANT_B_SHOP.m_percent
  is '佣金结算比例';
comment on column INSTANT_B_SHOP.m_rule_setting_id
  is '佣金结算规则ID。对应MD_COMM_RULE_SETTING表ROW_ID字段值';
comment on column INSTANT_B_SHOP.m_settle_period_name
  is '佣金结算周期名称。对应MD_COMM_RULE_SETTING表SETTLE_PERIOD_NAME字段值';
comment on column INSTANT_B_SHOP.m_start_date
  is '管理佣金生效时间';
comment on column INSTANT_B_SHOP.m_end_date
  is '管理佣金失效时间';
comment on column INSTANT_B_SHOP.acc_out_flag
  is '账户允许转出标志 [Y] - 允许; [N] - 不允许。针对销售代表有效';
comment on column INSTANT_B_SHOP.acc_in_flag
  is '账户允许转入标志 [Y] - 允许; [N] - 不允许。针对销售代表有效';
comment on column INSTANT_B_SHOP.acc_out_remain_amt
  is '转出保底限额 ';
comment on column INSTANT_B_SHOP.acc_trans_remain_amt
  is '转账保底限额';
comment on column INSTANT_B_SHOP.per_trans_max_amt
  is '单笔转账最高限额';
comment on column INSTANT_B_SHOP.day_trans_min_amt
  is '每日转账最高限额';
comment on column INSTANT_B_SHOP.active_flag
  is '启用状态标志：I：未启用，Y：启用，N：停用';
comment on column INSTANT_B_SHOP.deleted_flag
  is '记录删除标志 [0]-未删除;[1]-逻辑删除';
comment on column INSTANT_B_SHOP.origin_flag
  is '数据来源的标志：[]或[I]-(Input)系统录入;[O]-(Out)外部接口导入;[S]-(System)系统保留。本标志不能挪为它用。';
comment on column INSTANT_B_SHOP.origin_app
  is '数据来源应用的代码';
comment on column INSTANT_B_SHOP.created_org_id
  is '记录创建人所在业务单元ID';
comment on column INSTANT_B_SHOP.created_org_name
  is '记录创建人所在业务单元名称';
comment on column INSTANT_B_SHOP.created_user_id
  is '记录创建人ID';
comment on column INSTANT_B_SHOP.created_user_name
  is '记录创建人名称';
comment on column INSTANT_B_SHOP.created_date
  is '记录创建时间';
comment on column INSTANT_B_SHOP.last_upd_org_id
  is '记录修改人所在业务单元';
comment on column INSTANT_B_SHOP.last_upd_by
  is '记录最近修改人ID';
comment on column INSTANT_B_SHOP.last_upd_date
  is '记录最近修改日期';
comment on column INSTANT_B_SHOP.modification_num
  is '记录修改次数';
comment on column INSTANT_B_SHOP.remark
  is '备注';
comment on column INSTANT_B_SHOP.business_psw
  is '门店交易密码';
-- Create/Recreate indexes 
create index IDX_B_SH_CICE_ID on INSTANT_B_SHOP (CITY_CENTER_ID)
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
create index IDX_B_SH_COCE_ID on INSTANT_B_SHOP (COUNTY_CENTER_ID)
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
create index IDX_B_SH_DISTY_CODE on INSTANT_B_SHOP (DISTRIBUTE_TYPE_CODE)
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
create index IDX_B_SH_FC_SR_ID on INSTANT_B_SHOP (FC_SR_ID)
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
create index IDX_B_SH_PRCE_ID on INSTANT_B_SHOP (PROVINCE_CENTER_ID)
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
create index IDX_B_SH_SHOP_NO on INSTANT_B_SHOP (SHOP_NO)
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
create index IDX_B_SH_SR_ID on INSTANT_B_SHOP (SR_ID)
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
create index IDX_B_SH_WH_ID on INSTANT_B_SHOP (WH_ID)
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
-- Create/Recreate primary, unique and foreign key constraints 
alter table INSTANT_B_SHOP
  add constraint PK_INSTANT_B_SHOP primary key (SHOP_ID)
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

insert into instant_b_shop (PROVINCE_CENTER_ID, SHOP_ID, SHOP_NO, SHOP_NAME, OWNER_ID, SHOP_TYPE_CODE, SHOP_COMPETITION_TYPE_CODE, SHOP_LEVEL_CODE, CITY_CENTER_ID, CITY_ID, SUSPENDED_FLAG, SHOP_STATUS_CODE, STATUS_CHANGE_TIME, DEL_FLAG, SALE_DEPUTY_FLAG, MANAGER_NAME, MANAGER_CREDENTIALS_NO, SHOP_FULL_NAME, SALE_DEPUTY_ID, AGENT_ID, SECTION_ID, TELEPHONE, ADDRESS, DESCS, COUNTY_CENTER_ID, COUNTY_ID, WAREHOUSE_ID, GPS_INFO, SHOP_PRE_STATUS_CODE, DEL_TIME, SHOP_NO_RESERVED, EMAIL, ZIP_CODE, FAX, SR_ID, SR_CODE, DISTRIBUTE_TYPE_CODE, DISTRIBUTE_TYPE_NAME, SALE_CHANNEL_CODE1, SALE_CHANNEL_NAME1, SALE_CHANNEL_CODE2, SALE_CHANNEL_NAME2, SALE_CHANNEL_CODE3, SALE_CHANNEL_NAME3, DRAW_RETURN_FLAG, ORDER_SUBMIT_FLAG, CONFIRM_ACTIVATE_FLAG, MANUAL_ACTIVATE_FLAG, TRADE_OUT_FLAG, WH_ID, WH_NAME, RECEIVER_NAME, RECEIVER_TEL, RECEIVER_ADDRESS, INTERFACE_PARAM, S_SETTLE_TYPE_CODE, S_SETTLE_TYPE_NAME, S_STYLE_CODE, S_STYLE_NAME, S_PER_SETTING_ID, S_PERCENT, S_RULE_SETTING_ID, S_SETTLE_PERIOD_NAME, S_START_DATE, S_END_DATE, A_SETTLE_TYPE_CODE, A_SETTLE_TYPE_NAME, A_STYLE_CODE, A_STYLE_NAME, A_PER_SETTING_ID, A_PERCENT, A_RULE_SETTING_ID, A_SETTLE_PERIOD_NAME, A_START_DATE, A_END_DATE, C_MAX_AMT, C_MIN_AMT, C_CONFIRM_AMT, UA_LOCK_PERIOD, UA_LOCK_TIMES, UA_LOCK_MINUTES, WAC_LOCK_PERIOD_CODE, WAC_LOCK_PERIOD_NAME, WAC_LOCK_TIMES, CON_WAC_TIME, CHANNEL_CUSTOMER_ID, FC_AUTO_FLAG, FC_SR_ID, FC_SR_NAME, FC_VALID_DATE, FC_PERIOD_CODE, FC_PERIOD_NAME, FC_SETTLE_DATE, FC_MIN_AMT, M_SETTLE_TYPE_CODE, M_SETTLE_TYPE_NAME, M_STYLE_CODE, M_STYLE_NAME, M_PER_SETTING_ID, M_PERCENT, M_RULE_SETTING_ID, M_SETTLE_PERIOD_NAME, M_START_DATE, M_END_DATE, ACC_OUT_FLAG, ACC_IN_FLAG, ACC_OUT_REMAIN_AMT, ACC_TRANS_REMAIN_AMT, PER_TRANS_MAX_AMT, DAY_TRANS_MIN_AMT, ACTIVE_FLAG, DELETED_FLAG, ORIGIN_FLAG, ORIGIN_APP, CREATED_ORG_ID, CREATED_ORG_NAME, CREATED_USER_ID, CREATED_USER_NAME, CREATED_DATE, LAST_UPD_ORG_ID, LAST_UPD_BY, LAST_UPD_DATE, MODIFICATION_NUM, REMARK, SR_NAME, S_SETTLE_PERIOD_CODE, A_SETTLE_PERIOD_CODE, M_SETTLE_PERIOD_CODE, BUSINESS_PSW)
values (52, 118311061, '5201100041', '牛昭测试--2', 0, 2, 10, 30, 15, 520160, 0, 10, to_date('06-12-2017 16:13:59', 'dd-mm-yyyy hh24:mi:ss'), 1, 1, '小贵', '520101199001011234', '解振专用SR002', null, null, null, null, '测试用票，终端编号：（）鹏龙大厦21层内部用', null, null, null, null, null, null, to_date('06-12-2017 16:15:34', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, -1, null, '1', '普通分发', null, null, null, null, null, null, 'Y', 'N', 'Y', 'Y', 'N', 32123060, '贵州即开仓库', '小贵', null, '测试用票，终端编号：（）鹏龙大厦21层内部用', null, '1', '系统内', '2', '实时佣金', -1, 0.000000, -1, null, to_date('19-12-2016 14:37:48', 'dd-mm-yyyy hh24:mi:ss'), null, '2', '系统外', '1', '定时佣金', -1, 0.000000, -1, null, null, null, 10001.000000, 1.000000, 2501.000000, 10, 3, 1, null, null, 0, 0, 3.00004566047761E17, 'N', -1, null, null, null, null, 0, 0.000000, '2', '系统外', '1', '定时佣金', -1, 0.000000, -1, null, null, null, 'N', 'N', 0.000000, 0.000000, 0.000000, 0.000000, 'Y', '0', null, null, -1, null, -1, null, to_date('06-12-2017 16:13:59', 'dd-mm-yyyy hh24:mi:ss'), 52, 88882, to_date('06-12-2017 16:13:59', 'dd-mm-yyyy hh24:mi:ss'), 4, null, null, '-1', null, null, null);



