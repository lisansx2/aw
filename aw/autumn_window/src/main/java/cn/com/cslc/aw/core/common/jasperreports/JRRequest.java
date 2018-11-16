package cn.com.cslc.aw.core.common.jasperreports;

import java.util.Map;

import com.google.common.collect.Maps;

public class JRRequest {

	private String reportName;

	private String reportFormat ="xls";

	private Map<String, String> reportParamMap = Maps.newHashMap();

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getReportFormat() {
		return reportFormat;
	}

	public void setReportFormat(String reportFormat) {
		this.reportFormat = reportFormat;
	}

	public Map<String, String> getReportParamMap() {
		return reportParamMap;
	}

	public void setReportParamMap(Map<String, String> reportParamMap) {
		this.reportParamMap = reportParamMap;
	}

}
