package cn.com.cslc.aw.game.repository;


import cn.com.cslc.aw.core.common.repository.BaseRepository;
import cn.com.cslc.aw.game.domain.SysGameSystem;

public interface SysGameSystemRepository extends BaseRepository<SysGameSystem>{
		
	SysGameSystem findByCode(String code);
}
