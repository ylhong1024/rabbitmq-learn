package com.ylhong.rabbitmq;

import com.rabbitmq.client.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @author ylhong
 * @date 2021/10/27
 */
public class SendingTest {

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

    public static void main( String[] args ) throws IOException, TimeoutException {
        init();
        channel.basicPublish( "EXCHANGE_DIRECT", "ROUTING_KEY_DIRECT1", true, null , "hello world".getBytes( StandardCharsets.UTF_8 ) );
        channel.addReturnListener( (int replyCode,
                                    String replyText,
                                    String exchange,
                                    String routingKey,
                                    AMQP.BasicProperties properties,
                                    byte[] body)->{
            System.out.printf( "replyCode:[%s], replyText:[%s], exchange: [%s], routingKey: [%s], properties: [%s], body: [%s]%n",
                    replyCode, replyText, exchange, routingKey, properties, new String( body ) );
        }  );
    }

    @Test
    public void testSendMsg() throws IOException {
        final AMQP.BasicProperties basicProperties = new AMQP.BasicProperties.Builder()
                // 1 - non persist; 2 - persist
                .deliveryMode( 2 )
                .build();
        // header
        channel.basicPublish( "EXCHANGE_DIRECT", "ROUTING_KEY_DIRECT", true, null , "hello world".getBytes( StandardCharsets.UTF_8 ) );

        /**
         * Request specific "quality of service" settings.
         * These settings impose limits on the amount of data the server will deliver to consumers before requiring acknowledgements.
         * Thus they provide a means of consumer-initiated flow control.
         * Note the prefetch count must be between 0 and 65535 (unsigned short in AMQP 0-9-1).
         * Params:
         * prefetchSize – maximum amount of content (measured in octets) that the server will deliver, 0 if unlimited
         * prefetchCount – maximum number of messages that the server will deliver, 0 if unlimited
         * global – true if the settings should be applied to the entire channel rather than each consumer
         *
         * 大致意思为：请求服务质量的设置
         * 这些设置意味着，在消费者回复ack之前broker可以给消费者发的最大消息数量。因此他们提供消费者的初始流控。
         * 参数：
         * prefetchSize: 服务端将会发送的内容最大值（字节），0代表无限制
         * prefetchCount: 服务端发送的最大消息数量,0代表无限制
         * global: 如果为true则代表适用于整个信号而不是单个消费者
         */
        channel.basicQos( 1, false );

        /**
         * Cancel a consumer. Calls the consumer's Consumer.handleCancelOk method.
         * Params:
         * consumerTag – a client- or server-generated consumer tag to establish context
         * 意思是说：
         * 取消一个消费者。 调用消费者的 Consumer.handleCancleOk方法
         * 参数：
         * customerTag - 一个客户端或服务端生成的消费者标签
         */
//        channel.basicCancel(  );

//        channel.basicGet(  )
//        channel.basicConsume(  )
//        channel.basicAck(  );
//        channel.basicNack(  );
//        channel.basicReject(  );
//        channel.basicRecover()
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
