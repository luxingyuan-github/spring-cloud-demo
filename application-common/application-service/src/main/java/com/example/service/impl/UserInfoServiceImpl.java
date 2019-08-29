package com.example.service.impl;

import com.example.application.domain.ARocketmqUserInfo;
import com.example.application.mapper.ARocketmqUserInfoMapper;
import com.example.mqListener.UserInfoTransactionListenerImpl;
import com.example.service.UserInfoService;
import com.github.pagehelper.PageHelper;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

@Service
public class UserInfoServiceImpl implements UserInfoService {


    @Autowired
    private ARocketmqUserInfoMapper rocketmqUserInfoMapper;

    private static final String namesrvAddr ="47.96.156.35:9876;47.97.118.253:9876";

    private static final String subscribe = "transactionCluster";

    @Autowired
    private UserInfoTransactionListenerImpl userInfoTransactionListener;

    @Override
    public List<ARocketmqUserInfo> getUserInfoList() throws Exception {
        ARocketmqUserInfo userInfo = new ARocketmqUserInfo();
        PageHelper.startPage(1, 1);
        return this.rocketmqUserInfoMapper.selectAll();
    }

    @Override
    public void saveUserAndOrder() throws Exception {
        TransactionMQProducer transactionMQProducer = new TransactionMQProducer("group");
        ExecutorService executorService = new ThreadPoolExecutor(2, 5, 100, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2000), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("client-transaction-msg-check-thread");
                return thread;
            }
        });
        transactionMQProducer.setNamesrvAddr(namesrvAddr);
        transactionMQProducer.setVipChannelEnabled(false);
        transactionMQProducer.setRetryTimesWhenSendFailed(3);

        transactionMQProducer.setExecutorService(executorService);
//        transactionMQProducer.setTransactionListener(userInfoTransactionListener);
        transactionMQProducer.setTransactionListener(new TransactionListener() {

            private ConcurrentHashMap<String, Integer> localTrans = new ConcurrentHashMap<>();
            @Override
            public LocalTransactionState executeLocalTransaction(Message message, Object arg) {
                System.out.println("---------executeLocalTransaction---------");
                ARocketmqUserInfo userInfo = new ARocketmqUserInfo();
                userInfo.setNickname("transNickName");
                userInfo.setPassword("transPassword");
                userInfo.setCreateTime(new Date());
                userInfo.setOrdernum(1L);
                userInfo.setPhone("189");
                userInfo.setSignature("transSignature");
                int num = rocketmqUserInfoMapper.insert(userInfo);
                localTrans.put(message.getTransactionId(), num);
                if(num > 0){
                    return LocalTransactionState.COMMIT_MESSAGE;
                }else {
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                }
            }

            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
                System.out.println("---------checkLocalTransaction---------");
                Integer status = localTrans.get(messageExt.getTransactionId());
                if (status == 1) {
                    return LocalTransactionState.COMMIT_MESSAGE;
                }else {
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                }
            }
        });


        transactionMQProducer.start();
        try {

            Message msg = new Message(subscribe ,
                    "test" ,
                    "msgBody".getBytes(RemotingHelper.DEFAULT_CHARSET)
            );
            SendResult sendResult = transactionMQProducer.sendMessageInTransaction(msg, null);
            System.out.printf("%s%n", sendResult);
        }catch (Exception e){
            e.printStackTrace();
        }
        Thread.sleep(1000);
        transactionMQProducer.shutdown();
    }


}
