package com.apex.monitor.domain;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * JVM 信息
 *
 * @author apex
 */
@Data
public class Jvm implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private double total;
    private double max;
    private double free;
    private double used;
    private String version;
    private String home;
}
