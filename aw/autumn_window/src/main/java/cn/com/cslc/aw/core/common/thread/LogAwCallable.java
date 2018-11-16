package cn.com.cslc.aw.core.common.thread;

import cn.com.cslc.aw.core.common.dto.LogOperationAwDto;
import cn.com.cslc.aw.core.common.repository.LogOperationAwRepositoryCustom;
import cn.com.cslc.aw.core.common.service.LogOperationAwService;

import java.util.concurrent.Callable;

public class LogAwCallable implements Callable {

    LogOperationAwDto logOperationAwDto;
    LogOperationAwService logOperationAwService;

    public LogAwCallable(LogOperationAwDto logOperationAwDto, LogOperationAwService logOperationAwService) {
        this.logOperationAwDto = logOperationAwDto;
        this.logOperationAwService = logOperationAwService;
    }

    @Override
    public Object call() throws Exception {
        logOperationAwService.insertLogOperationAw(logOperationAwDto);
        return null;
    }
}
