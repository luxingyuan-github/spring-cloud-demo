package com.example.listener;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class TransactionListener {

    @PostConstruct
    public void consumer(){
        try {
            System.out.println("-------TransactionListener--------");
            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group");

            // Specify name server addresses.
            consumer.setNamesrvAddr("47.96.156.35:9876;47.97.118.253:9876");

            // Subscribe one more more topics to consume.
            consumer.subscribe("transactionCluster", "*");

            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
//            consumer.registerMessageListener(new MessageListenerConcurrently() {
//
//                @Override
//                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
//                                                                ConsumeConcurrentlyContext context) {
//                    for (MessageExt messageExt : msgs) {
//                        System.out.println(new String(messageExt.getBody()));
//                    }
//                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//                }
//            });


            consumer.registerMessageListener(
                    (List<MessageExt> msgs, ConsumeConcurrentlyContext context) ->{
                        System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
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
