package cn.com.cslc.aw.customer.repository;


import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.com.cslc.aw.core.common.repository.BaseRepository;
import cn.com.cslc.aw.customer.domain.SysCustomer;

public interface CustomerRepository extends BaseRepository<SysCustomer>,CustomerRepositoryCustom{

	 @Query("select s from SysCustomer s join fetch s.sysProvince where s.sysProvince.provinceNo= :provinceNo")
	List<SysCustomer> findByProvinceNo(@Param("provinceNo") String provinceNo);
	 
	 @Query("select s from SysCustomer s join fetch s.sysProvince where s.sysProvince.id= :provinceId")
		List<SysCustomer> findByProvinceId(@Param("provinceId") Long provinceId);

	@Query("select s from SysCustomer s join s.sysOrgCustomers s1 join s1.sysOrg s2 where s2.code in(:orgCodes)")
	List<SysCustomer> findCustomerByOrgCodes(@Param("orgCodes")  Set<String> orgCodes);
	 
}
