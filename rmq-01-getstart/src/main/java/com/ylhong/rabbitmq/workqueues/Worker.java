package com.ylhong.rabbitmq.workqueues;

import com.rabbitmq.client.*;
import com.ylhong.rabbitmq.utils.MQUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author ylhong
 * @date 2021/10/23
 */
public class Worker {

    public static final String QUEUE_NAME = "hello";

    public static void main( String[] args ) throws Exception {
        Connection conn = MQUtils.getConnection();
        final Channel channel = conn.createChannel();
        channel.queueDeclare( "hello", true, false, false, null );
        DeliverCallback deliverCallback = ( consumerTag, delivery ) -> {
            String message = new String( delivery.getBody(), StandardCharsets.UTF_8 );
            System.out.println( " [x] Received '" + message + "'" );
            try {
                doWork( message );
            } finally {
                System.out.println( " [x] Done" );
            }
        };
        boolean autoAck = true;
        channel.basicConsume( QUEUE_NAME, autoAck, deliverCallback, consumerTag -> {
        } );

    }

    private static void doWork( String task ) {
        for (char ch : task.toCharArray()) {
            if (ch == '.') {
                try {
                    Thread.sleep( 1000 );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
