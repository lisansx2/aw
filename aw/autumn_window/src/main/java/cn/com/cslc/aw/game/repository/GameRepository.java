package cn.com.cslc.aw.game.repository;


import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.com.cslc.aw.game.domain.SysGame;
import cn.com.cslc.aw.core.common.repository.BaseRepository;
import cn.com.cslc.aw.game.dto.SysGameProjection;

public interface GameRepository extends BaseRepository<SysGame>,GameRepositoryCustom {
	
	@Query("select s from SysGame s join fetch s.sysProvince join fetch s.sysGameSystem  where s.sysProvince.provinceNo=:provinceNo")
	List<SysGame> findSysGameByProvinceNo(@Param("provinceNo") String provinceNo);
	
	@Query("select DISTINCT s.gameNo as gameNo,s.gameName as gameName from SysGame s join s.sysProvince  where s.sysProvince.provinceNo in (:ProvinceCodeSet) and s.sysGameSystem.code='10'")
	List<SysGameProjection> findDistinctGameByProvinceNos(@Param("ProvinceCodeSet") Set<String> provinceCodeSet);
	
	@Query("select DISTINCT s.gameNo as gameNo,s.gameName as gameName from SysGame s join s.sysProvince  where s.sysProvince.provinceNo in (:ProvinceCodeSet) and s.sysGameSystem.code='20'")
	List<SysGameProjection> findDistinctInstantGameByByProvinces(@Param("ProvinceCodeSet") Set<String> ProvinceCodeSet);
	
	
	@Query("select c from SysGame c where c.sysProvince.provinceNo=00 and c.sysGameSystem.code<>'20'")
	List<SysGame> findNonInstantGames();
	
	@Query("select DISTINCT s.gameNo as gameNo,s.gameName as gameName from SysGame s join s.sysProvince  where s.sysProvince.provinceNo in (:ProvinceCodeSet) and s.sysGameSystem.code<>'20'")
	List<SysGameProjection> findDistinctNonInstantGameByProvinceNos(@Param("ProvinceCodeSet") Set<String> provinceCodeSet);

}
