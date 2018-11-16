/*门店佣金报表 begin*/
delete from sys_role_resource t where t.resource_id in (83,84,85,86);
delete from sys_resource c where c.id in (83,84,85,86);


insert into sys_resource (ID, NAME, IDENTIFIER, URL, PARENT_ID, PARENT_IDS, WEIGHT, HAS_CHILDREN, IS_SHOW, ICON, RESOURCE_TYPE_ID)
values (83, '门店佣金报表', 'shopCommissionReport', '/reports/shopCommissionReport', 12, '0/1/12/', 9, 0, null, 'fa fa-square fa-fw', 1);

insert into sys_resource (ID, NAME, IDENTIFIER, URL, PARENT_ID, PARENT_IDS, WEIGHT, HAS_CHILDREN, IS_SHOW, ICON, RESOURCE_TYPE_ID)
values (84, '查询门店佣金api', 'searchShopCommissionReportApi', '/api/reports/shopCommissionReport/search', 83, '0/1/12/83', 0, 0, null, null, 4);

insert into sys_resource (ID, NAME, IDENTIFIER, URL, PARENT_ID, PARENT_IDS, WEIGHT, HAS_CHILDREN, IS_SHOW, ICON, RESOURCE_TYPE_ID)
values (85, '导出pdf格式的门店佣金报表', 'exportPdfShopCommissionReport', '/reports/shopCommissionReport/exportFile/*', 83, '0/1/12/83', 0, 0, null, null, 2);

insert into sys_resource (ID, NAME, IDENTIFIER, URL, PARENT_ID, PARENT_IDS, WEIGHT, HAS_CHILDREN, IS_SHOW, ICON, RESOURCE_TYPE_ID)
values (86, '导出excel格式的门店佣金报表', 'exportExcelShopCommissionReport', '/reports/shopCommissionReport/exportFile/*', 83, '0/1/12/83', 0, 0, null, null, 2);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (204, 3, 83);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (205, 1, 83);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (206, 2, 83);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (207, 3, 84);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (208, 1, 84);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (209, 2, 84);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (210, 3, 85);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (211, 1, 85);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (212, 2, 85);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (213, 3, 86);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (214, 1, 86);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (215, 2, 86);
/*门店佣金报表 end*/

/*代理缴款明细报表 begin*/
delete from sys_role_resource t where t.resource_id in (87,88,89,90);
delete from sys_resource c where c.id in (87,88,89,90);

insert into sys_resource (ID, NAME, IDENTIFIER, URL, PARENT_ID, PARENT_IDS, WEIGHT, HAS_CHILDREN, IS_SHOW, ICON, RESOURCE_TYPE_ID)
values (87, '代理缴款明细报表', 'shopPaymentReport', '/reports/shopPaymentReport', 12, '0/1/12/', 10, 0, null, 'fa fa-square fa-fw', 1);

insert into sys_resource (ID, NAME, IDENTIFIER, URL, PARENT_ID, PARENT_IDS, WEIGHT, HAS_CHILDREN, IS_SHOW, ICON, RESOURCE_TYPE_ID)
values (88, '查询代理缴款明细api', 'searchShopPaymentReportApi', '/api/reports/shopPaymentReport/search', 87, '0/1/12/87', 0, 0, null, null, 4);

insert into sys_resource (ID, NAME, IDENTIFIER, URL, PARENT_ID, PARENT_IDS, WEIGHT, HAS_CHILDREN, IS_SHOW, ICON, RESOURCE_TYPE_ID)
values (89, '导出pdf格式的代理缴款明细报表', 'exportPdfShopPaymentReport', '/reports/shopPaymentReport/exportFile/*', 87, '0/1/12/87', 0, 0, null, null, 2);

insert into sys_resource (ID, NAME, IDENTIFIER, URL, PARENT_ID, PARENT_IDS, WEIGHT, HAS_CHILDREN, IS_SHOW, ICON, RESOURCE_TYPE_ID)
values (90, '导出excel格式的代理缴款明细报表', 'exportExcelShopPaymentReport', '/reports/shopPaymentReport/exportFile/*', 87, '0/1/12/87', 0, 0, null, null, 2);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (216, 3, 87);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (217, 1, 87);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (218, 2, 87);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (219, 3, 88);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (220, 1, 88);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (221, 2, 88);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (222, 3, 89);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (223, 1, 89);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (224, 2, 89);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (225, 3, 90);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (226, 1, 90);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (227, 2, 90);
/*代理缴款明细报表 end*/

commit;