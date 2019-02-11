package com.eldie.cloud.config.server.configs;

import com.eldie.cloud.config.server.repositories.EnvRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by circlee on 2019. 2. 11..
 */
@Configuration
public class EnvConfig {

    Logger logger = LoggerFactory.getLogger(EnvConfig.class);

    @Autowired
    private EnvRepository envRepository;

    @Bean
    public EnvironmentRepository getEnvironmentRepository(){
        return new EnvironmentRepository() {
            @Override
            public Environment findOne(String appName, String profile, String label) {

                logger.info("appName >>> {}" , appName);
                logger.info("profile >>> {}" , profile);
                logger.info("label >>> {}" , label);

                Map<String, String> property  = envRepository.getProp(appName, profile, label);


                PropertySource propertySource = new PropertySource("testProperty", property);


                Environment environment = new Environment(appName, profile);
                environment.add(propertySource);

                return environment;
            }
        };

    }
}
