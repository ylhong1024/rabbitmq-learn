package com.ylhong.rabbitmq.workqueues;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.ylhong.rabbitmq.utils.MQUtils;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/**
 * @author ylhong
 * @date 2021/10/23
 */
public class NewTask {

    public static void main( String[] args ) {
        try (Connection conn = MQUtils.getConnection();
             Channel channel = conn.createChannel()) {
            channel.queueDeclare( "hello", true, false, false, null );
            final List<String> taskArr = Arrays.asList( "first msg.", "second msg..", "third msg...", "fourth msg....", "fifth msg....." );

            for (String task : taskArr) {
                channel.basicPublish( "", "hello", null, task.getBytes( StandardCharsets.UTF_8 ) );
                System.out.println("[x] Sent '" + task + "'");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
