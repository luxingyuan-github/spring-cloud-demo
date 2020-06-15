package com.example.controller;

import com.example.common.RequestData;
import com.example.common.Result;
import com.example.order.TOrder;
import com.example.service.TOrderService;
import com.example.util.BaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping(value = "/order")
public class TOrderController {

    @Autowired
    private TOrderService TOrderService;

    @RequestMapping(value = "saveOrder", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Result saveOrder(@RequestBody RequestData requestData) throws Exception{
        Map<String,Object> requestBody = requestData.getRequestBody();
        TOrder tOrder = BaseUtil.populate(requestBody, TOrder.class);
        this.TOrderService.saveOrder(tOrder);
        return Result.getSuccessResp();
    }


    @RequestMapping(value = "updateStatus", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Result updateStatus(@RequestBody RequestData requestData) throws Exception{
        Map<String,Object> requestBody = requestData.getRequestBody();
        this.TOrderService.updateStatus(requestBody);
        return Result.getSuccessResp();
    }
}
