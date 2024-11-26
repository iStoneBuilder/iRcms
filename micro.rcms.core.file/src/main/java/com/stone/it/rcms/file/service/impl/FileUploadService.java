package com.stone.it.rcms.file.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.stone.it.rcms.file.service.IFileUploadService;
import java.io.InputStream;
import javax.inject.Named;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author cj.stone
 * @Date 2024/11/26
 * @Desc
 */
@Named
public class FileUploadService implements IFileUploadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadService.class);

    @Override
    public JSONObject uploadFile(InputStream fileInputStream, MultipartBody fileDetail) {
        // 获取文件附件
        Attachment fileAttachment = fileDetail.getAttachment("file");
        if (fileAttachment != null) {
            // 获取文件名
            String fileName = fileAttachment.getContentDisposition().getFilename();
            // 获取文件类型
            String contentType = fileAttachment.getContentType().toString();
            LOGGER.info("{}{}", fileName, contentType);
        }
        return null;
    }
}
