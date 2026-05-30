package com.apex.monitor.domain;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * CPU 信息
 *
 * @author apex
 */
@Data
public class Cpu implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int cpuNum;
    private double total;
    private double sys;
    private double used;
    private double free;
}
