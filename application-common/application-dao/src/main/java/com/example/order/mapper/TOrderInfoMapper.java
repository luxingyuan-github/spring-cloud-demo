package com.example.order.mapper;

import com.example.order.TOrder;
import tk.mybatis.mapper.common.Mapper;

import java.util.Map;

/**
 * @author luxingyuan
 */
public interface TOrderInfoMapper extends Mapper<TOrder> {


    int updateStatus(Map<String,Object> map);

    int saveOrder(TOrder tOrder);

}
