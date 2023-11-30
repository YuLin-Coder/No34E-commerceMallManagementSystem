package pers.zjh.shop.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pers.zjh.shop.mapper.CreditMapper;
import pers.zjh.shop.pojo.Credit;
import pers.zjh.shop.pojo.CreditExample;
import pers.zjh.shop.pojo.User;
import pers.zjh.shop.service.CreditService;
import pers.zjh.shop.service.UserService;
import pers.zjh.shop.util.Page;

import java.util.List;

/**
 * @Description:    积分控制器
 */

@Controller
@RequestMapping("credit")
public class CreditController {

    @Autowired
    CreditService creditService;
    @Autowired
    UserService userService;
    @Autowired
    CreditMapper creditMapper;

    /**
     * @Describe    取得积分数据,分页展示
     * @param       page,model
     * @return
     */
    @RequestMapping("getCreditList")
    public String list(Model model, Page page){
        // 取出数据前设置分页参数
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<User> users = userService.list();
        if (null != users){
            // 得到记录总条数
            int total = (int) new PageInfo<>(users).getTotal();
            page.setTotal(total);
            model.addAttribute("page",page);
        }
        setUser(users);
        model.addAttribute("users",users);
        return "admin/listCredit";
    }

    /**
     * @Describe    跳转功能: 跳转到积分规则制定页面
     * @param
     * @return
     */
    @RequestMapping("creditRule")
    public String creditRule(){return "admin/creditRule";}

    /**
     * @Describe    为单个用户设置积分属性
     * @param       user
     * @return      void
     */
    public void setUser(User user){
        CreditExample example = new CreditExample();
        example.createCriteria().andUidEqualTo(user.getId());
        List<Credit> credits =  creditMapper.selectByExample(example);
        if (null != credits && !credits.isEmpty()){
            Credit credit = credits.get(0);
            user.setCredit(credit);
        }
    }

    /**
     * @Describe    为一组用户设置积分属性
     * @param       users
     * @return      void
     */
    public void setUser(List<User> users){
        for (User user : users){
            setUser(user);
        }
    }

}
