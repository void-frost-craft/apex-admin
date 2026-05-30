package com.apex.monitor.domain;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 内存信息
 *
 * @author apex
 */
@Data
public class Memory implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private double total;
    private double used;
    private double free;
}
