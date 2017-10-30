package com.nbenja.springboot.springkafka;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.kafka.listener.AbstractMessageListenerContainer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Component("kafkaSettings")
@ConfigurationProperties(prefix = "kafka")
@Data
public class KafkaSettings {

    private static final String INVALID_CONSUMER_NAME = "Invalid consumer name %s";
    private static final String INVALID_PRODUCER_NAME = "Invalid producer name %s";
    private static final String INVALID_LISTENER_NAME = "Invalid listener name %s";


    private List<ConsumerSettings> consumers = new ArrayList<>();
    private List<ProducerSettings> producers = new ArrayList<>();
    private List<ListenerSettings> listeners = new ArrayList<>();


    public ConsumerSettings consumer(String consumerName) {
        return this.consumers.stream()
                .filter(consumerSettings -> consumerName.equalsIgnoreCase(consumerSettings
                        .getName())).findFirst().orElseThrow(() -> new RuntimeException(String.format(INVALID_CONSUMER_NAME,
                        consumerName)));
    }

    public ProducerSettings producer(String producerName) {
        return this.producers.stream()
                .filter(producerSettings -> producerName.equalsIgnoreCase(producerSettings
                        .getName())).findFirst().orElseThrow(() -> new RuntimeException(String.format(INVALID_PRODUCER_NAME,
                        producerName)));
    }

    public ListenerSettings listener(String listenerName) {
        return this.listeners.stream().filter(listenerSettings -> listenerName.equalsIgnoreCase
                (listenerSettings.getName())).findFirst().orElseThrow(() -> new RuntimeException(String.format(INVALID_PRODUCER_NAME,
                listenerName)));
    }

    @Setter
    @Getter
    public static class ConsumerSettings {

        private String name;
        private String topic;
        private Map<String, Object> properties = new HashMap<>();
    }

    @Setter
    @Getter
    public static class ProducerSettings {

        private String name;
        private String topic;
        private Map<String, Object> properties = new HashMap<>();
    }


    @Getter
    @Setter
    public static class ListenerSettings {
        private String name;
        private AbstractMessageListenerContainer.AckMode ackMode;
        private Integer concurrency;
        private Long pollTimeout;
        private KafkaProperties.Listener.Type type = KafkaProperties.Listener.Type.SINGLE;
    }

}
