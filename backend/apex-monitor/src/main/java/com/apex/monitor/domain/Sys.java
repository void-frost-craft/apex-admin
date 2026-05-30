package com.apex.monitor.domain;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 系统信息
 *
 * @author apex
 */
@Data
public class Sys implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String osName;
    private String osArch;
    private String computerName;
    private String computerIp;
    private String userDir;
}
