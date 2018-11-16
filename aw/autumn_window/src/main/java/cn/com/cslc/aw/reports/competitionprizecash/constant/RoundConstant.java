package cn.com.cslc.aw.reports.competitionprizecash.constant;

public enum RoundConstant {
	ROUND_HALF_UP("四舍五入", "10"), ROUND_ABANDON("舍尾取整", "20"), ROUND_UP("进位取整","30");  
    private String name;  
    private String code;  

    private RoundConstant(String name, String code) {  
        this.name = name;  
        this.code = code;  
    }  
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}  
}
