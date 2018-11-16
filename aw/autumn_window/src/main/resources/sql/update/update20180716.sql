

delete from sys_resource c where c.id in (70,71);

insert into sys_resource (ID, NAME, IDENTIFIER, URL, PARENT_ID, PARENT_IDS, WEIGHT, HAS_CHILDREN, IS_SHOW, ICON, RESOURCE_TYPE_ID)
values (70, '时段销量报表(自然日)', 'periodSalesReportNatural', '/reports/periodSalesReportNatural', 12, '0/1/12/', 1, 0, null, 'fa fa-square fa-fw', 1);

insert into sys_resource (ID, NAME, IDENTIFIER, URL, PARENT_ID, PARENT_IDS, WEIGHT, HAS_CHILDREN, IS_SHOW, ICON, RESOURCE_TYPE_ID)
values (71, '查询时段销量(自然日)api', 'searchPeriodSalesReportNaturalApi', '/api/reports/periodSalesReportNatural/search', 70, '0/1/12/70', 0, 0, null, null, 4);

insert into sys_resource (ID, NAME, IDENTIFIER, URL, PARENT_ID, PARENT_IDS, WEIGHT, HAS_CHILDREN, IS_SHOW, ICON, RESOURCE_TYPE_ID)
values (72, '导出pdf格式的时段销量报表(自然日)', 'exportPdfPeriodSaleReportNatural', '/reports/periodSalesReportNatural/exportFile/*', 70, '0/1/12/70', 0, 0, null, null, 2);

insert into sys_resource (ID, NAME, IDENTIFIER, URL, PARENT_ID, PARENT_IDS, WEIGHT, HAS_CHILDREN, IS_SHOW, ICON, RESOURCE_TYPE_ID)
values (73, '导出excel格式的时段销量报表(自然日)', 'exportExcelPeriodSaleReportNatural', '/reports/periodSalesReportNatural/exportFile/*', 70, '0/1/12/70', 0, 0, null, null, 2);

insert into sys_resource (ID, NAME, IDENTIFIER, URL, PARENT_ID, PARENT_IDS, WEIGHT, HAS_CHILDREN, IS_SHOW, ICON, RESOURCE_TYPE_ID)
values (74, '门店历史额度记录报表(自然日)', 'shopHistoryBalanceReportNatural', '/reports/shopHistoryBalanceReportNatural', 12, '0/1/12/', 4, 0, null, 'fa fa-square fa-fw', 1);

insert into sys_resource (ID, NAME, IDENTIFIER, URL, PARENT_ID, PARENT_IDS, WEIGHT, HAS_CHILDREN, IS_SHOW, ICON, RESOURCE_TYPE_ID)
values (75, '查询门店历史额度记录(自然日)api', 'searchShopHistoryBalanceNaturalApi', '/api/reports/shopHistoryBalanceReportNatural/search', 74, '0/1/12/74', 0, 0, null, null, 4);

insert into sys_resource (ID, NAME, IDENTIFIER, URL, PARENT_ID, PARENT_IDS, WEIGHT, HAS_CHILDREN, IS_SHOW, ICON, RESOURCE_TYPE_ID)
values (76, '导出pdf格式的门店历史额度记录报表(自然日)', 'exportPdfShopHistoryBalanceReportNatural', '/reports/shopHistoryBalanceReportNatural/exportFile/*', 74, '0/1/12/74', 0, 0, null, null, 2);

insert into sys_resource (ID, NAME, IDENTIFIER, URL, PARENT_ID, PARENT_IDS, WEIGHT, HAS_CHILDREN, IS_SHOW, ICON, RESOURCE_TYPE_ID)
values (77, '导出excel格式的门店历史额度记录报表(自然日)', 'exportExcelShopHistoryBalanceReportNatural', '/reports/shopHistoryBalanceReportNatural/exportFile/*', 74, '0/1/12/74', 0, 0, null, null, 2);

update sys_resource c set c.weight=2,c.name='时段销量报表(业务日)' where c.id =13;
update sys_resource c set c.weight=5,c.name='门店历史额度记录报表(业务日)' where c.id =34;

update sys_resource c set c.weight=3 where c.id=30;
update sys_resource c set c.weight=6 where c.id=51;
update sys_resource c set c.weight=7 where c.id=55;
update sys_resource c set c.weight=8 where c.id=59;

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (175, 3, 70);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (176, 1, 70);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (177, 2, 70);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (178, 3, 71);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (179, 1, 71);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (180, 2, 71);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (181, 3, 72);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (182, 1, 72);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (183, 2, 72);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (184, 3, 73);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (185, 1, 73);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (186, 2, 73);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (187, 3, 74);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (188, 1, 74);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (189, 2, 74);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (190, 3, 75);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (191, 1, 75);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (192, 2, 75);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (193, 3, 76);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (194, 1, 76);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (195, 2, 76);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (196, 3, 77);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (197, 1, 77);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (198, 2, 77);

commit;