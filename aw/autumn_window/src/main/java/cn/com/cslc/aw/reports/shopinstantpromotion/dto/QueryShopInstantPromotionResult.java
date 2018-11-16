package cn.com.cslc.aw.reports.shopinstantpromotion.dto;

import java.math.BigDecimal;

public class QueryShopInstantPromotionResult {

	private String shopNo;
	
	private String orgName;
	
	private String promotionName;
	
	private BigDecimal sumBonus;
	
	
	/*
	 * 		{ "data": "shopNo", "title":"门店编号" },
    			{ "data": "orgName", "title":"所属机构" },
    			{ "data": "paidPromotionName" ,"title":"促销活动名称"},
    			{ "data": "paidPromotionAmountSum" ,"title":"促销兑奖金额（元）"}
	 * 
	 * 
	 * */

	public String getShopNo() {
		return shopNo;
	}

	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getPromotionName() {
		return promotionName;
	}

	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}

	public BigDecimal getSumBonus() {
		return sumBonus;
	}

	public void setSumBonus(BigDecimal sumBonus) {
		this.sumBonus = sumBonus;
	}

	
}
