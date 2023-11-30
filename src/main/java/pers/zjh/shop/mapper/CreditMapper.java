package pers.zjh.shop.mapper;

import java.util.List;
import pers.zjh.shop.pojo.Credit;
import pers.zjh.shop.pojo.CreditExample;

/**
 * @Description:    积分持久映射层
 */

public interface CreditMapper {

    /**
     * @Describe    根据id删除某一积分记录
     * @param       id
     * @return      int
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * @Describe    添加积分记录
     * @param       record
     * @return      int
     */
    int insert(Credit record);

    /**
     * @Describe    添加积分记录，选择性插入数据
     * @param       record
     * @return      int
     */
    int insertSelective(Credit record);

    /**
     * @Describe    根据设置的条件查询一堆积分记录
     * @param       example
     * @return       List<Credit>
     */
    List<Credit> selectByExample(CreditExample example);

    /**
     * @Describe    根据主键id 查询积分记录
     * @param       id
     * @return      Credit
     */
    Credit selectByPrimaryKey(Integer id);

    /**
     * @Describe    对字段进行判断再更新积分记录(如果为Null就忽略更新)
     * @param       record
     * @return      int
     */
    int updateByPrimaryKeySelective(Credit record);

    /**
     * @Describe    对注入的字段全部更新
     * @param       record
     * @return      int
     */
    int updateByPrimaryKey(Credit record);

}