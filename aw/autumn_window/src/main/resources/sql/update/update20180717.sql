
insert into SYS_RESOURCE (ID, NAME, IDENTIFIER, URL, PARENT_ID, PARENT_IDS, WEIGHT, HAS_CHILDREN, IS_SHOW, ICON, RESOURCE_TYPE_ID)
values (65, '机构管理', 'orgManage', '/org', 1, '0/1/', 2, 0, null, 'fa fa-sitemap fa-fw', 1);

insert into SYS_RESOURCE (ID, NAME, IDENTIFIER, URL, PARENT_ID, PARENT_IDS, WEIGHT, HAS_CHILDREN, IS_SHOW, ICON, RESOURCE_TYPE_ID)
values (66, '查询机构列表', 'searchOrgApi', '/api/org/search', 65, '0/1/65', 0, 0, null, null, 4);

insert into SYS_RESOURCE (ID, NAME, IDENTIFIER, URL, PARENT_ID, PARENT_IDS, WEIGHT, HAS_CHILDREN, IS_SHOW, ICON, RESOURCE_TYPE_ID)
values (67, '修改机构', 'orgUpdate', '/org/orgUpdate', 65, '0/1/65', 0, 0, null, null, 3);

insert into SYS_RESOURCE (ID, NAME, IDENTIFIER, URL, PARENT_ID, PARENT_IDS, WEIGHT, HAS_CHILDREN, IS_SHOW, ICON, RESOURCE_TYPE_ID)
values (68, '新增机构', 'orgAdd', '/org/orgAdd', 65, '0/1/65', 0, 0, null, null, 3);

insert into SYS_RESOURCE (ID, NAME, IDENTIFIER, URL, PARENT_ID, PARENT_IDS, WEIGHT, HAS_CHILDREN, IS_SHOW, ICON, RESOURCE_TYPE_ID)
values (69, '机构权限', 'orgAuth', '/org/orgAuth', 65, '0/1/65', 0, 0, null, null, 3);

insert into SYS_RESOURCE (ID, NAME, IDENTIFIER, URL, PARENT_ID, PARENT_IDS, WEIGHT, HAS_CHILDREN, IS_SHOW, ICON, RESOURCE_TYPE_ID)
values (78, '机构权限', 'getOrgCode2Tree', '/api/common/org/getOrgCode2Tree/*', 65, '0/1/65', 0, 0, null, null, 3);

insert into SYS_RESOURCE (ID, NAME, IDENTIFIER, URL, PARENT_ID, PARENT_IDS, WEIGHT, HAS_CHILDREN, IS_SHOW, ICON, RESOURCE_TYPE_ID)
values (79, '机构权限', 'getOrgCodeNext', '/api/org/getOrgCodeNext/*', 65, '0/1/65', 0, 0, null, null, 3);


insert into SYS_RESOURCE (ID, NAME, IDENTIFIER, URL, PARENT_ID, PARENT_IDS, WEIGHT, HAS_CHILDREN, IS_SHOW, ICON, RESOURCE_TYPE_ID)
values (80, '机构权限', 'orgDelCheck', '/api/org/orgDelCheck/*', 65, '0/1/65', 0, 0, null, null, 3);

insert into SYS_RESOURCE (ID, NAME, IDENTIFIER, URL, PARENT_ID, PARENT_IDS, WEIGHT, HAS_CHILDREN, IS_SHOW, ICON, RESOURCE_TYPE_ID)
values (81, '机构权限', 'orgDel', '/org/orgDel', 65, '0/1/65', 0, 0, null, null, 3);

insert into SYS_RESOURCE (ID, NAME, IDENTIFIER, URL, PARENT_ID, PARENT_IDS, WEIGHT, HAS_CHILDREN, IS_SHOW, ICON, RESOURCE_TYPE_ID)
values (82, '机构权限', 'orgDelCheck1', '/api/org/orgDelCheck1/*', 65, '0/1/65', 0, 0, null, null, 3);

insert into sys_org (ID, NAME, PARENT_ID, PARENT_IDS, IS_SHOW, HAS_CHILDREN, WEIGHT, CODE, PARENT_CODE, PROVINCE_ID, ORG_TYPE_ID, DEL_TIME, UPDATE_TIME, DEL_FLAG)
select aw_sequence.nextval, concat(c.province_name , '省中心'), '2', '0/1/2', 1, 1, c.province_no,concat( '000' ,c.province_no), '000', c.id, 1, null, null, null
  from sys_province c
 where c.id not in (1, 20, 12, 6, 23, 11, 16);


insert into SYS_ROLE_RESOURCE (ID, ROLE_ID, RESOURCE_ID)
values (163, 1, 65);

insert into SYS_ROLE_RESOURCE (ID, ROLE_ID, RESOURCE_ID)
values (166, 1, 66);

insert into SYS_ROLE_RESOURCE (ID, ROLE_ID, RESOURCE_ID)
values (167, 1, 67);

insert into SYS_ROLE_RESOURCE (ID, ROLE_ID, RESOURCE_ID)
values (170, 1, 68);

insert into SYS_ROLE_RESOURCE (ID, ROLE_ID, RESOURCE_ID)
values (171, 1, 69);

insert into SYS_ROLE_RESOURCE (ID, ROLE_ID, RESOURCE_ID)
values (199, 1, 78);

insert into SYS_ROLE_RESOURCE (ID, ROLE_ID, RESOURCE_ID)
values (200, 1, 79);

insert into SYS_ROLE_RESOURCE (ID, ROLE_ID, RESOURCE_ID)
values (201, 1, 80);

insert into SYS_ROLE_RESOURCE (ID, ROLE_ID, RESOURCE_ID)
values (202, 1, 81);

insert into SYS_ROLE_RESOURCE (ID, ROLE_ID, RESOURCE_ID)
values (203, 1, 82);


commit;