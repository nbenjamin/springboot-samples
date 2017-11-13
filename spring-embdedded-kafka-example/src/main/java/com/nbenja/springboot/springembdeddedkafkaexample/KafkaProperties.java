package com.nbenja.springboot.springembdeddedkafkaexample;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "kafka")
public class KafkaProperties implements Serializable {

  private Properties producer;
  private Properties consumer;


  public Map<String, Object> getConsumerKafkaProperties() {
    return consumer.entrySet().stream()
            .collect(Collectors.toMap(e -> e.getKey().toString(), e -> e.getValue()));
  }

  public Map<String, String> getProducerKafkaProperties() {
    return producer.entrySet().stream().collect(Collectors.toMap(e -> e.getKey().toString(), e -> e
            .getValue().toString()));
  }
}

