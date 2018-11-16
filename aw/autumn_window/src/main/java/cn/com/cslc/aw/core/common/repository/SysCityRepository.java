package cn.com.cslc.aw.core.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.com.cslc.aw.core.common.domain.SysCity;

public interface SysCityRepository extends BaseRepository<SysCity> {
	@Query("select c from SysCity c where c.sysProvince.id=:provinceId")
	List<SysCity> findByProvinceId(@Param("provinceId") Long provinceId);
	
	SysCity findByCityNo(String  cityNo);
}
