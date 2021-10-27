package com.ylhong.rabbitmq.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author ylhong
 * @date 2021/10/23
 */
public class MQUtils {

    public static Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost( "172.16.62.7" );
        factory.setPort( 5672 );
        factory.setUsername( "admin" );
        factory.setPassword( "admin" );
        return factory.newConnection();
    }

    public static void release( Connection conn, Channel channel ) {
        if (conn != null) {
            try {
                conn.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (channel != null) {
            try {
                channel.close();
            } catch (IOException | TimeoutException e) {
                e.printStackTrace();
            }
        }
    }

}
