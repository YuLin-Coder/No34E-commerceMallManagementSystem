package pers.zjh.shop.service;

import pers.zjh.shop.pojo.Order;
import pers.zjh.shop.pojo.OrderItem;

import java.util.List;

/**
 * @Description:    订单项业务逻辑接口
 */

public interface OrderItemService {

    /**
     * @Describe    添加订单项
     * @param       orderItem
     * @return      void
     */
    void add(OrderItem orderItem);

    /**
     * @Describe    根据id 删除订单项
     * @param          id
     * @return      void
     */
    void delete(Integer id);

    /**
     * @Describe    更新订单项
     * @param       orderItem
     * @return      void
     */
    void update(OrderItem orderItem);

    /**
     * @Describe    根据id 查询订单项
     * @param       id
     * @return      OrderItem
     */
    OrderItem get(Integer id);

    /**
     * @Describe    查询所有订单项
     * @param
     * @return      List<OrderItem>
     */
    List<OrderItem> list();

    /**
     * @Describe    为一组订单填充必要属性
     * @param       orders
     * @return      void
     */
    void fillOrder(List<Order> orders);

    /**
     * @Describe    为订单填充必要属性，如总价格，订单项总数，订单项等
     * @param       order
     * @return      void
     */
    void fill(Order order);

    /**
     * @Describe    根据产品id 查询该产品销量
     * @param       productId
     * @return      int
     */
    int getSaleCount(Integer productId);

    /**
     * @Describe     根据会员id 查询该会员所有订单项
     * @param           userId
     * @return        List<OrderItem>
     */
    List<OrderItem> listByUser(Integer userId);

}
