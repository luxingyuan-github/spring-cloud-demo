package com.example.application.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "a_rocketmq_user_info")
public class ARocketmqUserInfo implements java.io.Serializable{
    private static final long serialVersionUID = -4412352396156553921L;
    /**
     * 用户主键ID，从1000位开始递增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户手机号码，唯一序列
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 密码，存储的是加密后的密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 用户昵称
     */
    @Column(name = "nickname")
    private String nickname;

    /**
     * 用户签名
     */
    @Column(name = "signature")
    private String signature;

    /**
     * 订单数量
     */
    @Column(name = "orderNum")
    private Long ordernum;

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