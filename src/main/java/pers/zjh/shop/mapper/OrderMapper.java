package pers.zjh.shop.mapper;

import java.util.List;
import pers.zjh.shop.pojo.Order;
import pers.zjh.shop.pojo.OrderExample;

/**
 * @Description:    订单持久映射层
 */

public interface OrderMapper {

    /**
     * @Describe    根据id删除某一订单
     * @param        id
     * @return      int
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * @Describe    添加订单
     * @param       record
     * @return      int
     */
    int insert(Order record);

    /**
     * @Describe    添加订单，选择性插入数据
     * @param       record
     * @return      int
     */
    int insertSelective(Order record);

    /**
     * @Describe    根据设置的条件查询一堆订单
     * @param           example
     * @return           List<Order>
     */
    List<Order> selectByExample(OrderExample example);

    /**
     * @Describe    根据主键id 查询订单
     * @param       id
     * @return      Order
     */
    Order selectByPrimaryKey(Integer id);

    /**
     * @Describe    对字段进行判断再更新订单(如果为Null就忽略更新)
     * @param       record
     * @return      int
     */
    int updateByPrimaryKeySelective(Order record);

    /**
     * @Describe    对注入的字段全部更新
     * @param       record
     * @return      int
     */
    int updateByPrimaryKey(Order record);

}