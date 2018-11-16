package cn.com.cslc.aw.settings.gamesetting.dto;

import java.util.List;

import cn.com.cslc.aw.core.common.domain.SysProvince;

public class GameSettingResult {
	private List<GameSettingDefinition> gameDefList;
	
	private SysProvince sysProvince;

	public List<GameSettingDefinition> getGameSettingDefList() {
		return gameDefList;
	}

	public void setGameSettingDefList(List<GameSettingDefinition> gameDefList) {
		this.gameDefList = gameDefList;
	}

	public List<GameSettingDefinition> getGameDefList() {
		return gameDefList;
	}

	public void setGameDefList(List<GameSettingDefinition> gameDefList) {
		this.gameDefList = gameDefList;
	}

	public SysProvince getSysProvince() {
		return sysProvince;
	}

	public void setSysProvince(SysProvince sysProvince) {
		this.sysProvince = sysProvince;
	}
}
