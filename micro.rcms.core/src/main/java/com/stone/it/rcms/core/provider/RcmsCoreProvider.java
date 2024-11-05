package com.stone.it.rcms.core.provider;

import com.stone.it.rcms.core.exception.RcmsApplicationException;
import com.stone.it.rcms.core.exception.RcmsExceptionEnum;
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
public class RcmsCoreProvider implements ExceptionMapper<Exception> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RcmsCoreProvider.class);

    /**
     * 拦截所有异常，并转换为自定义的异常类型
     *
     * @param exception the exception to map to a response.
     * @return
     */
    @Override
    public Response toResponse(Exception exception) {
        LOGGER.error("Exception occurred: ", exception);
        String name = exception.getClass().getName().replace(exception.getClass().getPackageName() + ".", "");
        // 匹配枚举类型异常
        if (Arrays.stream(RcmsExceptionEnum.values()).anyMatch(e -> e.name().equals(name))) {
            return Response.status(RcmsExceptionEnum.valueOf(name).getCode())
                .entity(ResponseUtil.responseBuild(RcmsExceptionEnum.valueOf(name).getCode(),
                    RcmsExceptionEnum.valueOf(name).getMessage(), exception.getMessage()))
                .type("application/json").build();
        }
        // 匹配自定义异常
        if (name.equals("RcmsApplicationException")) {
            RcmsApplicationException rae = (RcmsApplicationException)exception;
            return Response.status(rae.getCode())
                .entity(ResponseUtil.responseBuild(rae.getCode(), rae.getMessage(), rae.getMessage()))
                .type("application/json").build();
        }
        // 未知异常
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity(ResponseUtil.responseBuild(500, "System Error", exception.getMessage())).type("application/json")
            .build();
    }

}
