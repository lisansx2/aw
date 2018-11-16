/*门店佣金报表 */
delete from sys_role_resource t where t.resource_id in (83,84,85,86);
delete from sys_resource c where c.id in (83,84,85,86);
/*门店缴款明细报表 */
delete from sys_role_resource t where t.resource_id in (87,88,89,90);
delete from sys_resource c where c.id in (87,88,89,90);
commit;