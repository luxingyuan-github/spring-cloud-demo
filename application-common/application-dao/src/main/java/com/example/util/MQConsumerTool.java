package com.example.util;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MQConsumerTool {

    private  String namesrvAddr ="47.96.156.35:9876;47.97.118.253:9876";

    private  String subscribe = "wowoTestCluster";

    public static void main(String[] args) {
        try {
            SimpleDateFormat format  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group");

            // Specify name server addresses.
            consumer.setNamesrvAddr("47.96.156.35:9876;47.97.118.253:9876");

            // Subscribe one more more topics to consume.
            consumer.subscribe("transactionCluster", "*");

            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);

            consumer.registerMessageListener(
                    (List<MessageExt> msgs,ConsumeConcurrentlyContext context) ->{
                        System.out.printf("%s,%s Receive New Messages: %s %n",format.format(new Date()), Thread.currentThread().getName(), msgs);

                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            );

            consumer.start();

            System.out.printf("Consumer Started.%n");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
