package vananh.example.multitenant.service;

import vananh.example.multitenant.model.DataSourceConfig;

import java.util.List;
import java.util.Optional;

public interface DataSourceConfigService {
    List<DataSourceConfig> getAll();

    Optional<DataSourceConfig> getById(Long id);

    Optional<DataSourceConfig> getByName(String name);

    DataSourceConfig save(DataSourceConfig dataSourceConfig);

    void deleteById(Long id);
}
