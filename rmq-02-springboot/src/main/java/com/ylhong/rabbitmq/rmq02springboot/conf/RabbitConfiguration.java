package com.ylhong.rabbitmq.rmq02springboot.conf;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ylhong
 * @date 2021/10/25
 */
@Configuration
public class RabbitConfiguration {

    @Bean
    public SimpleRabbitListenerContainerFactory listenerContainerFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer, CachingConnectionFactory cachingConnectionFactory ) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, cachingConnectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());

        return factory;
    }



}
