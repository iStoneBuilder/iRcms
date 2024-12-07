package com.stone.it.rcms.core.pay.dao;

import com.stone.it.rcms.core.pay.vo.OrderVO;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.mifi.common.vo.CommonVO;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cj.stone
 * @Date 2024/12/7
 * @Desc
 */
public interface IOrderDao {
    PageResult<OrderVO> findOrderPageResult(OrderVO orderVO, PageVO pageVO, List<CommonVO> list);

    OrderVO findOrderDetail(OrderVO orderVO);

    int updateOrder(OrderVO orderVO);

    int createOrder(OrderVO orderVO);

}
