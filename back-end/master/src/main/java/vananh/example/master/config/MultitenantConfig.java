package vananh.example.master.config;

import vananh.example.common.multitenant.DataSourceConfigFactory;
import vananh.example.common.multitenant.DataSourceConfigMap;
import vananh.example.common.multitenant.TenantContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MultitenantConfig {
    @Bean
    public TenantContext tenantContext() {
        return new TenantContext();
    }

    @Bean
    public DataSourceConfigFactory dataSourceConfigFactory() {
        return new DataSourceConfigFactory();
    }

    @Bean
    public DataSourceConfigMap dataSourceConfigMap() {
        return new DataSourceConfigMap();
    }
}
