package com.example.Controller;

import com.example.application.domain.ARocketmqUserInfo;
import com.example.common.Result;
import com.example.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/userInfo")
public class UserInfoController {


    @Autowired
    private UserInfoService userInfoService;


    @RequestMapping(value = "getUserList", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Result getUserList(){
        try{
            List<ARocketmqUserInfo> list = this.userInfoService.getUserInfoList();
            return Result.getSuccessResp(list);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "saveUserOrder", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Result saveUserOrder(){
        try{
            this.userInfoService.saveUserAndOrder();
            return Result.getSuccessResp();
        }catch (Exception e){
            e.printStackTrace();
            return Result.getFailResp("save fail");
        }
    }


}
