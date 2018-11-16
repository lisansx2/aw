package cn.com.cslc.aw.reports.shophistorybalance.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Sets;

import cn.com.cslc.aw.reports.shophistorybalance.dto.QueryShopHistoryBalanceCondition;
import cn.com.cslc.aw.reports.shophistorybalance.dto.QueryShopHistoryBalanceResult;
import cn.com.cslc.aw.reports.shophistorybalance.repository.ShopHistoryBalanceReportRepository;



@Transactional(readOnly = true)
@Service
public class ShopHistoryBalanceReportService {

	private static final Logger LOG = LoggerFactory.getLogger(ShopHistoryBalanceReportService.class);

	@Autowired
	private ShopHistoryBalanceReportRepository shopHistoryBalanceReportRepository;
	
	public Page<QueryShopHistoryBalanceResult> queryByCondition(QueryShopHistoryBalanceCondition queryCondition,
			Pageable pageRequest) {
		return shopHistoryBalanceReportRepository.queryByCondition(queryCondition, pageRequest);
	}
	public Page<QueryShopHistoryBalanceResult> queryByConditionNatural(QueryShopHistoryBalanceCondition queryCondition,
			Pageable pageRequest) {
		return shopHistoryBalanceReportRepository.queryByConditionNatural(queryCondition, pageRequest);
	}
	public Set<String> getProvinceIdByAgentNo(List<String> agentNoList){
		List<Object[]> resultList = shopHistoryBalanceReportRepository.queryProvinceIdByAgentNo(agentNoList);
		Set<String> provinceIdSet = Sets.newHashSet();
		for(Object record : resultList) {
		    BigDecimal provinceId = (BigDecimal) record;
		    provinceIdSet.add(provinceId.toString());
		}

		return provinceIdSet;
	}
	
}
