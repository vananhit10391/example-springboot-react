package vananh.example.common.multitenant;

import vananh.example.common.multitenant.constants.MultiTenantConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Component
public class DataSourceConfigMap {
    @Autowired
    private DataSource defaultDS;

    private static Map<String, DataSource> map = new HashMap<>();

    public Map<String, DataSource> getMap() {
        return map;
    }

    public void setMap(Map<String, DataSource> map) {
        this.map = map;
    }

    public void init() {
        this.map.put(MultiTenantConstants.DEFAULT_TENANT_ID, defaultDS);
    }
}
