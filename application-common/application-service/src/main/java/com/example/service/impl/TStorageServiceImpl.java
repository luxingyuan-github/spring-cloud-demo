package com.example.service.impl;

import com.example.service.TStorageService;
import com.example.storage.mapper.TStorageMapper;
import io.seata.core.context.RootContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
@Service
public class TStorageServiceImpl implements TStorageService {

    @Autowired
    private TStorageMapper  tStorageMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int decrease(Map<String, Object> map) {
        System.out.println("[TStorageServiceImpl decrease] 当前 XID: {}"+ RootContext.getXID());
        int updateNum =  tStorageMapper.decrease(map);
        return updateNum;
    }
}
