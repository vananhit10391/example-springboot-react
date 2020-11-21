package vananh.example.multitenant.jms;

import vananh.example.common.multitenant.model.DataSourceConfigMessage;
import vananh.example.common.multitenant.model.JMSMessage;
import vananh.example.multitenant.model.DataSourceConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import javax.jms.*;

@Component
@Slf4j
public class DataSourceConfigSender {
    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    ConnectionFactory connectionFactory;

    @Value("${active-mq.multi-topic}")
    private String topic;

    public void sendUpdate(DataSourceConfig dataSourceConfig){
        try{
            log.info("Send message update dataSourceConfig to Topic: " + topic);
            DataSourceConfigMessage message = new DataSourceConfigMessage();
            message.setId(dataSourceConfig.getId());
            message.setName(dataSourceConfig.getName());
            message.setUrl(dataSourceConfig.getUrl());
            message.setDriverClassName(dataSourceConfig.getDriverClassName());
            message.setUsername(dataSourceConfig.getUsername());
            message.setPassword(dataSourceConfig.getPassword());
            message.setInitialize(dataSourceConfig.isInitialize());
            jmsTemplate.convertAndSend(topic, message);
        } catch(Exception e){
            log.error("Recieved Exception during send message update dataSourceConfig: ", e);
        }
    }

    public void sendDelete(String name){
        try{
            log.info("Send message delete dataSourceConfig to Topic: " + topic);
            JMSMessage jmsMessage = new JMSMessage();
            jmsMessage.setMessage(name);
            jmsTemplate.convertAndSend(topic, jmsMessage);
        } catch(Exception e){
            log.error("Recieved Exception during send message update dataSourceConfig: ", e);
        }
    }
}
