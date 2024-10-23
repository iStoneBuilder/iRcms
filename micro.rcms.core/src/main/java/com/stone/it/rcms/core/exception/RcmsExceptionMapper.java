package com.stone.it.rcms.core.exception;

import com.stone.it.rcms.core.interceptor.JwtTokenOutInterceptor;
import com.stone.it.rcms.core.util.ResponseUtil;
import java.util.Arrays;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author cj.stone
 * @Date 2024/10/23
 * @Desc
 */
@Provider
public class RcmsExceptionMapper implements ExceptionMapper<Exception> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RcmsExceptionMapper.class);

    @Override
    public Response toResponse(Exception exception) {
        LOGGER.error("Exception occurred: ", exception);
        String name = exception.getClass().getName().replace(exception.getClass().getPackageName() + ".", "");
        if (Arrays.stream(RcmsExceptionEnum.values()).anyMatch(e -> e.name().equals(name))) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(ResponseUtil.responseBuild(RcmsExceptionEnum.valueOf(name).getCode(),
                    RcmsExceptionEnum.valueOf(name).getMessage(), exception.getMessage()))
                .type("application/json").build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity(ResponseUtil.responseBuild(500, "Internal Server Error", exception.getMessage()))
            .type("application/json").build();
    }
}
