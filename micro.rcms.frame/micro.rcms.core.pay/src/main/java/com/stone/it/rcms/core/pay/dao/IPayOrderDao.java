package com.stone.it.rcms.core.pay.dao;

import com.stone.it.rcms.core.pay.vo.PayOrderVO;

/**
 * @author cj.stone
 * @Date 2025/1/7
 * @Desc
 */
public interface IPayOrderDao {
	int createOrder(PayOrderVO orderVO);
	int updateOrder(PayOrderVO orderVO);
	int updateOrderRefund(PayOrderVO upVO);
	PayOrderVO findOrderDetail(PayOrderVO orderVO);
}
