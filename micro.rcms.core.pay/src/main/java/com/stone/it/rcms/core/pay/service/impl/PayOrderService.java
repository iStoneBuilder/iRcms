package com.stone.it.rcms.core.pay.service.impl;

import com.stone.it.rcms.core.pay.dao.IOrderDao;
import com.stone.it.rcms.core.pay.service.IPayOrderService;
import com.stone.it.rcms.core.pay.service.IPayService;
import com.stone.it.rcms.core.pay.vo.OrderVO;
import com.stone.it.rcms.core.pay.vo.PayVO;
import com.stone.it.rcms.core.util.UserUtil;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.core.common.service.ICommonService;
import com.stone.it.rcms.core.common.vo.CommonVO;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cj.stone
 * @Date 2024/12/7
 * @Desc
 */
@Named
public class PayOrderService implements IPayOrderService {

    @Inject
    private IOrderDao orderDao;

    @Inject
    private ICommonService commonService;

    @Inject
    private IPayService payService;

    @Override
    public PageResult<OrderVO> findOrderPageResult(OrderVO orderVO, PageVO pageVO) {
        List<CommonVO> list = new ArrayList<>();
        if (orderVO.getEnterpriseId() == null) {
            list = commonService.findEnterpriseListByParentId(UserUtil.getEnterpriseId());
        }
        return orderDao.findOrderPageResult(orderVO, pageVO, list);
    }

    @Override
    public OrderVO findOrderDetail(String orderNo, OrderVO orderVO) {
        orderVO.setOrderNo(orderNo);
        return orderDao.findOrderDetail(orderVO);
    }

    @Override
    public String refundOrder(String orderNo, OrderVO orderVO) throws Exception {
        PayVO payVO = new PayVO();
        payVO.setOrderNo(orderNo);
        payVO.setOrderAmount(Long.valueOf(orderVO.getOrderAmount()));
        payVO.setTenantId(orderVO.getTenantId());
        payVO.setEnterpriseId(orderVO.getCurrentEnterpriseId());
        // 计算退款金额
        return payService.refund(payVO);
    }

}
