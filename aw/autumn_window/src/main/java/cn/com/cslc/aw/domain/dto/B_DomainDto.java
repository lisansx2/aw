package cn.com.cslc.aw.domain.dto;

import java.math.BigDecimal;

public class B_DomainDto {

	private BigDecimal domKey	;
	private String domValue	;

	public BigDecimal getDomKey() {
		return domKey;
	}

	public void setDomKey(BigDecimal domKey) {
		this.domKey = domKey;
	}

	public String getDomValue() {
		return domValue;
	}

	public void setDomValue(String domValue) {
		this.domValue = domValue;
	}
}
