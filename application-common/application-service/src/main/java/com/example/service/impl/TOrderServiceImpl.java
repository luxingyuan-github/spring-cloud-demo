package com.example.service.impl;

import com.example.order.TOrder;
import com.example.order.mapper.TOrderInfoMapper;
import com.example.service.TOrderService;
import io.seata.core.context.RootContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

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
    public void updateStatus(Map<String,Object> map){
        System.out.println("[TOrderServiceImpl updateStatus] 当前 XID: {}"+ RootContext.getXID());
        this.tOrderInfoMapper.updateStatus(map);
    }
}
