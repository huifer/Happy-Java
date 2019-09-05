package com.huifer.happy.common.entity.bo;

import lombok.Data;

import java.util.List;

@Data
public class FileMetaBO {
    private String groupName;
    private String remoteFilename;
    private String storageServersCount;
    private List<String> ipPorts;
    private String url;
}
