package vananh.example.master.multitenant.jms;

import vananh.example.common.multitenant.DataSourceConfigMap;
import vananh.example.common.multitenant.TenantContext;
import vananh.example.common.multitenant.model.DataSourceConfigMessage;
import vananh.example.common.multitenant.model.JMSMessage;
import vananh.example.master.multitenant.TenantDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

@Component
@Slf4j
public class MultiTenantListener implements MessageListener {
    @Autowired
    TenantContext tenantContext;

    @Autowired
    DataSourceConfigMap dataSourceConfigMap;

    @Autowired
    TenantDataSource tenantDataSource;

    @Override
    @JmsListener(destination = "${active-mq.multi-topic}")
    public void onMessage(Message message) {
        try{
            ObjectMessage objectMessage = (ObjectMessage)message;
            if (objectMessage.getObject() instanceof DataSourceConfigMessage) {
                DataSourceConfigMessage dataSourceConfigMessage = (DataSourceConfigMessage) objectMessage.getObject();
                log.info("Received message update dataSourceConfig: " + dataSourceConfigMessage);
                String name = dataSourceConfigMessage.getName();
                dataSourceConfigMap.getMap().put(name, tenantDataSource.updateDataSource(name));
            } else if (objectMessage.getObject() instanceof JMSMessage) {
                JMSMessage jmsMessage = (JMSMessage) objectMessage.getObject();
                String key = jmsMessage.getMessage();
                log.info("Received message delete dataSourceConfig: " + key);
                dataSourceConfigMap.getMap().remove(key);
            } else {
                log.info("Can't detect object type for JMSDestination: " + objectMessage.getJMSDestination());
            }
        } catch(Exception e) {
            log.error("Received Exception while processing message dataSourceConfig: " + e);
        }
    }
}

