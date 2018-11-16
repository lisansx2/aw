package cn.com.cslc.aw.reports.shopinstantpromotion.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.com.cslc.aw.reports.shopinstantpromotion.dto.InstantPromotion;
import cn.com.cslc.aw.reports.shopinstantpromotion.dto.QueryShopInstantPromotionCondition;
import cn.com.cslc.aw.reports.shopinstantpromotion.dto.QueryShopInstantPromotionResult;



public interface ShopInstantPromotionReportRepositoryCustom {
	
	Page<QueryShopInstantPromotionResult> queryByCondition(QueryShopInstantPromotionCondition queryCondition, Pageable pageable);
	
	List<InstantPromotion> findPromotionByCustomerCodes(Set<String> userCustomerCodeSet);
	
	InstantPromotion findPromotionByPromotionCode(String promotionCode);
	
	
}
