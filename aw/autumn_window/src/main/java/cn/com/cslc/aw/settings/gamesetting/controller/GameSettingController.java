package cn.com.cslc.aw.settings.gamesetting.controller;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import cn.com.cslc.aw.core.common.controller.BaseController;
import cn.com.cslc.aw.game.domain.SysGame;
import cn.com.cslc.aw.core.common.domain.SysUser;
import cn.com.cslc.aw.settings.gamesetting.dto.GameSettingDefinition;
import cn.com.cslc.aw.settings.gamesetting.dto.GameSettingDto;
import cn.com.cslc.aw.settings.gamesetting.dto.GameSettingResult;
import cn.com.cslc.aw.game.service.GameService;



@Controller
@RequestMapping("/settings/gameSetting")
public class GameSettingController extends BaseController{

	private static final Logger LOG = LoggerFactory.getLogger(GameSettingController.class);
	
	private String indexViewName = "redirect:/settings/gameSetting";
	
	@Autowired
	private GameService gameService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String showGameSettingIndex(Model model){
		return "settings/gameSetting/gameSettingIndex";
	}
	
	
	@RequestMapping(value="/setGame",method=RequestMethod.GET)
	public String showGameSetting(@RequestParam(value="provinceNo",required=true) String provinceNo,  final SysUser user, Model model){
		
			String viewPath = "/settings/gameSetting/setGame";

			GameSettingResult gameSettingResult =new GameSettingResult();
			List<GameSettingDefinition> gameSettingDefList = Lists.newArrayList();
			
			List<GameSettingDto> allGameDtoList= gameService.getAllGamesByProvinceNo(provinceNo);
			
			for(GameSettingDto gameDto :allGameDtoList){
				GameSettingDefinition gameSettingDefinition = new GameSettingDefinition();
				gameSettingDefinition.setGameName(gameDto.getGameName());
				gameSettingDefinition.setGameNo(gameDto.getGameNo()+"&"+gameDto.getGameSystemNo());
				gameSettingDefinition.setIsSelected(Boolean.FALSE);
				gameSettingDefList.add(gameSettingDefinition);
			}
			
			for(SysGame sysGame : gameService.findSysGameByProvinceNo(provinceNo)){
				for(GameSettingDefinition gameSettingDefinition : gameSettingDefList){
					if(gameSettingDefinition.getGameNo().equals(sysGame.getGameNo()+"&"+sysGame.getSysGameSystem().getCode()) ){
						gameSettingDefinition.setIsSelected(Boolean.TRUE);
						gameSettingDefinition.setGameId(sysGame.getId());
					}
				}
			}

			gameSettingResult.setGameSettingDefList(gameSettingDefList);
			gameSettingResult.setSysProvince(baseService.getSysProvinceByNo(provinceNo));
			model.addAttribute(gameSettingResult);
			
		return viewPath;
	}
	
	@RequestMapping(value="/setGame",method=RequestMethod.POST)
	public String setGame(@RequestParam(value="sysProvince.provinceNo",required=true) String provinceNo, @RequestParam(value="selectedGameList",required=false) String[] selectedGameNoArr,RedirectAttributes model){
		Set<String> selectedGameNoSet = Sets.newHashSet();
		if(selectedGameNoArr !=null){
			selectedGameNoSet = Sets.newHashSet(selectedGameNoArr);
		}
		
		gameService.setGame(provinceNo, selectedGameNoSet);
		model.addFlashAttribute("success", Boolean.TRUE);
		model.addFlashAttribute("message", "设置游戏操作成功！");
		return getIndexViewName();
	}
	
	
	
	
	@Override
	protected String getIndexViewName() {
		// TODO Auto-generated method stub
		return indexViewName;
	}

}
