package com.stone.it.rcms.core.pay.dao;

import com.stone.it.rcms.core.pay.vo.OrderVO;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.core.common.vo.CommonVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @author cj.stone
 * @Date 2024/12/7
 * @Desc
 */
public interface IOrderDao {

    PageResult<OrderVO> findOrderPageResult(@Param("param1") OrderVO orderVO, @Param("param2") PageVO pageVO,
        @Param("param3") List<CommonVO> list);

    OrderVO findOrderDetail(OrderVO orderVO);

    int updateOrder(OrderVO orderVO);

    int createOrder(OrderVO orderVO);

    int updateOrderRefund(OrderVO upVO);
}
