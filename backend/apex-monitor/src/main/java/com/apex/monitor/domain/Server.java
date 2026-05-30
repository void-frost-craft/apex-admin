package com.apex.monitor.domain;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 服务器信息
 *
 * @author apex
 */
@Data
public class Server implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Cpu cpu = new Cpu();
    private Jvm jvm = new Jvm();
    private Memory memory = new Memory();
    private Sys sys = new Sys();
    private SysFile sysFile = new SysFile();
}
