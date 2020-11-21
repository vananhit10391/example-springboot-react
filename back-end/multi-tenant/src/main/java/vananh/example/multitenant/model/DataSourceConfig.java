package vananh.example.multitenant.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class DataSourceConfig implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private String username;

    private String password;

    @Column(nullable = false)
    private String driverClassName;

    private boolean initialize = true;
}