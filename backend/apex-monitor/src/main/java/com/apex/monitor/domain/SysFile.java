package com.apex.monitor.domain;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 磁盘信息
 *
 * @author apex
 */
@Data
public class SysFile implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String dirName;
    private String sysTypeName;
    private String typeName;
    private String total;
    private String free;
    private String used;
    private double usage;
}
