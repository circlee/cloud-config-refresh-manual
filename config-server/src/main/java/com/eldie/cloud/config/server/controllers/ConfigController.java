package com.eldie.cloud.config.server.controllers;

import com.eldie.cloud.config.server.repositories.EnvRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by circlee on 2019. 2. 11..
 */
@RestController
public class ConfigController {

    Logger logger = LoggerFactory.getLogger(ConfigController.class);

    @Autowired
    private EnvRepository envRepository;

    @PostMapping("/config/{app}/{profile}/{key}")
    public String getCentralConfig(@PathVariable("app") String app
    ,@PathVariable("profile") String profile
    ,@PathVariable("key") String key
    ,@RequestBody String value){

        logger.info("app >>> {}" , app);
        logger.info("profile >>> {}" , profile);
        logger.info("key >>> {}" , key);
        logger.info("value >>> {}" , value);

        envRepository.setPropKey(app, profile, key, value);

        logger.info("env : {}", envRepository.getEnvMap() );

        return "OK";
    }
}
