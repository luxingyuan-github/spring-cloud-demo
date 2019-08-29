package com.example.service.impl;

import com.example.order.domain.ARocketmqOrderInfo;
import com.example.order.mapper.ARocketmqOrderInfoMapper;
import com.example.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    private ARocketmqOrderInfoMapper rocketmqOrderInfoMapper;

    @Override
    public List<ARocketmqOrderInfo> getOrderList() throws Exception {
        return this.rocketmqOrderInfoMapper.selectAll();
    }
}
