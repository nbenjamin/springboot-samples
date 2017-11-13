package com.nbenja.springboot.springembdeddedkafkaexample;

import static com.nbenja.springboot.springembdeddedkafkaexample.AccceptanceTest.embeddedKafka;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.kafka.test.utils.ContainerTestUtils.waitForAssignment;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringEmbdeddedKafkaExampleApplication
        .class},
        properties = {
                "kafka.producer.bootstrap.servers: ${spring.embedded.kafka.brokers}", "" +
                "kafka.consumer.bootstrap.servers: ${spring.embedded.kafka.brokers}"
        })
@ContextConfiguration
@DirtiesContext
public class AcceptanceTestSteps {

    //
//    @Autowired
//    @Qualifier("KafkaRealTemplate")
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private MessageListener messageListener;


    @Autowired
    KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    @When("^application is up and running$")
    public void application_is_up_and_running() throws Throwable {
        kafkaTemplate().send("test.demo", "Send message to specific topic 2");
    }

    @Then("^publish message$")
    public void publish_message() throws Throwable {
    }

    @Then("^consumer the same message$")
    public void consumer_the_same_message() throws Throwable {
        TimeUnit.SECONDS.sleep(2);
        assertThat(messageListener.getRecords().stream().findFirst().get(), is(equalTo("Send " +
                "message to specific topic 2")));

    }

    private KafkaTemplate<String, String> kafkaTemplate() throws Exception {

        for (MessageListenerContainer messageListenerContainer : kafkaListenerEndpointRegistry
                .getListenerContainers()) {
            waitForAssignment(messageListenerContainer, 2);
        }
        return new KafkaTemplate<>(defaultKafkaProducerFactory());
    }

    private ProducerFactory defaultKafkaProducerFactory() {
        return new DefaultKafkaProducerFactory(KafkaTestUtils
                .senderProps(embeddedKafka.getBrokersAsString()));
    }

}
