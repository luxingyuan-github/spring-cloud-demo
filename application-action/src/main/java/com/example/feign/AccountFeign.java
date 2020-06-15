package com.example.feign;

import com.example.common.RequestData;
import com.example.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "application-mba")
public interface AccountFeign {

    @RequestMapping(value = "/account/decrease", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    Result decrease(@RequestBody RequestData requestData);
}
