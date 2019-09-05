package com.huifer.happy.common.entity.po;

public class Fileburst {
    /**
     * 主键
     */
    private Long id;

    /**
     * 文件名称
     */
    private String filename;

    /**
     * 分片id, 相同文件名+burst_id 组合后成为一个文件
     */
    private Integer burstId;

    /**
     * 文件地址
     */
    private String url;

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

    public Integer getBurstId() {
        return burstId;
    }

    public void setBurstId(Integer burstId) {
        this.burstId = burstId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}