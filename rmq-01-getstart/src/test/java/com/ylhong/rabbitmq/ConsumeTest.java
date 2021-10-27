package com.ylhong.rabbitmq;

import com.rabbitmq.client.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * 消费者测试
 *
 * @author ylhong
 * @date 2021/10/27
 */

public class ConsumeTest {

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

    /**
     * 消费者对于消息的处理。
     * 正常接受：
     *      - basicConsume: 使用推模式接收消息。
     *      - basicGet: 使用拉模式接收消息。
     *          * autoAck:
     *              o: true，broker一接收到消息就给服务端应答
     *              o: false, 手动应答
     * 拒绝接受：
     *      -
     *
     */
    public static void main(String[] args) throws IOException, TimeoutException {
        init();
        // 消费一条消息（推模式); 该种方式是mq会发消息推送给channel。
//        Map<String, Object> map = new HashMap<>();
//        final String cTag = channel.basicConsume( "QUEUE_DIRECT",
//                true,
//                map,
//                // DeliverCallback 传输回调
//                ( String consumerTag, Delivery message ) -> {
//
//                    System.out.printf("consumerTag[%s], body[%s], envelop[%s], properties[%s]",consumerTag, new String(message.getBody()), message.getEnvelope(), message.getProperties());
//                },
//                // cancel callback
//                consumerTag -> {
//                    System.out.printf("consumerTag:[%s]", consumerTag);
//                },
//                // consumer shutdown callback
//                ( ( consumerTag, sig ) -> {
//
//                } ) );
//        System.out.printf( "consumerTag[%s]%n", cTag);
//        System.out.printf( "argument: [%s]", map );

        // 消费一条消息（拉模式）; 如果要保持消费消息，需要一致请求通道，该种方式过于浪费性能一般不用。
//        final GetResponse resp = channel.basicGet( "QUEUE_DIRECT", true );
        // 获取服务端还有多少条消息
//        System.out.println( resp.getMessageCount() );
//        System.out.println( new String( resp.getBody() ) );

        // 当应答模式如果为autoAck则当消费者接受完消息，马上就给服务端应答ack
        // 当应答模式autoAck=false，则需要手动设置ack或者nack或者reject等其他动作来给mq应答对消息的处理情况


//        channel.basicReject(  );
//        channel.basicNack(  );
        /**
         * 将消息恢复到发送时一样。
         * 接受一个参数requeue，如果为true，则重新入队列，并且尽可能把消息发往其他消费者。
         * 如果为false，则不重新入队列，直接发往本消费者。
         */
//        channel.basicRecover(true);

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
