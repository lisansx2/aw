package cn.com.cslc.aw.game.dto;

import java.math.BigDecimal;

public class C_GameDefDto {

	private BigDecimal provinceCenterId;
	
	private String gameNo;
	
	private String gameName;
	
	private String gameShortName;
	
	private BigDecimal gameStatusCode;

	public BigDecimal getProvinceCenterId() {
		return provinceCenterId;
	}

	public void setProvinceCenterId(BigDecimal provinceCenterId) {
		this.provinceCenterId = provinceCenterId;
	}

	public String getGameNo() {
		return gameNo;
	}

	public void setGameNo(String gameNo) {
		this.gameNo = gameNo;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public BigDecimal getGameStatusCode() {
		return gameStatusCode;
	}

	public void setGameStatusCode(BigDecimal gameStatusCode) {
		this.gameStatusCode = gameStatusCode;
	}

	public String getGameShortName() {
		return gameShortName;
	}

	public void setGameShortName(String gameShortName) {
		this.gameShortName = gameShortName;
	} 
	
	
	
	

	
}
