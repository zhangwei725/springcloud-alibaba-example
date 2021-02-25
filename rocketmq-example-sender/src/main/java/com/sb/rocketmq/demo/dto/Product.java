package com.sb.rocketmq.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author zhangwei
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
    private Integer pid;
    private String title;
    private BigDecimal price;
}