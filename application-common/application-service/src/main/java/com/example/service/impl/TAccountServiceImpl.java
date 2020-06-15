package com.example.service.impl;

import com.example.application.TAccount;
import com.example.application.mapper.TAccountMapper;
import com.example.service.TAccountService;
import io.seata.core.context.RootContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class TAccountServiceImpl implements TAccountService {


    @Autowired
    private TAccountMapper tAccountMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void decrease(Map<String,Object> map) throws Exception {
        System.out.println("[TAccountServiceImpl] 当前 XID: {}"+ RootContext.getXID());
        tAccountMapper.decrease(map);
    }


}
