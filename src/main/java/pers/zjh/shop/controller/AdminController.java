package pers.zjh.shop.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pers.zjh.shop.pojo.Admin;
import pers.zjh.shop.service.AdminService;
import pers.zjh.shop.util.Page;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description:  管理员控制器
 */

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    /**
     * @Describe 取得管理员数据,分页展示
     * @param     model,page
     * @return    admin/listAdmin
     */
    @RequestMapping("getAdminList")
    public String list(Model model, Page page){
        // 取数据前设置分页参数
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Admin> admins = adminService.list();
        // 获取数据总记录数
        int total = (int) new PageInfo<>(admins).getTotal();
        // 给分页类设置参数：总页数
        page.setTotal(total);
        model.addAttribute("page",page);
        model.addAttribute("admins",admins);
        return "admin/listAdmin";
    }

    /**
     * @Describe    根据id 删除管理员
     * @param       id
     * @return      重定向到管理员页面
     */
    @RequestMapping("admin_delete")
    public String delete(Integer id){
        if (null == id){
            return "fail";
        }
        adminService.delete(id);
        return "redirect:/admin/getAdminList";
    }

    /**
     * @Describe    添加管理员用户
     * @param       admin,model
     * @return
     */
    @RequestMapping("admin_add")
    public String add(Admin admin,Model model){
        if (null == admin){
            return "fail";
        }
        String name = admin.getName();
        // 标识数据库中是否已经存在即将添加的name
        boolean isExist = adminService.exist(name);
        if (isExist){
            String s = "用户名已经存在";
            model.addAttribute("message",s);
            return "admin/addAdmin";
        }
        adminService.add(admin);
        return "redirect:/admin/getAdminList";
    }

    /**
     * @Describe    进行后台管理员登录认证
     * @param       name,password
     * @return
     */
    @RequestMapping("admin_login")
    public String login(String name,String password, HttpSession session){
        if (null == name || null == password){
            return "failLogin";
        }
        Admin admin = adminService.get(name,password);
        // 用户不存在则回到登录界面
        if (null == admin){
            return "admin/login";
        }
        // 登录成功跳转到主页面
        session.setAttribute("admin",admin.getName());
        return "admin/adminHome";
    }

    /**
     * @Describe    跳转功能: 转到添加管理员界面
     * @param
     * @return
     */
    @RequestMapping("addAdminPage")
    public String addAdmin(){return "admin/addAdmin";}

    /**
     * @Describe    跳转功能: 跳转到管理员登录界面
     * @param
     * @return
     */
    @RequestMapping("adminLogin")
    public String login(){ return "admin/login"; }

}

