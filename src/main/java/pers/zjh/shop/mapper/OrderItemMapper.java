package pers.zjh.shop.mapper;

import java.util.List;
import pers.zjh.shop.pojo.OrderItem;
import pers.zjh.shop.pojo.OrderItemExample;

/**
 * @Description:    订单项持久映射层
 */

public interface OrderItemMapper {

    /**
     * @Describe    根据id删除某一订单项
     * @param
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * @Describe    添加订单项
     * @param       orderItem
     * @return      int
     */
    int insert(OrderItem orderItem);

    /**
     * @Describe    添加订单项，选择性插入数据
     * @param       record
     * @return      int
     */
    int insertSelective(OrderItem record);

    /**
     * @Describe    根据设置的条件查询一堆订单项
     * @param       example
     * @return       List<OrderItem>
     */
    List<OrderItem> selectByExample(OrderItemExample example);

    /**
     * @Describe    根据主键id 查询订单项
     * @param       id
     * @return      OrderItem
     */
    OrderItem selectByPrimaryKey(Integer id);

    /**
     * @Describe    对字段进行判断再更新订单项(如果为Null就忽略更新)
     * @param       orderItem
     * @return      int
     */
    int updateByPrimaryKeySelective(OrderItem orderItem);

    /**
     * @Describe    对注入的字段全部更新
     * @param       orderItem
     * @return      int
     */
    int updateByPrimaryKey(OrderItem orderItem);

}