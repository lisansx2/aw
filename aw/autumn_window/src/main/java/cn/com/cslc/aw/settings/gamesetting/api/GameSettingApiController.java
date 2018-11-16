package cn.com.cslc.aw.settings.gamesetting.api;

import static cn.com.cslc.aw.core.common.datatables.DataTablesResponse.generateDTResponse;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import cn.com.cslc.aw.core.common.api.BaseApiController;
import cn.com.cslc.aw.core.common.datatables.DataTablesRequest;
import cn.com.cslc.aw.core.common.datatables.DataTablesRequestParams;
import cn.com.cslc.aw.core.common.datatables.DataTablesResponse;
import cn.com.cslc.aw.settings.gamesetting.dto.QueryGameSettingResult;
import cn.com.cslc.aw.game.service.GameService;


@RestController
@RequestMapping("/api/settings/gameSetting")
public class GameSettingApiController extends BaseApiController{

	@Autowired
	private GameService gameService;
	
	@RequestMapping(value = "/queryGameSetted", method = POST)
	public DataTablesResponse<List<QueryGameSettingResult>> queryGames(@DataTablesRequestParams DataTablesRequest dataTablesRequest, @SessionAttribute Set<String> userProvinceCodeSet) {
		Page<QueryGameSettingResult> page= gameService.queryGameListByProvinceCodes(userProvinceCodeSet, dataTablesRequest.getPageRequest());
		 return generateDTResponse(dataTablesRequest, page);
	}
	
}
