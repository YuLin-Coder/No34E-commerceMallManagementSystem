package pers.zjh.shop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.zjh.shop.mapper.CreditMapper;
import pers.zjh.shop.pojo.Credit;
import pers.zjh.shop.pojo.CreditExample;
import pers.zjh.shop.service.CreditService;
import pers.zjh.shop.service.UserService;

import java.util.List;

/**
 * @Description:    积分业务逻辑接口实现层
 * @Author: Zhujinghui
 * @CreateDate: 2018/10/27 13:46
 */

@Service
public class CreditServiceImpl implements CreditService {

    @Autowired
    CreditMapper creditMapper;
    @Autowired
    UserService userService;

    /**
     * @Describe    查询所有积分数据
     * @param
     * @return      List<Credit>
     */
    @Override
    public List<Credit> list() {
        CreditExample example = new CreditExample();
        example.setOrderByClause("number desc");
        List<Credit> credits = creditMapper.selectByExample(example);
        return credits;
    }

    /**
     * @Describe    根据用户id 查询积分
     * @param       uid
     * @return
     */
    @Override
    public Credit get(Integer uid) {
        CreditExample example = new CreditExample();
        example.createCriteria().andUidEqualTo(uid);
        List<Credit> credits = creditMapper.selectByExample(example);
        return credits.get(0);
    }

    /**
     * @Describe    增肌积分记录
     * @param       credit
     * @return      void
     */
    @Override
    public void add(Credit credit) {
        creditMapper.insert(credit);
    }

    @Override
    public void update(Credit credit) {
        creditMapper.updateByPrimaryKeySelective(credit);
    }

}
