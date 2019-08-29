package com.example.service;

import com.example.order.domain.ARocketmqOrderInfo;

import java.util.List;

public interface OrderInfoService {


    List<ARocketmqOrderInfo> getOrderList()throws Exception;
}
