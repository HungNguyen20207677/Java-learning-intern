package com.sapo.edu.ex10kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic ex10Topic() {
        return TopicBuilder.name("ex10")
                .build();
    }

    @Bean
    public NewTopic ex10JsonTopic() {
        return TopicBuilder.name("ex10_json")
                .build();
    }
}
