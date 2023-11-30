package pers.zjh.shop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.zjh.shop.mapper.OrderMapper;
import pers.zjh.shop.pojo.Order;
import pers.zjh.shop.pojo.OrderExample;
import pers.zjh.shop.pojo.OrderItem;
import pers.zjh.shop.pojo.User;
import pers.zjh.shop.service.OrderItemService;
import pers.zjh.shop.service.OrderService;
import pers.zjh.shop.service.UserService;

import java.util.List;

/**
 * @Description:    订单业务逻辑接口实现层
 * @Author: Zhujinghui
 * @CreateDate: 2018/10/27 13:49
 */

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;
    @Autowired
    UserService userService;
    @Autowired
    OrderItemService orderItemService;

    /**
     * @Describe    添加订单
     * @param       order
     * @return      void
     */
    @Override
    public void add(Order order) {
        orderMapper.insert(order);
    }

    /**
     * @Describe    根据id 删除订单
     * @param       id
     * @return      void
     */
    @Override
    public void delete(Integer id) {
        orderMapper.deleteByPrimaryKey(id);
    }

    /**
     * @Describe    更新订单
     * @param       order
     * @return      void
     */
    @Override
    public void update(Order order) {
        orderMapper.updateByPrimaryKeySelective(order);
    }

    /**
     * @Describe    根据id 查询订单
     * @param       id
     * @return      Order
     */
    @Override
    public Order get(Integer id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    /**
     * @Describe    查询所有订单
     * @param
     * @return       List<Order>
     */
    @Override
    public List<Order> list() {
        OrderExample example = new OrderExample();
        example.setOrderByClause("id desc");
        List<Order> orders = orderMapper.selectByExample(example);
        // 为订单设置会员属性
        setUser(orders);
        return orders;
    }

    /**
     * @Describe    新增订单,同时修改订单项数据,返回订单总价格
     * @param       order
     * @param       orderItems
     * @return      totalPrice
     */
    @Override
    public float add(Order order, List<OrderItem> orderItems) {
        // 订单总价格
        float totalPrice = 0;
        // 添加订单
        add(order);

        for (OrderItem orderItem : orderItems){
            // 为订单项设置orderId属性
            orderItem.setOid(order.getId());
            // 更新订单项
            orderItemService.update(orderItem);
            // 计算总价格
            totalPrice += orderItem.getProduct().getPromote_price()*orderItem.getNumber();
        }
        return totalPrice;
    }

    /**
     * @Describe    根据用户id 和订单状态查找订单
     * @param       uid
     * @param       status
     * @return      List<Order>
     */
    @Override
    public List<Order> list(Integer uid, String status) {
        OrderExample example = new OrderExample();
        example.createCriteria().andUidEqualTo(uid).andStatusNotEqualTo(status);
        return orderMapper.selectByExample(example);
    }

    /**
     * @Describe     为一组订单设置会员属性
     * @param           os
     * @return      void
     */
    public void setUser(List<Order> os){
        for (Order o : os)
            setUser(o);
    }

    /**
     * @Describe    为单个订单设置会员属性
     * @param       order
     * @return      void
     */
    public void setUser(Order order){
        int uid = order.getUid();
        User user = userService.get(uid);
        order.setUser(user);
    }

}
