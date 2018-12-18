package com.example.entity;

/**
 * @Author: jiangm
 * @Description: 导入导出时间实体
 * @CreateDate: 2018/8/30 14:43
 * @Version: 空管PBCS平台事后分析系统
 */
public class ImportTime {
    private String startTime;
    private String endTime;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "ImportTime{" +
                "startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
