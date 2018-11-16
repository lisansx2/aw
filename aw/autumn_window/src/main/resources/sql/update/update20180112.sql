--1、删除原有表结构


--2、新建的表结构




--3、初始化数据


--竞彩兑奖报表
delete from SYS_ROLE_RESOURCE t where t.RESOURCE_ID in(59,60,61,62);
delete from sys_resource t where t.ID in(59,60,61,62);
insert into sys_resource (ID, NAME, IDENTIFIER, URL, PARENT_ID, PARENT_IDS, WEIGHT, HAS_CHILDREN, IS_SHOW, ICON, RESOURCE_TYPE_ID)
values (59, '门店竞彩兑奖报表', 'competitionPrizeCashReport', '/reports/competitionPrizeCashReport', 12, '0/1/12/', 6, 0, null, 'fa fa-square fa-fw', 1);

insert into sys_resource (ID, NAME, IDENTIFIER, URL, PARENT_ID, PARENT_IDS, WEIGHT, HAS_CHILDREN, IS_SHOW, ICON, RESOURCE_TYPE_ID)
values (60, '查询竞彩兑奖报表信息api', 'competitionPrizeCashApi', '/api/reports/competitionPrizeCashReport/search', 59, '0/1/12/59', 0, 0, null, null, 4);

insert into sys_resource (ID, NAME, IDENTIFIER, URL, PARENT_ID, PARENT_IDS, WEIGHT, HAS_CHILDREN, IS_SHOW, ICON, RESOURCE_TYPE_ID)
values (61, '导出pdf格式的竞彩兑奖报表', 'exportPdfCompetitionPrizeCashReport', '/reports/competitionPrizeCashReport/exportFile/*', 59, '0/1/12/59', 0, 0, null, null, 2);

insert into sys_resource (ID, NAME, IDENTIFIER, URL, PARENT_ID, PARENT_IDS, WEIGHT, HAS_CHILDREN, IS_SHOW, ICON, RESOURCE_TYPE_ID)
values (62, '导出excel格式的竞彩兑奖报表', 'exportExcelCompetitionPrizeCashReport', '/reports/competitionPrizeCashReport/exportFile/*', 59, '0/1/12/59', 0, 0, null, null, 2);


insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (144, 1, 59);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (145, 1, 60);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (146, 1, 61);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (147, 1, 62);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (148, 2, 59);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (149, 2, 60);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (150, 2, 61);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (151, 2, 62);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (152, 3, 59);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (153, 3, 60);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (154, 3, 61);

insert into sys_role_resource (ID, ROLE_ID, RESOURCE_ID)
values (155, 3, 62);



commit;