package pers.zjh.shop.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import pers.zjh.shop.pojo.Category;
import pers.zjh.shop.pojo.OrderItem;
import pers.zjh.shop.pojo.User;
import pers.zjh.shop.service.CategoryService;
import pers.zjh.shop.service.OrderItemService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description:    前端展示拦截器，生成视图前获取 一些信息
 */

public class ForeShowInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    CategoryService categoryService;
    @Autowired
    OrderItemService orderItemService;

    /* 在业务处理器处理请求执行完成后,生成视图之前执行的动作 */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView){
        // 获取分类集合信息, 用于放在搜索栏下面
        List<Category> categories = categoryService.list();
        request.getSession().setAttribute("categories",categories);

        // 获取购物车数量
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int cartTotalItemNumber = 0;
        if (null != user){
            List<OrderItem> orderItems = orderItemService.listByUser(user.getId());
            for (OrderItem orderItem : orderItems){
                cartTotalItemNumber += orderItem.getNumber();
            }
        }
        session.setAttribute("cartTotalItemNumber",cartTotalItemNumber);
    }

}
