package pers.zjh.shop.service;

import pers.zjh.shop.pojo.Credit;

import java.util.List;

/**
 * @Description:    积分业务逻辑接口
 */

public interface CreditService {

    /**
     * @Describe    查询所有积分数据
     * @param
     * @return      List<Credit>
     */
    List<Credit> list();

    /**
     * @Describe    根据用户id 查询积分
     * @param       uid
     * @return
     */
    Credit get(Integer uid);

    /**
     * @Describe    增加积分记录
     * @param       credit
     * @return
     */
    void add(Credit credit);

    /**
     * @Describe    修改积分
     * @param       credit
     * @return
     */
    void update(Credit credit);
}
