package com.example.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author luxingyuan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
public class TAccount implements java.io.Serializable{
    private static final long serialVersionUID = -4412352396156553921L;
    /**
     * 用户主键ID，从1000位开始递增
     */
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(generator = "JDBC")
    private Long id;


    @Column(name = "user_id")
    private Long user_id;


    @Column(name = "total")
    private Long total;

    @Column(name = "used")
    private Long used;

    @Column(name = "residue")
    private Long residue;

}