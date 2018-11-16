package cn.com.cslc.aw.reports.competitionprizecash.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import cn.com.cslc.aw.reports.competitionprizecash.constant.RoundConstant;
import cn.com.cslc.aw.reports.competitionprizecash.dto.QueryCompetitionPrizeCashCondition;
import cn.com.cslc.aw.reports.competitionprizecash.dto.QueryCompetitionPrizeCashResult;
import cn.com.cslc.aw.reports.competitionprizecash.dto.RoundCondition;
import cn.com.cslc.aw.reports.competitionprizecash.repository.CompetitionPrizeCashReportRepository;


@Service
public class CompetitionPrizeCashReportService {
	
	
	@Autowired
	private CompetitionPrizeCashReportRepository competitionPrizeCashReportRepository; 
	
	
	public List<RoundCondition> findRoundCondition(){

		 List<RoundCondition> roundList= Lists.newArrayList();
		 RoundCondition roundHalfUp =new RoundCondition();
		 roundHalfUp.setRoundName(RoundConstant.ROUND_HALF_UP.getName());
		 roundHalfUp.setRoundNo(RoundConstant.ROUND_HALF_UP.getCode());
		 roundList.add(roundHalfUp);
		 
		 RoundCondition roundAbandon=new RoundCondition(); 
		 roundAbandon.setRoundName(RoundConstant.ROUND_ABANDON.getName());
		 roundAbandon.setRoundNo(RoundConstant.ROUND_ABANDON.getCode());
		 roundList.add(roundAbandon);
		 
		 RoundCondition roundUp =new RoundCondition();
		 roundUp.setRoundName(RoundConstant.ROUND_UP.getName());
		 roundUp.setRoundNo(RoundConstant.ROUND_UP.getCode());
		 roundList.add(roundUp);
		 return roundList;
	 }
	
	
	public String findRoundConditionNameByCode(String code){
		
		for(RoundConstant rc : RoundConstant.values()){
			if(rc.getCode().equals(code)){
				return rc.getName();
			}
		}
		return  "";
	}
	
	 
	 
	 public Page<QueryCompetitionPrizeCashResult> queryByCondition(QueryCompetitionPrizeCashCondition queryCondition,Pageable pageRequest){
		 return competitionPrizeCashReportRepository.queryByCondition(queryCondition,pageRequest);
	 }
	

}
