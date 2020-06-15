package com.example.service.impl;

import com.example.common.RequestData;
import com.example.feign.OrderFeign;
import com.example.feign.AccountFeign;
import com.example.feign.StorageFeign;
import com.example.order.TOrder;
import com.example.service.TxService;
import com.example.util.BaseUtil;
import com.google.common.collect.ImmutableMap;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class TxServiceImpl implements TxService {


    @Autowired
    private AccountFeign accountFeign;

    @Autowired
    private OrderFeign orderFeign;

    @Autowired
    private StorageFeign storageFeign;




    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
//    @Transactional(rollbackFor =  Exception.class)
    public void createOrder() throws Exception{

        System.out.println("[reduceBalance] 当前 XID: {}"+ RootContext.getXID());

        System.out.println("-------下单开始---------");
        TOrder tOrder = new TOrder();
        tOrder.setId(null);
        tOrder.setUser_id(1L);
        tOrder.setProduct_id(1L);
        tOrder.setCount(2);
        tOrder.setMoney(new BigDecimal(10));
        tOrder.setStatus(0);


        RequestData orderRequestData = new RequestData();
        Map<String,Object> orderMap = BaseUtil.objectToMap(tOrder);
        orderRequestData.setRequestBody(orderMap);
        this.orderFeign.saveOrder(orderRequestData);


        System.out.println("-------减库存开始---------");

        RequestData storageRequestData = new RequestData();
        Map<String,Object> storageMap = ImmutableMap.<String,Object>builder()
                .put("count",tOrder.getCount())
                .put("product_id",tOrder.getProduct_id())
                .build();
        storageRequestData.setRequestBody(storageMap);
        this.storageFeign.decrease(storageRequestData);

        System.out.println("------扣减金额开始------");
        RequestData accountRequestData = new RequestData();
        Map<String,Object> accountMap = ImmutableMap.<String,Object>builder()
                .put("count",tOrder.getMoney())
                .put("user_id",tOrder.getUser_id())
                .build();
        accountRequestData.setRequestBody(accountMap);
        this.accountFeign.decrease(accountRequestData);


        Thread.sleep(15000);
        System.out.println("------修改订单状态-----");
        RequestData updateStatusRequestData = new RequestData();
        Map<String,Object> updateStatusMap = ImmutableMap.<String,Object>builder()
                .put("product_id",tOrder.getProduct_id())
                .build();
        updateStatusRequestData.setRequestBody(updateStatusRequestData);
        this.orderFeign.updateStatus(updateStatusRequestData);
    }

}
