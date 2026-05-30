package com.apex.monitor.utils;

import com.apex.monitor.domain.*;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.InetAddress;
import java.util.List;
import java.util.Properties;

/**
 * 服务器工具类
 *
 * @author apex
 */
public class ServerUtils {

    public static Server getServerInfo() {
        Server server = new Server();
        try {
            SystemInfo systemInfo = new SystemInfo();
            HardwareAbstractionLayer hardware = systemInfo.getHardware();

            // CPU 信息
            setCpuInfo(server.getCpu(), hardware.getProcessor());

            // 内存信息
            setMemoryInfo(server.getMemory(), hardware.getMemory());

            // JVM 信息
            setJvmInfo(server.getJvm());

            // 系统信息
            setSysInfo(server.getSys());

            // 磁盘信息
            setSysFileInfo(server.getSysFile(), systemInfo.getOperatingSystem());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return server;
    }

    private static void setCpuInfo(Cpu cpu, CentralProcessor processor) {
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        long[] ticks = processor.getSystemCpuLoadTicks();

        long nice = ticks[CentralProcessor.TickType.NICE.getIndex()] - prevTicks[CentralProcessor.TickType.NICE.getIndex()];
        long irq = ticks[CentralProcessor.TickType.IRQ.getIndex()] - prevTicks[CentralProcessor.TickType.IRQ.getIndex()];
        long softirq = ticks[CentralProcessor.TickType.SOFTIRQ.getIndex()] - prevTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()];
        long steal = ticks[CentralProcessor.TickType.STEAL.getIndex()] - prevTicks[CentralProcessor.TickType.STEAL.getIndex()];
        long cSys = ticks[CentralProcessor.TickType.SYSTEM.getIndex()] - prevTicks[CentralProcessor.TickType.SYSTEM.getIndex()];
        long user = ticks[CentralProcessor.TickType.USER.getIndex()] - prevTicks[CentralProcessor.TickType.USER.getIndex()];
        long iowait = ticks[CentralProcessor.TickType.IOWAIT.getIndex()] - prevTicks[CentralProcessor.TickType.IOWAIT.getIndex()];
        long idle = ticks[CentralProcessor.TickType.IDLE.getIndex()] - prevTicks[CentralProcessor.TickType.IDLE.getIndex()];
        long totalCpu = user + nice + cSys + idle + iowait + irq + softirq + steal;

        cpu.setCpuNum(processor.getLogicalProcessorCount());
        cpu.setTotal(totalCpu);
        cpu.setSys(cSys);
        cpu.setUsed(user);
        cpu.setFree(idle);
    }

    private static void setMemoryInfo(Memory memory, GlobalMemory globalMemory) {
        memory.setTotal(globalMemory.getTotal());
        memory.setUsed(globalMemory.getTotal() - globalMemory.getAvailable());
        memory.setFree(globalMemory.getAvailable());
    }

    private static void setJvmInfo(Jvm jvm) {
        Properties props = System.getProperties();
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        Runtime runtime = Runtime.getRuntime();

        jvm.setTotal(runtime.totalMemory());
        jvm.setMax(runtime.maxMemory());
        jvm.setFree(runtime.freeMemory());
        jvm.setUsed(runtime.totalMemory() - runtime.freeMemory());
        jvm.setVersion(props.getProperty("java.version"));
        jvm.setHome(props.getProperty("java.home"));
    }

    private static void setSysInfo(Sys sys) {
        Properties props = System.getProperties();
        try {
            sys.setComputerName(InetAddress.getLocalHost().getHostName());
            sys.setComputerIp(InetAddress.getLocalHost().getHostAddress());
        } catch (Exception e) {
            e.printStackTrace();
        }
        sys.setOsName(props.getProperty("os.name"));
        sys.setOsArch(props.getProperty("os.arch"));
        sys.setUserDir(props.getProperty("user.dir"));
    }

    private static void setSysFileInfo(SysFile sysFile, OperatingSystem os) {
        FileSystem fileSystem = os.getFileSystem();
        List<OSFileStore> fsArray = fileSystem.getFileStores();
        if (!fsArray.isEmpty()) {
            OSFileStore fs = fsArray.get(0);
            long free = fs.getFreeSpace();
            long total = fs.getTotalSpace();
            long used = total - free;

            sysFile.setDirName(fs.getMount());
            sysFile.setSysTypeName(fs.getType());
            sysFile.setTypeName(fs.getName());
            sysFile.setTotal(formatSize(total));
            sysFile.setFree(formatSize(free));
            sysFile.setUsed(formatSize(used));
            sysFile.setUsage(used * 100.0 / total);
        }
    }

    private static String formatSize(long size) {
        double gb = size / (1024.0 * 1024.0 * 1024.0);
        return String.format("%.2f GB", gb);
    }
}
