package com.nbenja.springboot.springembdeddedkafkaexample;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Data
public class MessageListener {

    public Set<String> records = new HashSet();

    @KafkaListener(topics = "test.demo", id = "requestConsumerListener")
    public void messageListener(ConsumerRecord<String, String> consumerRecord) {
        log.info("Message received {}", String.valueOf(consumerRecord));
        log.info("consumerRecord value received {}", consumerRecord.value());
        records.add( consumerRecord.value());
    }
}
