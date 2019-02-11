package com.eldie.cloud.config.client.controllers;

import com.eldie.cloud.config.client.configs.CentralConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by circlee on 2019. 2. 11..
 */
@RestController
public class ApiController {

    Logger logger = LoggerFactory.getLogger(ApiController.class);

    @Autowired(required = false)
    CentralConfig centralConfig;

    @Autowired(required = false)
    ContextRefresher contextRefresher;

    @GetMapping("/centralConfig")
    public CentralConfig getCentralConfig(@RequestParam(value = "refresh" , required = falseg) boolean refresh){

        if(refresh) {
            if(contextRefresher != null) {
                contextRefresher.refresh();
            }
        }

        logger.info("CentralConfig >> {}" , centralConfig);

        CentralConfig copyConfig = new CentralConfig();
        BeanUtils.copyProperties(centralConfig, copyConfig);
        return copyConfig;
    }


}
