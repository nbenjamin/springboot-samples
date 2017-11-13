package com.nbenja.springboot.springembdeddedkafkaexample;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

@Configuration
@EnableKafka
public class KafkaTestConfiguration {

    @Bean
    public ProducerFactory producerFactory(KafkaProperties kafkaProperties) {
       return new DefaultKafkaProducerFactory(kafkaProperties.getProducerKafkaProperties());
    }

    @Bean
    public ConsumerFactory consumerFactory(KafkaProperties kafkaProperties) {
        return new DefaultKafkaConsumerFactory(kafkaProperties.getConsumerKafkaProperties());
    }

    @Bean("KafkaRealTemplate")
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory producerFactory) {
        KafkaTemplate kafkaTemplate = new KafkaTemplate<String, String>(producerFactory);
        kafkaTemplate.setDefaultTopic("test.demo");
        return kafkaTemplate;
    }

    @Bean("kafkaMessageListenerContainerTest")
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer> kafkaMessageListenerContainer(ConsumerFactory
                                                                                   consumerFactory) {
        ConcurrentKafkaListenerContainerFactory containerFactory = new
                ConcurrentKafkaListenerContainerFactory();
        containerFactory.setConcurrency(2);
        containerFactory.getContainerProperties().setPollTimeout(3000);

        containerFactory.setConsumerFactory(consumerFactory);
        return containerFactory;
    }

    @Bean
    public MessageListener messageListener(){
        return new MessageListener();
    }
}
