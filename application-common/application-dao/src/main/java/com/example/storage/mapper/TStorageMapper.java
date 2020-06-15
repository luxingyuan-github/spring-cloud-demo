package com.example.storage.mapper;

import com.example.storage.TStorage;
import tk.mybatis.mapper.common.Mapper;

import java.util.Map;

/**
 * @author luxingyuan
 */
public interface TStorageMapper extends Mapper<TStorage> {

    int decrease(Map<String,Object> map);
}
