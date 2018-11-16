package cn.com.cslc.aw.reports.shopinstantpromotion.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cn.com.cslc.aw.reports.shopinstantpromotion.dto.InstantPromotion;
import cn.com.cslc.aw.reports.shopinstantpromotion.dto.QueryShopInstantPromotionCondition;
import cn.com.cslc.aw.reports.shopinstantpromotion.dto.QueryShopInstantPromotionResult;
import cn.com.cslc.aw.reports.shopinstantpromotion.repository.ShopInstantPromotionReportRepository;

@Service
public class ShopInstantPromotionReportService {
	
	@Autowired
	private ShopInstantPromotionReportRepository shopInstantPromotionReportRepository;
	

	
	public Page<QueryShopInstantPromotionResult> queryByCondition(QueryShopInstantPromotionCondition queryCondition,
			Pageable pageRequest) {
		return shopInstantPromotionReportRepository.queryByCondition(queryCondition, pageRequest);
	}
	
	public List<InstantPromotion> findPromotionByCustomerCodes(Set<String> userCustomerCodeSet){
		return shopInstantPromotionReportRepository.findPromotionByCustomerCodes(userCustomerCodeSet);
	}
	
	public InstantPromotion findPromotionByPromotionCode(String promotionCode){
		return shopInstantPromotionReportRepository.findPromotionByPromotionCode(promotionCode);
	}

}
