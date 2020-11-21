package vananh.example.multitenant.service.impl;

import vananh.example.multitenant.model.DataSourceConfig;
import vananh.example.multitenant.repository.DataSourceConfigRepository;
import vananh.example.multitenant.service.DataSourceConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DataSourceConfigServiceImpl implements DataSourceConfigService {
    @Autowired
    DataSourceConfigRepository repository;

    @Override
    public List<DataSourceConfig> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<DataSourceConfig> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public DataSourceConfig save(DataSourceConfig dataSourceConfig) {
        return repository.save(dataSourceConfig);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<DataSourceConfig> getByName(String name) {
        return repository.findByName(name);
    }
}
