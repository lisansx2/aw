package cn.com.cslc.aw.reports.periodsales.service;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cslc.aw.reports.periodsales.dto.QueryPeriodSalesCondition;
import cn.com.cslc.aw.reports.periodsales.dto.QueryPeriodSalesResult;
import cn.com.cslc.aw.reports.periodsales.repository.PeriodSalesReportRepository;



@Transactional(readOnly = true)
@Service
public class PeriodSalesReportService{

	private static final Logger LOG = LoggerFactory.getLogger(PeriodSalesReportService.class);
	
	@Autowired
	private PeriodSalesReportRepository periodSalesReportRepository;
	
	public Page<QueryPeriodSalesResult> queryPeriodSalesByCondition(QueryPeriodSalesCondition queryCondition, Pageable pageable) {
		return periodSalesReportRepository.queryPeriodSalesByCondition(queryCondition, pageable);
	}
	public Page<QueryPeriodSalesResult> queryPeriodSalesByConditionNatural(QueryPeriodSalesCondition queryCondition, Pageable pageable) {


		Date beforeTime = new Date();
		long  milliseconds = beforeTime.getTime();
		LOG.info("#################PeriodSalesReportService queryPeriodSalesByConditionNatural before################# milliseconds:" + milliseconds);
		Page<QueryPeriodSalesResult> result = periodSalesReportRepository.queryPeriodSalesByConditionNatural(queryCondition, pageable);
		LOG.info("#################PeriodSalesReportService queryPeriodSalesByConditionNatural after ################# milliseconds:" + milliseconds + ", 耗时:" +  (new Date().getTime() - beforeTime.getTime()) + "毫秒");
		LOG.info("");
		return result;
	}
	
	/**
	 * 生成基础报表（左表）数据，每日执行一次
	 * 1、从数据表中查询最新日期startDate
	 * 2、生成startDate到昨天的数据
	 */
	@Transactional
	public void generateBaseReportData(){
		Date startDate = periodSalesReportRepository.findMaxReportDate();
		if(startDate == null){
			startDate = periodSalesReportRepository.findInitReportDate();
		}else{
			startDate = DateUtils.addDays(startDate, 1);
		}
		
		Date endDate = DateUtils.addDays(new Date(), -1);
		
		String startDateStr = DateFormatUtils.format(startDate, "yyyy-MM-dd");
		String endDateStr = DateFormatUtils.format(endDate, "yyyy-MM-dd");

		try {
			if(!DateUtils.parseDate(startDateStr, new String[]{"yyyy-MM-dd"}).after(DateUtils.parseDate(endDateStr, new String[]{"yyyy-MM-dd"}))){
				periodSalesReportRepository.batchInsertReportBaseData(startDateStr, endDateStr);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}	
	}
}
