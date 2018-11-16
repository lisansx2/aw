package cn.com.cslc.aw.game.dto;

import java.math.BigDecimal;

public class Instant_GmGameAward {


	private BigDecimal provinceCenterId;
	
	private String provinceOrgCode;
	
	private String gameNo;
	
	private String gameName;
	

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

	public String getProvinceOrgCode() {
		return provinceOrgCode;
	}

	public void setProvinceOrgCode(String provinceOrgCode) {
		this.provinceOrgCode = provinceOrgCode;
	}

	
}
