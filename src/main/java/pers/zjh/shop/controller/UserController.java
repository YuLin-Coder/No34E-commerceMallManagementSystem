package pers.zjh.shop.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pers.zjh.shop.pojo.User;
import pers.zjh.shop.service.UserService;
import pers.zjh.shop.util.Page;

import java.util.List;


/**
 * @Description:    会员控制器
 */

@Controller
@RequestMapping("user")
public class UserController{

    @Autowired
    UserService userService;

    /**
     * @Describe    获取会员数据,跳转到用户展示页面
     * @param       model,page
     * @return
     */
    @RequestMapping("getUserList")
    public String list(Model model, Page page){
        // 取出数据前设置分页参数
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<User> users = userService.list();
        // 获得用户总数
        int total = (int) new PageInfo<>(users).getTotal();
        page.setTotal(total);
        model.addAttribute("page",page);
        model.addAttribute("users",users);
        return "admin/listUser";
    }

    /**
     * @Describe    根据id 删除会员
     * @param       id
     * @return      重定向发起请求： 会员列表
     */
    @RequestMapping("user_delete")
    public String delete(Integer id){
        if (null == id){
            return "fail";
        }
        userService.delete(id);
        return "redirect:/user/getUserList";
    }

}
