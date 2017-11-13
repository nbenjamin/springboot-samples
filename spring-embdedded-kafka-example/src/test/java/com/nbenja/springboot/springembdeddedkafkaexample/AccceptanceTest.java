package com.nbenja.springboot.springembdeddedkafkaexample;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.springframework.kafka.test.rule.KafkaEmbedded;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources")
public class AccceptanceTest {

    @ClassRule
    public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, "test.demo");

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

        System.out.println("broker url " + System.getProperty("spring.kafka.bootstrap-servers"));
        System.setProperty("spring.embedded.kafka.brokers", embeddedKafka.getBrokersAsString());
        System.setProperty("spring.kafka.bootstrap-servers", embeddedKafka.getBrokersAsString());

    }
}
