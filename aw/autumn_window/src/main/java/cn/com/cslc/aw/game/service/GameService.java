package cn.com.cslc.aw.game.service;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import cn.com.cslc.aw.game.domain.SysGame;
import cn.com.cslc.aw.core.common.domain.SysProvince;
import cn.com.cslc.aw.game.repository.SysGameRepository;
import cn.com.cslc.aw.core.common.service.BaseService;
import cn.com.cslc.aw.game.constant.SysGameSystemConstant;
import cn.com.cslc.aw.game.domain.SysGameSystem;
import cn.com.cslc.aw.game.dto.C_GameDefDto;
import cn.com.cslc.aw.settings.gamesetting.dto.GameSettingDto;
import cn.com.cslc.aw.game.dto.Instant_GmGameAward;
import cn.com.cslc.aw.settings.gamesetting.dto.QueryGameSettingResult;
import cn.com.cslc.aw.game.dto.SysGameProjection;
import cn.com.cslc.aw.game.repository.GameRepository;
import cn.com.cslc.aw.game.repository.SysGameSystemRepository;




@Transactional(readOnly = true)
@Service
public class GameService {

	private static final Logger LOG = LoggerFactory.getLogger(GameService.class);
	
	@Autowired
	private GameRepository gameRepository;
	
	@Autowired
	private SysGameRepository sysGameRepository;
	
	@Autowired
	private BaseService baseService;
	
	@Autowired
	private SysGameSystemRepository sysGameSystemRepository;
	
	public Page<QueryGameSettingResult> queryGameListByProvinceCodes(Set<String> provinceCodesSet, Pageable pageable) {
		return gameRepository.queryGameListByProvinceCodes(provinceCodesSet, pageable);
	}
	
	public List<C_GameDefDto> getGameDtoByProvinceNo(String provinceNo){
		
		return gameRepository.getGameDtoByProvinceNoAndGameNo(provinceNo,null);
	}
	
	public List<C_GameDefDto> getGameDtoByProvinceNoAndGameNo(String provinceNo,String gameNo){
		
		return gameRepository.getGameDtoByProvinceNoAndGameNo(provinceNo,gameNo);
	}
	
	public List<SysGame> findSysGameByProvinceNo(String provinceNo){
		
		return gameRepository.findSysGameByProvinceNo(provinceNo);
	}
	
	public List<Instant_GmGameAward> getInstantGameDtoByProvinceNo(String provinceNo ){
		
		return gameRepository.getInstantGameDtoByProvinceNoAndGameNo(provinceNo,null);
	}
	
	
	public SysGameRepository getSysGameRepository() {
		return sysGameRepository;
	}

	public void setSysGameRepository(SysGameRepository sysGameRepository) {
		this.sysGameRepository = sysGameRepository;
	}
	
	public List<SysGame> getAllSysGame() {
        return sysGameRepository.findAll();
    }
	
	public List<SysGame>  findAllInstantGames(){
		return sysGameRepository.findAllInstantGames();
	}
	
	public List<SysGame>  findAllElectronGames(){
		return sysGameRepository.findAllElectronGames();
	}
    
    public List<SysGame> getAllSysGameByProvinceId(Long provinceId) {
        return sysGameRepository.findByProvinceId(provinceId);
    }
    
    public List<SysGame> findSysGameByProvinceNo(Set<String> ProvinceCodeSet){
    	 return sysGameRepository.findSysGameByProvinceNo(ProvinceCodeSet);
    	
    }

    public SysGame getSysGameByGameNo(String gameNo) {
        return sysGameRepository.findByGameNo(gameNo).get(0);
    }
    
    public List<SysGame> findNonInstantGames(){	
    	return gameRepository.findNonInstantGames();
    }
    
	
	public List<SysGameProjection> findSysGameByProvinceNos(Set<String> userProvinceCodeSet) {
		return gameRepository.findDistinctGameByProvinceNos(userProvinceCodeSet);
	}
	
	public List<SysGameProjection> findDistinctNonInstantGameByProvinceNos(Set<String> userProvinceCodeSet){
		return gameRepository.findDistinctNonInstantGameByProvinceNos(userProvinceCodeSet);
	}
	
