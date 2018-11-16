package cn.com.cslc.aw.reports.competitionprizecash.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.com.cslc.aw.reports.competitionprizecash.dto.QueryCompetitionPrizeCashCondition;
import cn.com.cslc.aw.reports.competitionprizecash.dto.QueryCompetitionPrizeCashResult;

public interface CompetitionPrizeCashReportRepositoryCustom {
	Page<QueryCompetitionPrizeCashResult> queryByCondition(QueryCompetitionPrizeCashCondition queryCondition,Pageable pageRequest);
}
