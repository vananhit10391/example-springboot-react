package vananh.example.master.multitenant.config;

import vananh.example.common.multitenant.DataSourceConfigMap;
import vananh.example.common.multitenant.constants.MultiTenantConstants;
import vananh.example.master.multitenant.TenantDataSource;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Component
public class DataSourceBasedMultiTenantConnectionProviderImpl extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {
    @Autowired
    private ApplicationContext context;

    @Autowired
    DataSourceConfigMap dataSourceConfigMap;

    boolean init = false;

    @PostConstruct
    public void load() {
        dataSourceConfigMap.init();
    }

    @Override
    protected DataSource selectAnyDataSource() {
        return dataSourceConfigMap.getMap().get(MultiTenantConstants.DEFAULT_TENANT_ID);
    }

    @Override
    protected DataSource selectDataSource(String tenantIdentifier) {
        if (!init) {
            init = true;
            TenantDataSource tenantDataSource = context.getBean(TenantDataSource.class);
            tenantDataSource.getAll();
        }
        DataSource dataSource = dataSourceConfigMap.getMap().get(tenantIdentifier) != null ?
                dataSourceConfigMap.getMap().get(tenantIdentifier) : dataSourceConfigMap.getMap().get(MultiTenantConstants.DEFAULT_TENANT_ID);
        return dataSource;
    }
}
