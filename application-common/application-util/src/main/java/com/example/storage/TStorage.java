package com.example.storage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author luxingyuan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "storage")
public class TStorage implements java.io.Serializable{
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(generator = "JDBC")
    private  Long id;

    @Column(name = "product_id")
    private Long product_id;
    @Column(name = "total")
    private Integer total;
    @Column(name = "used")
    private Integer used;
    @Column(name = "residue")
    private Integer residue;
}
