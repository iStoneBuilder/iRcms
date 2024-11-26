package com.stone.it.rcms.file.service;

import com.alibaba.fastjson2.JSONObject;
import java.io.InputStream;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;

/**
 *
 * @author cj.stone
 * @Date 2024/11/26
 * @Desc
 */
@Path("")
public interface IFileUploadService {

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    JSONObject uploadFile(@Multipart("file") InputStream fileInputStream, @Multipart("file") MultipartBody fileDetail);

}
