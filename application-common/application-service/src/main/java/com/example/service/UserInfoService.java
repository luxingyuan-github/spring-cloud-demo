package com.example.service;

import com.example.application.domain.ARocketmqUserInfo;

import java.util.List;

public interface UserInfoService {

    List<ARocketmqUserInfo> getUserInfoList() throws Exception;

    void saveUserAndOrder() throws Exception;

}
