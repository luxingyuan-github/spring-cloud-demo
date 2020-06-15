package com.example.application.mapper;

import com.example.application.TAccount;
import tk.mybatis.mapper.common.Mapper;

import java.util.Map;

public interface TAccountMapper extends Mapper<TAccount> {

    int decrease(Map<String,Object> map);
}