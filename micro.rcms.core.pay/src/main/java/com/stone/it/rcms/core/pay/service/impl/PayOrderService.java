package com.stone.it.rcms.core.pay.service.impl;

import com.stone.it.rcms.core.pay.dao.IOrderDao;
import com.stone.it.rcms.core.pay.service.IPayOrderService;
import com.stone.it.rcms.core.pay.vo.OrderVO;
import com.stone.it.rcms.core.util.UserUtil;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.mifi.common.service.ICommonService;
import com.stone.it.rcms.mifi.common.vo.CommonVO;

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
    public OrderVO updateOrder(String orderNo, OrderVO orderVO) {
        orderVO.setOrderNo(orderNo);
        orderDao.updateOrder(orderVO);
        return orderVO;
    }
}
