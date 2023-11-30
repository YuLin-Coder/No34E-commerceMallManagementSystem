package pers.zjh.shop.service;

import pers.zjh.shop.pojo.Order;
import pers.zjh.shop.pojo.OrderItem;

import java.util.List;

/**
 * @Description:    订单业务逻辑接口
 */

public interface OrderService {

    /**
     * @Describe    添加订单
     * @param       order
     * @return      void
     */
    void add(Order order);

    /**
     * @Describe    根据id 删除订单
     * @param       id
     * @return      void
     */
    void delete(Integer id);

    /**
     * @Describe    更新订单
     * @param       order
     * @return      void
     */
    void update(Order order);

    /**
     * @Describe    根据id 查询订单
     * @param       id
     * @return      Order
     */
    Order get(Integer id);

    /**
     * @Describe    查询所有订单
     * @param
     * @return       List<Order>
     */
    List<Order> list();

    /**
     * @Describe    新增订单,同时修改订单项数据,返回订单总价格
     * @param       order
     * @param       orderItems
     * @return      totalPrice
     */
    float add(Order order, List<OrderItem> orderItems);

    /**
     * @Describe    根据用户id 和订单状态查找订单
     * @param       uid
     * @param       status
     * @return      List<Order>
     */
    List<Order> list(Integer uid, String status);

    /**
     *  订单的几种状态值
     * */
    public static final String waitPay = "waitPay";
    public static final String waitDelivery = "waitDelivery";
    public static final String waitConfirm = "waitConfirm";
    public static final String finish = "finish";
    public static final String delete = "delete";

}
