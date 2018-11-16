package cn.com.cslc.aw.core.common.constant;

public enum  SysUserStateConstant {
	Enabled("已启用", "10"), Disable("已停用", "20"), Created("新建", "30"), Deleted("删除", "40");  
    private String name;  
    private String code;  

    private SysUserStateConstant(String name, String code) {  
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
