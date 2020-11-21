package vananh.example.master.multitenant;

import vananh.example.common.multitenant.DataSourceConfigFactory;
import vananh.example.common.multitenant.DataSourceConfigMap;
import vananh.example.common.multitenant.model.DataSourceConfigMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

@Component
public class TenantDataSource {
    @Autowired
    DataSourceConfigMap dataSourceConfigMap;

    @Autowired
    DataSourceConfigFactory dataSourceConfigFactory;

    @PostConstruct
    public void getAll() {
        List<DataSourceConfigMessage> configList = dataSourceConfigFactory.getAll();

        for (DataSourceConfigMessage config : configList) {
            DataSource dataSource = getDataSource(config.getName());
            dataSourceConfigMap.getMap().put(config.getName(), dataSource);
        }
    }

    public DataSource getDataSource(String name) {
        if (dataSourceConfigMap.getMap().get(name) != null) {
            return dataSourceConfigMap.getMap().get(name);
        }
        DataSource dataSource = createDataSource(name);
        if (dataSource != null) {
            dataSourceConfigMap.getMap().put(name, dataSource);
        }
        return dataSource;
    }

    public DataSource updateDataSource(String name) {
        DataSource dataSource = createDataSource(name);
        if (dataSource != null) {
            dataSourceConfigMap.getMap().put(name, dataSource);
        } else {
            dataSourceConfigMap.getMap().remove(name);
        }
        return dataSource;
    }

    private DataSource createDataSource(String name) {
        DataSourceConfigMessage config = dataSourceConfigFactory.get(name);
        if (config != null) {
            DataSourceBuilder factory = DataSourceBuilder
                    .create().driverClassName(config.getDriverClassName())
                    .username(config.getUsername())
                    .password(config.getPassword())
                    .url(config.getUrl());
            DataSource dataSource = factory.build();
            return dataSource;
        }
        return null;
    }
}
