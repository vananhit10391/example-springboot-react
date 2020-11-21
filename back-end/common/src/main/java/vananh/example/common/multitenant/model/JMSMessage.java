package vananh.example.common.multitenant.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class JMSMessage implements Serializable {
    String message;
}
