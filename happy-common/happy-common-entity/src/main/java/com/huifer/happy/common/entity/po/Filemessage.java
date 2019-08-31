package com.huifer.happy.common.entity.po;

public class Filemessage {
    /**
    * 主键
    */
    private Long id;

    /**
    * 文件名称
    */
    private String filename;

    /**
    * 文件大小
    */
    private Long size;

    /**
    * 分片数
    */
    private Integer burstSize;

    /**
    * 用户id
    */
    private Long userId;

    /**
    * 创建时间
    */
    private Long careteTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Integer getBurstSize() {
        return burstSize;
    }

    public void setBurstSize(Integer burstSize) {
        this.burstSize = burstSize;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCareteTime() {
        return careteTime;
    }

    public void setCareteTime(Long careteTime) {
        this.careteTime = careteTime;
    }
}