package cn.com.cslc.aw.reports.quartzexample.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SampleService {

    private static final Logger LOG = LoggerFactory.getLogger(SampleService.class);

    public void hello() {
        LOG.info("Hello World!");
    }
    
    public void hello2() {
        LOG.info("Hello World2!");
    }
}
