package com.ljy.oneclub.entity;

public class SysInfo {
    private int memoryRatio;
    /*cpu使用率.*/
    private int cpuRatio;

    public int getMemoryRatio() {
        return memoryRatio;
    }

    public void setMemoryRatio(int memoryRatio) {
        this.memoryRatio = memoryRatio;
    }

    public int getCpuRatio() {
        return cpuRatio;
    }

    public void setCpuRatio(int cpuRatio) {
        this.cpuRatio = cpuRatio;
    }
}
