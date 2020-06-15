package com.example.controller;

import com.example.common.Result;
import com.example.feign.OrderFeign;
import com.example.service.TxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/tx")
public class TxController {


    @Autowired
    TxService txService;

    @RequestMapping(value = "createOrder", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Result createOrder() throws Exception{
        this.txService.createOrder();
        return Result.getSuccessResp();
    }
}
