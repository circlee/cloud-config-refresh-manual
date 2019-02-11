package com.eldie.cloud.config.server.repositories;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by circlee on 2019. 2. 11..
 */
@Repository
public class EnvRepository {

    Map<String, Map<String, String>> envMap = new HashMap<>();

    @PostConstruct
    public void init(){
        Map<String, String> property = new HashMap<>();
        property.put("central.test", "test");
        property.put("central.test1", "test1");
        property.put("central.test2", "test2");

        putProp("cofigClient", property);
    }

    public void putProp(String app, Map<String, String> prop) {
        putProp(app, "default", "null", prop);
    }

    public void putProp(String app, String profile, Map<String, String> prop) {
        putProp(app, profile, "null", prop);
    }

    public void putProp(String app, String profile, String label, Map<String, String> prop) {
        String mapKey = app.join("-").join(profile).join("-").join(label == null ? "null" : label);
        envMap.put(mapKey, prop);
    }


    public Map<String, String> getProp(String app, String profile) {
        return getProp(app, profile, "null");
    }

    public Map<String, String> getProp(String app, String profile, String label) {
        String mapKey = app.join("-").join(profile).join("-").join(label == null ? "null" : label);
        return envMap.get(mapKey);
    }



    public void setPropKey(String app, String key, String value) {
        setPropKey(app, "default", "null", key , value);
    }

    public void setPropKey(String app, String profile, String key, String value) {
        setPropKey(app, profile, "null", key , value);
    }

    public void setPropKey(String app, String profile, String label, String key, String value) {

        Map<String, String> propMap = getProp(app, profile, label == null ? "null" : label);
        if(propMap == null) {
            propMap = new HashMap<>();
        }

        propMap.put(key, value);
        putProp(app, profile, label, propMap);
    }

    public Map<String, Map<String, String>> getEnvMap() {
        return envMap;
    }
}
