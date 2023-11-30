package pers.zjh.shop.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.zjh.shop.pojo.Admin;
import pers.zjh.shop.service.LgjService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("admin")
public class LgjController {
    @Autowired
    private LgjService lgjservice;

    @RequestMapping("finds")
    @ResponseBody
    public List<Admin> findsUser(@RequestParam(value="uname" ,required=false)String username, HttpSession session, @RequestParam(value="pageNo",required=false,defaultValue="1")int pageNo, @RequestParam(value="pageSize",required=false,defaultValue="13")int pageSize){
        System.out.println(username);
        PageHelper.startPage(pageNo, pageSize);
        List<Admin> list= lgjservice.findUser(username);
        PageInfo<Admin> page=new PageInfo<Admin>(list);
        session.setAttribute("list", list);
    return list;
    }

    @RequestMapping("test")
    public String test(){
        return "admin/lgjList";
    }
}
