package cn.com.cslc.aw.core.common.dto;

public class CityDto {
	private Long cityId;
	private String cityNo;
	private String cityName;
	private boolean capitalFlag;
	private String provinceName;
	public Long getCityId() {
		return cityId;
	}
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	public String getCityNo() {
		return cityNo;
	}
	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public boolean isCapitalFlag() {
		return capitalFlag;
	}
	public void setCapitalFlag(boolean capitalFlag) {
		this.capitalFlag = capitalFlag;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	
}
