package com.nbenja.springboot.springkafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @Autowired
    private KafkaSettings kafkaSettings;

    @KafkaListener(topics = "#{kafkaSettings.consumer('test-consumer-1').getTopic()}")
    public void consumeKafkaMessage(ConsumerRecord<String,String> consumerRecord) {
        System.out.println(" Message Listener 1 - " +consumerRecord.toString());
    }

    @KafkaListener(topics = "#{kafkaSettings.consumer('test-consumer-2').getTopic()}")
    public void consumeKafkaMessage2(ConsumerRecord<String,String> consumerRecord) {
        System.out.println(" Message Listener 2 - " +consumerRecord.toString());
    }
}
