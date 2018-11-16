package cn.com.cslc.aw.reports.periodsales.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.com.cslc.aw.reports.periodsales.dto.QueryPeriodSalesCondition;
import cn.com.cslc.aw.reports.periodsales.dto.QueryPeriodSalesResult;

public interface PeriodSalesReportRepositoryCustom {
	
	Page<QueryPeriodSalesResult> queryPeriodSalesByCondition(QueryPeriodSalesCondition queryCondition, Pageable pageable);
	
	Page<QueryPeriodSalesResult> queryPeriodSalesByConditionNatural(QueryPeriodSalesCondition queryCondition, Pageable pageable);
	
	Date findMaxReportDate();
	
	Date findInitReportDate();
	
	void batchInsertReportBaseData(String startDate, String endDate);
}
