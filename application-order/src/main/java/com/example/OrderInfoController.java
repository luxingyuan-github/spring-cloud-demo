package com.example;

import com.example.common.Result;
import com.example.order.domain.ARocketmqOrderInfo;
import com.example.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/orderInfo")
public class OrderInfoController {

    @Autowired
    private OrderInfoService orderInfoService;

    @RequestMapping(value = "getOrderList", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Result getOrderList(){
        try{
            List<ARocketmqOrderInfo> list = this.orderInfoService.getOrderList();
            return Result.getSuccessResp(list);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
