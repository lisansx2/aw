package cn.com.cslc.aw.core.common.service;

import cn.com.cslc.aw.core.common.dto.LogOperationAwDto;
import cn.com.cslc.aw.core.common.repository.LogOperationAwRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class LogOperationAwService {

    @Autowired
    private LogOperationAwRepositoryCustom logOperationAwRepositoryImpl;

    @Transactional
    public void insertLogOperationAw(LogOperationAwDto logOperationAwDto){
        logOperationAwRepositoryImpl.insertLogOperationAw(logOperationAwDto);
    }
}
