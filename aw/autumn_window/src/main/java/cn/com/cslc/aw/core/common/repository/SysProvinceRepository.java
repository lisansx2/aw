package cn.com.cslc.aw.core.common.repository;

import cn.com.cslc.aw.core.common.domain.SysProvince;

public interface SysProvinceRepository extends BaseRepository<SysProvince> {
	
/*	@Query("select c from SysProvince c where c.provinceNo <> 00")
	List<SysProvince> findAll();*/
	
	SysProvince findByProvinceNo(String  provinceNo);
}
