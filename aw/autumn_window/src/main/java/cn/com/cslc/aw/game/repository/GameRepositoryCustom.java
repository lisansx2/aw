package cn.com.cslc.aw.game.repository;


import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.com.cslc.aw.game.dto.C_GameDefDto;
import cn.com.cslc.aw.game.dto.Instant_GmGameAward;
import cn.com.cslc.aw.settings.gamesetting.dto.QueryGameSettingResult;



public interface GameRepositoryCustom {
		
		Page<QueryGameSettingResult> queryGameListByProvinceCodes(Set<String> provinceCodesSet, Pageable pageable);
		
		List<C_GameDefDto> getGameDtoByProvinceNoAndGameNo(String provinceNo,String gameNo );
		
		List<Instant_GmGameAward> getInstantGameDtoByProvinceNoAndGameNo(String provinceNo,String gameNo );
		
		
		
}
