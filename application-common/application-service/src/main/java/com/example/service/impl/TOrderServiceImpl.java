package com.example.service.impl;

import com.example.order.TOrder;
import com.example.order.mapper.TOrderInfoMapper;
import com.example.service.TOrderService;
import io.seata.core.context.RootContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Random;

@Service
public class TOrderServiceImpl implements TOrderService {

    @Autowired
    private TOrderInfoMapper tOrderInfoMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrder(TOrder tOrder) throws Exception {
        System.out.println("[TOrderServiceImpl saveOrder] 当前 XID: {}"+ RootContext.getXID());
        this.tOrderInfoMapper.saveOrder(tOrder);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStatus(Map<String,Object> map) throws Exception {
        System.out.println("[TOrderServiceImpl updateStatus] 当前 XID: {}"+ RootContext.getXID());
        Random rm = new Random();
        int random = rm.nextInt(100)+1;
        if(random % 2 == 0){
            throw new Exception("随机异常");
        }
        this.tOrderInfoMapper.updateStatus(map);
    }
}
