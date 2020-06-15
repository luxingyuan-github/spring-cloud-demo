package com.example.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order")
public class TOrder implements java.io.Serializable {
    private static final long serialVersionUID = 7938703389030219137L;
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @Column(name = "user_id")
    private  Long user_id;

    @Column(name = "product_id")
    private Long product_id;

    @Column(name = "count")
    private Integer count;

    @Column(name = "money")
    private BigDecimal money;

    @Column(name = "status")
    private Integer status;

}
