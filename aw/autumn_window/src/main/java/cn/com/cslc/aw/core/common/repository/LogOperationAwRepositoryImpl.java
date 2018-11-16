package cn.com.cslc.aw.core.common.repository;

import cn.com.cslc.aw.core.common.dto.LogOperationAwDto;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class LogOperationAwRepositoryImpl implements LogOperationAwRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insertLogOperationAw(LogOperationAwDto logOperationAwDto) {

        StringBuffer sql = new StringBuffer();
        sql.append(" insert into log_operation_aw ");
        sql.append("   (log_id, ");
        sql.append("    log_type_code, ");
        sql.append("    log_time, ");
        sql.append("    request_url, ");
        sql.append("    request_param, ");
        sql.append("    log_desc, ");
        sql.append("    return_no, ");
        sql.append("    user_id, ");
        sql.append("    user_name, ");
        sql.append("    user_ip, ");
        sql.append("    server_ip, ");
        sql.append("    province_center_id, ");
        sql.append("    cost_time) ");
        sql.append(" values ");
        sql.append("   (seq_log_id.nextval, " + logOperationAwDto.getLogTypeCode() + ", sysdate, '" + logOperationAwDto.getRequestUrl() + "', '" + logOperationAwDto.getRequestParam() + "', '" + logOperationAwDto.getLogDesc()
                + "', 0, " + logOperationAwDto.getUserId() + ", '" + logOperationAwDto.getUserName() + "' , '" + logOperationAwDto.getUserIp() + "', '" + logOperationAwDto.getServerIp()
                + "', '" + logOperationAwDto.getProvinceNo() + "', " + logOperationAwDto.getCostTime() + " )");
        em.createNativeQuery(sql.toString()).executeUpdate();
    }
}
