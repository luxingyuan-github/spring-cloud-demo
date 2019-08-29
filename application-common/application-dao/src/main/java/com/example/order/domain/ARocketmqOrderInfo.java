package com.example.order.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "a_rocketmq_order_info")
public class ARocketmqOrderInfo implements java.io.Serializable {
    private static final long serialVersionUID = 7938703389030219137L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_no")
    private String orderNo;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "del_status")
    private String delStatus;

    /**
     * 最后更新时间
     */
    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 最后更新时间
     */
    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

}
