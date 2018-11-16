package cn.com.cslc.aw.game.domain;
// Generated 2017-5-11 10:43:06 by Hibernate Tools 5.1.2.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.com.cslc.aw.core.common.domain.BaseEntity;
import cn.com.cslc.aw.core.common.domain.SysProvince;
import cn.com.cslc.aw.game.domain.SysGameSystem;

/**
 * SysGame generated by hbm2java
 */
@Entity
@Table(name = "SYS_GAME")
public class SysGame extends BaseEntity {

	private String gameNo;
	private String gameName;
	private String gameShortName;
	
	private SysGameSystem sysGameSystem;
	
	private SysProvince sysProvince;
	
	public SysGame() {
	}

	@Column(name = "GAME_NO", nullable = false, length = 8)
	public String getGameNo() {
		return this.gameNo;
	}

	public void setGameNo(String gameNo) {
		this.gameNo = gameNo;
	}

	@Column(name = "GAME_NAME", nullable = false, length = 45)
	public String getGameName() {
		return this.gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	@Column(name = "GAME_SHORT_NAME", nullable = false, length = 45)
	public String getGameShortName() {
		return this.gameShortName;
	}

	public void setGameShortName(String gameShortName) {
		this.gameShortName = gameShortName;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROVINCE_ID")
	public SysProvince getSysProvince() {
		return this.sysProvince;
	}

	public void setSysProvince(SysProvince sysProvince) {
		this.sysProvince = sysProvince;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SYSTEM_ID")
	public SysGameSystem getSysGameSystem() {
		return sysGameSystem;
	}

	public void setSysGameSystem(SysGameSystem sysGameSystem) {
		this.sysGameSystem = sysGameSystem;
	}
	
	public String combineDropID() {
		return gameNo+"&"+sysGameSystem.getCode();
		
	}
	

}
