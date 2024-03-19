package com.legaoyi.iov.message.processor.mq;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;

import com.legaoyi.kafka.KafkaMessageProducer;
import com.legaoyi.mq.MQMessageProducer;

@Configuration("kafkaConfiguration")
@ConditionalOnProperty(name = "spring.kafka.bootstrap-servers")
public class KafkaConfiguration {

    @Bean("commonDownstreamMessageProducer")
    public MQMessageProducer commonDownstreamMessageProducer() {
        return new KafkaMessageProducer();
    }

    @Bean
    public KafkaListenerContainerFactory<?> batchFactory(ConsumerFactory<String, String> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        // factory.setConcurrency(10);
        // factory.getContainerProperties().setPollTimeout(1500);
        factory.setBatchListener(true);// 设置为批量消费，每个批次数量在Kafka配置参数中设置
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);// 设置手动提交ackMode
        return factory;
    }
}
