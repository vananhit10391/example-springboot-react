package vananh.example.multitenant.controller;

import vananh.example.common.exception.ResourceNotFoundException;
import vananh.example.common.payload.response.ApiResponse;
import vananh.example.multitenant.jms.DataSourceConfigSender;
import vananh.example.multitenant.model.DataSourceConfig;
import vananh.example.multitenant.service.DataSourceConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/datasource")
public class DataSourceConfigController {
    @Autowired
    DataSourceConfigService service;

    @Autowired
    DataSourceConfigSender dataSourceConfigSender;

    @GetMapping(value = "/")
    public ResponseEntity<List<DataSourceConfig>> getAll() {
        List<DataSourceConfig> list = service.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<DataSourceConfig> getById(@PathVariable(value = "name") String name) {
        Optional<DataSourceConfig> dataSourceConfigOptional = service.getByName(name);
        if (!dataSourceConfigOptional.isPresent()) {
            throw new ResourceNotFoundException("DataSourceConfig", "name", name);
        }
        return ResponseEntity.ok(dataSourceConfigOptional.get());
    }

    @PostMapping(value = "/")
    public ResponseEntity<DataSourceConfig> save(@RequestBody DataSourceConfig dataSourceConfig) {
        service.save(dataSourceConfig);
        dataSourceConfigSender.sendUpdate(dataSourceConfig);
        return ResponseEntity.ok(dataSourceConfig);
    }

    @PutMapping(value = "/")
    public ResponseEntity<DataSourceConfig> update(@RequestBody DataSourceConfig dataSourceConfig) {
        Optional<DataSourceConfig> dataSourceConfigOptional = service.getById(dataSourceConfig.getId());
        if (!dataSourceConfigOptional.isPresent()) {
            throw new ResourceNotFoundException("DataSourceConfig", "id", dataSourceConfig.getId());
        }
        service.save(dataSourceConfig);
        dataSourceConfigSender.sendUpdate(dataSourceConfigOptional.get());
        return ResponseEntity.ok(dataSourceConfig);
    }

    @DeleteMapping(value = "/{name}")
    public ResponseEntity<?> delete(@PathVariable(value = "name") String name) {
        Optional<DataSourceConfig> dataSourceConfigOptional = service.getByName(name);
        if (!dataSourceConfigOptional.isPresent()) {
            throw new ResourceNotFoundException("DataSourceConfig", "name", name);
        }
        service.deleteById(dataSourceConfigOptional.get().getId());
        dataSourceConfigSender.sendDelete(name);
        return ResponseEntity.ok(new ApiResponse(true, "Delete success"));
    }
}
