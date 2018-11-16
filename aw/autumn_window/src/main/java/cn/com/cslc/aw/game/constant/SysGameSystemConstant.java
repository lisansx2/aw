package cn.com.cslc.aw.game.constant;

public enum SysGameSystemConstant {
	ElectronGame("电彩游戏", "10"), instantGame("即开游戏", "20");  
    private String name;  
    private String code;  

    private SysGameSystemConstant(String name, String code) {  
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
