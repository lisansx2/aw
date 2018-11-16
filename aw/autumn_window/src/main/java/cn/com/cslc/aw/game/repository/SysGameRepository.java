package cn.com.cslc.aw.game.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.com.cslc.aw.game.domain.SysGame;
import cn.com.cslc.aw.core.common.repository.BaseRepository;

public interface SysGameRepository extends BaseRepository<SysGame> {
	@Query("select c from SysGame c where c.sysProvince.provinceNo=00 ")
	List<SysGame> findAll();
	
	@Query("select c from SysGame c where c.sysProvince.provinceNo=00 and c.sysGameSystem.code='20'")
	List<SysGame> findAllInstantGames();
	
	@Query("select c from SysGame c where c.sysProvince.provinceNo=00 and c.sysGameSystem.code='10'")
	List<SysGame> findAllElectronGames();
	

	@Query("select c from SysGame c where c.sysProvince.id=:provinceId")
	List<SysGame> findByProvinceId(@Param("provinceId") Long provinceId);
	
	List<SysGame> findByGameNo(@Param("gameNo") String gameNo);

	@Query("select DISTINCT(s) from SysGame s join s.sysProvince  where s.sysProvince.provinceNo in (:ProvinceCodeSet)")
	List<SysGame> findSysGameByProvinceNo(@Param("ProvinceCodeSet") Set<String> ProvinceCodeSet);
	
}