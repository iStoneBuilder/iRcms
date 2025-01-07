package com.stone.it.rcms.core.pay.service;

import com.alibaba.fastjson2.JSONObject;
import com.stone.it.rcms.core.annotate.RcmsMethod;
import com.stone.it.rcms.core.pay.vo.PayVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * 微信支付实现
 *
 * @author cj.stone
 * @Date 2025/1/7
 * @Desc
 */
@Produces({MediaType.APPLICATION_JSON})
@Consumes(MediaType.APPLICATION_JSON)
public interface IWeChatPayService {
	/**
	 * 创建订单
	 *
	 * @param payWay 支付方式
	 * @param paySource 支付来源
	 * @param payVO 订单信息
	 * @return 订单信息
	 */
	@POST
	@Path("/{pay_way}/{pay_source}/order")
	@RcmsMethod(name = "支付.创建订单", type = "Y")
	@RequiresPermissions("permission:pay:create")
	JSONObject pay(@PathParam("pay_way") String payWay, @PathParam("pay_source") String paySource, PayVO payVO);

}
