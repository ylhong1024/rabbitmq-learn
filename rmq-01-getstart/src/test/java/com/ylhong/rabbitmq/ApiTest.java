package com.ylhong.rabbitmq;

import com.rabbitmq.client.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author ylhong
 * @date 2021/10/27
 */
public class ApiTest {

    static Connection conn;
    static Channel channel;

    @BeforeClass
    public static void init() throws IOException, TimeoutException {
        // 0. create connection factory
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost( "172.16.62.7" );
        factory.setPort( 5672 );
        factory.setUsername( "admin" );
        factory.setPassword( "admin" );
        factory.setVirtualHost( "/" );

        // 1. create connection
        conn = factory.newConnection();
        // 2. create channel
        channel = conn.createChannel();
    }

    @Test
    public void testExchangeDeclare() throws IOException {
        // direct
        channel.exchangeDeclare( "EXCHANGE_DIRECT", BuiltinExchangeType.DIRECT, true, false, null );
        // topic
        channel.exchangeDeclare( "EXCHANGE_TOPIC", BuiltinExchangeType.TOPIC, true, false, null );
        // fanout
        channel.exchangeDeclare( "EXCHANGE_FANOUT", BuiltinExchangeType.FANOUT, true, false, null );
        // headers
        channel.exchangeDeclare( "EXCHANGE_HEADERS", BuiltinExchangeType.HEADERS, true, false, null );
    }

    @Test
    public void testQueueDeclare() throws IOException {
        // direct
        channel.queueDeclare( "QUEUE_DIRECT", true, false, false, null );

        // topic
        channel.queueDeclare( "QUEUE_TOPIC", true, false, false, null );

        // FANOUT
        channel.queueDeclare( "QUEUE_FANOUT", true, false, false, null );

        // HEADERS
        channel.queueDeclare( "QUEUE_HEADERS", true, false, false, null );
    }

    @Test
    public void testBinding() throws IOException {
        // 交换机和交换机绑定
//        channel.exchangeBind( "dest", "src", "routingKey", null );

        // 交换机的和队列的绑定(需要broker给应答)
        channel.queueBind( "QUEUE_DIRECT", "EXCHANGE_DIRECT", "ROUTING_KEY_DIRECT", null );
        // 无需broker给应答
//        channel.queueBindNoWait( "QUEUE_DIRECT", "EXCHANGE_DIRECT", "ROUTING_KEY_DIRECT", null );

//        channel.queueBind( "QUEUE_TOPIC", "EXCHANGE_TOPIC", "topic.*", null );

//        channel.queueBind( "QUEUE_FANOUT", "EXCHANGE_FANOUT", null, null );
    }


    @AfterClass
    public static void release() {
        if (channel != null) {
            try {
                channel.close();
            } catch (IOException | TimeoutException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
