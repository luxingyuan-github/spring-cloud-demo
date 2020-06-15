package com.example.service;

import com.example.order.TOrder;

import java.util.Map;

public interface TOrderService {



    void saveOrder(TOrder tOrder) throws Exception;

    void updateStatus(Map<String,Object> map) throws Exception;
}
