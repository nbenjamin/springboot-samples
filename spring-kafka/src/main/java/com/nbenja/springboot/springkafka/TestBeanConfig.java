package com.nbenja.springboot.springkafka;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.annotation.PostConstruct;
@Configuration
public class TestBeanConfig implements InitializingBean {

    @Bean("test-consumer-1")
    @Primary
    public KafkaListener kafkaListener11() {
        return new KafkaListener("Samy", "anoop");
    }

    @Autowired
    @Qualifier("test-consumer-1")
    private KafkaListener kafkaListener;

    @Autowired
    @Qualifier("test-consumer-2")
    private KafkaListener kafkaListener2;

//    @Autowired
//    @Qualifier("test-consumer-3")
//    private KafkaListener kafkaListener3;

    //@PostConstruct
    public void testListener() {
        System.out.println(kafkaListener);
        System.out.println(kafkaListener2);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //System.out.println(kafkaListener3);
        System.out.println(kafkaListener);
        System.out.println(kafkaListener2);
    }
}
