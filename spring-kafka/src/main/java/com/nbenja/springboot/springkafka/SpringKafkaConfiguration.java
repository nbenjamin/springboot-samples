package com.nbenja.springboot.springkafka;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;

import java.util.HashMap;
import java.util.Properties;

import javax.annotation.PostConstruct;

@Configuration
@EnableConfigurationProperties(KafkaSettings.class)
@ConfigurationProperties
@Order(2)
public class SpringKafkaConfiguration {

    private Properties info = new Properties();
    @Autowired
    @Qualifier("test-consumer-1")
    private KafkaListener kafkaListener;

    @Autowired
    @Qualifier("test-consumer-2")
    private KafkaListener kafkaListener2;


    public Properties cisternino() {
        return info;
    }

    public void setInfo(Properties info) {
        this.info = info;
    }

    @Bean
    public TestListener testListener() {
        System.out.println(kafkaListener);
        System.out.println(kafkaListener2);
        return new TestListener();
    }

    @Bean("test-consumer-3")
    public KafkaListener testConsumer1() {
        return new KafkaListener("Samy", "anoop");
    }

}

@Configuration
@Order(1)
class DemoDymanicBean implements BeanFactoryAware {

    @Autowired
    private KafkaSettings kafkaSettings;

    private HashMap<String, Object> hashMap = new HashMap<>();
    private BeanFactory beanFactory;


    public HashMap<String, Object> getHashMap() {
        return hashMap;
    }

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }


    public void setHashMap(HashMap<String, Object> hashMap) {
        this.hashMap = hashMap;
    }

    @PostConstruct
    public void configure() {
        ConfigurableBeanFactory configurableBeanFactory = (ConfigurableBeanFactory) beanFactory;
        for (KafkaSettings.ConsumerSettings consumerSettings : kafkaSettings.getConsumers()) {
            KafkaListener kafkaListener = new KafkaListener(consumerSettings.getName(),
                    consumerSettings.getTopic());
            configurableBeanFactory.registerSingleton(consumerSettings.getName(), kafkaListener);
        }
    }

}

class KafkaListener {
    private String name;
    private String topic;

    public KafkaListener(String name, String topic) {
        this.name = name;
        this.topic = topic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}

class TestListener {

}