	/**
	 * 此方法需要重新实现
	 * @param ProvinceCodeSet
	 * @return
	 */
    public List<SysGameProjection> findInstantGameByByProvinceNos(Set<String> ProvinceCodeSet) {
    	
    	return gameRepository.findDistinctInstantGameByByProvinces(ProvinceCodeSet);
    }
	
	public List<GameSettingDto> getAllGamesByProvinceNo(String provinceNo){
		
		List<C_GameDefDto> c_games= gameRepository.getGameDtoByProvinceNoAndGameNo(provinceNo,null);
		
		List<Instant_GmGameAward> instantGames=gameRepository.getInstantGameDtoByProvinceNoAndGameNo(provinceNo,null);
		
		List<GameSettingDto> gameSettingDtos= Lists.newArrayList();
		for(C_GameDefDto gameDefDto :c_games){
			GameSettingDto gameSettingDto=new GameSettingDto();
			gameSettingDto.setGameName(gameDefDto.getGameName());
			gameSettingDto.setGameNo(gameDefDto.getGameNo());
			gameSettingDto.setGameSystemNo(SysGameSystemConstant.ElectronGame.getCode());
			gameSettingDtos.add(gameSettingDto);	
		}
		for(Instant_GmGameAward InstantGameDto :instantGames){
			GameSettingDto gameSettingDto=new GameSettingDto();
			gameSettingDto.setGameName(InstantGameDto.getGameName());
			gameSettingDto.setGameNo(InstantGameDto.getGameNo());
			gameSettingDto.setGameSystemNo(SysGameSystemConstant.instantGame.getCode());
			gameSettingDtos.add(gameSettingDto);	
		}
		
		return gameSettingDtos;
	}
	
	
	
	@Transactional
	public void setGame(String provinceNo, Set<String> selectedGameNoSet) {
		List<SysGame> oldGameList = gameRepository.findSysGameByProvinceNo(provinceNo);
		for(SysGame oldGame : oldGameList){
			String comBineId=oldGame.combineDropID();
			if(!selectedGameNoSet.contains(comBineId)){
				gameRepository.delete(oldGame);	
			}else{
				selectedGameNoSet.remove(comBineId);
			}
		}
		
		SysProvince province=baseService.getSysProvinceByNo(provinceNo);
		List<SysGameSystem> gameSystemList=sysGameSystemRepository.findAll();
		for(String gameNo : selectedGameNoSet){
			
			String [] gameInfo =gameNo.split("&");
			SysGame newGame = new SysGame();
			newGame.setSysProvince(province);
			if(gameInfo[1].equals(SysGameSystemConstant.ElectronGame.getCode())){
				List<C_GameDefDto> gameList= 	gameRepository.getGameDtoByProvinceNoAndGameNo(provinceNo,gameInfo[0]);
				newGame.setGameName(gameList.get(0).getGameName());
				newGame.setGameNo(gameInfo[0]);
				newGame.setGameShortName(gameList.get(0).getGameShortName());
				newGame.setSysGameSystem(getGameSystem(gameSystemList,SysGameSystemConstant.ElectronGame.getCode()));
			}
			if(gameInfo[1].equals(SysGameSystemConstant.instantGame.getCode())){
				List<Instant_GmGameAward> instantGames=gameRepository.getInstantGameDtoByProvinceNoAndGameNo(provinceNo,gameInfo[0]);
				newGame.setGameName(instantGames.get(0).getGameName());
				newGame.setGameNo(gameInfo[0]);
				newGame.setGameShortName(instantGames.get(0).getGameName());
				newGame.setSysGameSystem(getGameSystem(gameSystemList,SysGameSystemConstant.instantGame.getCode()));
			}
			gameRepository.save(newGame);
		}
	}
	
	
	private SysGameSystem getGameSystem(List<SysGameSystem> gameSystems,String systemCode){
		for(SysGameSystem system :gameSystems){
			if(system.getCode().equals(systemCode)){
				return system;
			}
		}
		return null;
	}
}
