package com.example.feign;

import com.example.common.RequestData;
import com.example.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "application-order")
public interface OrderFeign {



    @RequestMapping(value = "/order/saveOrder", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    Result saveOrder(@RequestBody RequestData requestData);

    @RequestMapping(value = "/order/updateStatus", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    Result updateStatus(@RequestBody RequestData requestData);
}
