package com.nbenja.springboot.springkafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Properties;

@SpringBootApplication
@EnableConfigurationProperties
@ConfigurationProperties
public class SpringKafkaApplication {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public static void main(String[] args) {
		SpringApplication.run(SpringKafkaApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(KafkaSettings kafkaSettings) {
		return (args) -> {
			this.kafkaTemplate.send(kafkaSettings.producer("test-producer-1").getTopic(), "this is " +
					"test messsage");
			this.kafkaTemplate.send(kafkaSettings.producer("test-producer-2").getTopic(), "this" +
					" is test messsage");
		};
	}

}
