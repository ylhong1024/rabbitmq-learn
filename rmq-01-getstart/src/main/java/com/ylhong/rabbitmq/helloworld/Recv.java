package com.ylhong.rabbitmq.helloworld;

import com.rabbitmq.client.*;

import java.nio.charset.StandardCharsets;

/**
 * @author ylhong
 * @date 2021/10/3
 */
public class Recv {

    private final static String QUEUE_NAME = "hello_001";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("172.16.62.7");
        factory.setPort( 5672 );
        factory.setUsername( "admin" );
        factory.setPassword( "admin" );
        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();
//        channel.queueDeclare( QUEUE_NAME, false, false, false, null );
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        DeliverCallback deliverCallback = ( consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8 );
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });

    }
}
