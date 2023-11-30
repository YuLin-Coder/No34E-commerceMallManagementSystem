package pers.zjh.shop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.zjh.shop.mapper.OrderItemMapper;
import pers.zjh.shop.pojo.*;
import pers.zjh.shop.service.OrderItemService;
import pers.zjh.shop.service.ProductService;

import java.util.List;

/**
 * @Description:    订单项业务逻辑接口实现层
 * @Author: Zhujinghui
 * @CreateDate: 2018/10/27 13:46
 */

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    OrderItemMapper orderItemMapper;
    @Autowired
    ProductService productService;

    /**
     * @Describe    添加订单项
     * @param       orderItem
     * @return      void
     */
    @Override
    public void add(OrderItem orderItem) {
        orderItemMapper.insert(orderItem);
    }

    /**
     * @Describe    根据id 删除订单项
     * @param          id
     * @return      void
     */
    @Override
    public void delete(Integer id) {
        orderItemMapper.deleteByPrimaryKey(id);
    }

    /**
     * @Describe    更新订单项
     * @param       orderItem
     * @return      void
     */
    @Override
    public void update(OrderItem orderItem) {
        orderItemMapper.updateByPrimaryKeySelective(orderItem);
    }

    /**
     * @Describe    根据id 查询订单项
     * @param       id
     * @return      OrderItem
     */
    @Override
    public OrderItem get(Integer id) {
        OrderItem orderItem = orderItemMapper.selectByPrimaryKey(id);
        // 为该订单项设置商品属性
        setProduct(orderItem);
        return orderItem;
    }

    /**
     * @Describe    查询所有订单项
     * @param
     * @return      List<OrderItem>
     */
    @Override
    public List<OrderItem> list() {
        OrderItemExample example = new OrderItemExample();
        example.setOrderByClause("id desc");
        return orderItemMapper.selectByExample(example);
    }

    /**
     * @Describe    为一组订单填充必要属性
     * @param       orders
     * @return      void
     */
    @Override
    public void fillOrder(List<Order> orders) {
        if (null != orders){
            for (Order order : orders){
                fill(order);
            }
        }
    }

    /**
     * @Describe    为订单填充必要属性，如总价格，订单项总数，订单项等
     * @param       order
     * @return      void
     */
    @Override
    public void fill(Order order) {
        if (null != order) {
            // 根据订单id 查询该订单里所有的订单项
            OrderItemExample example = new OrderItemExample();
            example.createCriteria().andOidEqualTo(order.getId());
            example.setOrderByClause("id desc");
            List<OrderItem> orderItems = orderItemMapper.selectByExample(example);

            if (null != orderItems) {
                // 为订单项设置商品属性
                setProduct(orderItems);
                // 订单总价格
                float totalPrice = 0;
                // 订单里的订单项数量
                int totalNumber = 0;
                for (OrderItem orderItem : orderItems) {
                    if (null != orderItem.getProduct() &&  null != orderItem.getNumber()){
                        totalPrice += orderItem.getNumber() * (orderItem.getProduct().getPromote_price());
                        totalNumber += orderItem.getNumber();
                    }
                }
                // 为订单设置总价格
                order.setTotalPrice(totalPrice);
                // 为订单设置订单项总数量
                order.setTotalNumber(totalNumber);
                // 为订单填充订单项
                order.setOrderItems(orderItems);
            }
        }
    }

    /**
     * @Describe    根据产品id 查询该产品销量
     * @param       productId
     * @return      int
     */
    @Override
    public int getSaleCount(Integer productId) {
        // 先查出含有该产品的订单
        OrderItemExample example = new OrderItemExample();
        example.createCriteria().andPidEqualTo(productId).andOidIsNotNull();
        List<OrderItem> orderItems = orderItemMapper.selectByExample(example);

        // 销量
        int result = 0;
        for (OrderItem orderItem : orderItems){
            result += orderItem.getNumber();
        }
        return result;
    }

    /**
     * @Describe     根据会员id 查询该会员所有订单项
     * @param           userId
     * @return        List<OrderItem>
     */
    @Override
    public List<OrderItem> listByUser(Integer userId) {
        // 根据会员id 查询该会员所有订单项
        OrderItemExample example = new OrderItemExample();
        example.createCriteria().andUidEqualTo(userId).andOidIsNull();
        List<OrderItem> orderItems = orderItemMapper.selectByExample(example);

        // 给订单项设置产品属性
        if (null != orderItems){
            setProduct(orderItems);
        }
        return orderItems;
    }


    /**
     * @Describe    为一组订单项的每个订单分别填充商品属性
     * @param       orderItems
     * @return      void
     */
    public void setProduct(List<OrderItem> orderItems){
        for (OrderItem orderItem: orderItems) {
            setProduct(orderItem);
        }
    }

    /**
     * @Describe    根据订单项的productId 找到该商品，为订单项设置商品属性
     * @param       orderItem
     * @return      void
     */
    private void setProduct(OrderItem orderItem) {
        Product product = productService.get(orderItem.getPid());
        orderItem.setProduct(product);
    }

}
