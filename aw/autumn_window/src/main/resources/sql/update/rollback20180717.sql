delete from sys_org c where c.province_id in (select b.id from sys_province b  where b.id not in (1, 20, 12, 6, 23, 11, 16));
delete from SYS_ROLE_RESOURCE c where c.id in (163,166,167,170,171,199,200,201,202,203);
delete from sys_resource c where c.id in (65,66,67,68,69,78,79,80,81,82);
commit;