package vananh.example.common.multitenant.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class DataSourceConfigMessage implements Serializable {
    private Long id;
    private String name;
    private String url;
    private String username;
    private String password;
    private String driverClassName;
    private boolean initialize;
}
