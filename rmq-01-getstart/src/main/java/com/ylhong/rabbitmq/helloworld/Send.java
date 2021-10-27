package com.ylhong.rabbitmq.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author ylhong
 * @date 2021/10/3
 */
public class Send {

    private final static String QUEUE_NAME = "hello";

    public static void main( String[] argv ) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost( "172.16.62.7" );
        factory.setPort( 5672 );
        factory.setUsername( "admin" );
        factory.setPassword( "admin" );

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel())
        {

            channel.queueDeclare( QUEUE_NAME, false, false, false, null );
            String message = "Hello World!";

            channel.basicPublish( "", QUEUE_NAME, null, message.getBytes() );
            System.out.println( " [x] Sent '" + message + "'" );
        }
    }

}
