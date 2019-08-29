package com.example.util;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MQProducerTool {

    private static final Logger logger = LoggerFactory.getLogger(MQProducerTool.class);

    private  String namesrvAddr ="47.96.156.35:9876;47.97.118.253:9876";

    private  String subscribe = "transactionCluster";


    public  void sendMsg( String tags, String msgBody) {
        logger.error("MQProducerTool sendMsg begin");
        logger.error("MQProducerTool sendMsg namesrvAddr:"+namesrvAddr +",topic:"+subscribe+" ,tags:"+tags+" ,msgBody"+msgBody);
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new
                DefaultMQProducer("group");
        try {

            // Specify name server addresses.
            producer.setNamesrvAddr(namesrvAddr);
            producer.setVipChannelEnabled(false);
            producer.setRetryTimesWhenSendFailed(3);
            //Launch the instance.
            producer.start();

            Message msg = new Message(subscribe ,
                    tags ,
                    msgBody.getBytes(RemotingHelper.DEFAULT_CHARSET)
            );
            /*
             * Call send message to deliver message to one of brokers.
             */
            SendResult sendResult = producer.send(msg);
            logger.error("MQProducerTool sendMsg id:--->" + sendResult.getMsgId() + ",result:--->" + sendResult.getSendStatus()
                    + " ," + sendResult.toString());
        } catch (Exception e) {
            logger.error("MQProducerTool sendMsg error", e);
        }
        //Shut down once the producer instance is not longer in use.
        producer.shutdown();
        logger.error("MQProducerTool sendMsg end");
    }

    public static void main(String[] args) {
        new MQProducerTool().sendMsg("test","msgBody");
    }
}
