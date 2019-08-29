package com.example.mqListener;

import com.example.application.domain.ARocketmqUserInfo;
import com.example.application.mapper.ARocketmqUserInfoMapper;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/***
 *  
 * @ClassName
 * @Description: 保存
 * @author LXY
 * @date 2019/6/27 11:52 AM
 * @param 
 * @return 
 */
@Component
public class UserInfoTransactionListenerImpl implements TransactionListener {


    private ConcurrentHashMap<String, Integer> localTrans = new ConcurrentHashMap<>();


    @Autowired
    private ARocketmqUserInfoMapper rocketmqUserInfoMapper;


    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object o) {
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
        return LocalTransactionState.UNKNOW;
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
}
