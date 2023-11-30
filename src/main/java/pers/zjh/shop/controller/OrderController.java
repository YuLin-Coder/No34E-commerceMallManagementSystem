package pers.zjh.shop.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pers.zjh.shop.pojo.Order;
import pers.zjh.shop.service.OrderItemService;
import pers.zjh.shop.service.OrderService;
import pers.zjh.shop.util.Page;

import java.util.Date;
import java.util.List;

/**
 * @Description:    订单控制器
 */

@Controller
@RequestMapping("order")
public class OrderController {

    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemService orderItemService;

    /**
     * @Describe    分页取出订单数据，跳转到订单展示页面
     * @param       model,page
     * @return
     */
    @RequestMapping("getOrderList")
    public String list(Model model, Page page){
        // 取数据前设置分页参数
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Order> orders = orderService.list();
        // 得到订单总数
        int total = (int) new PageInfo<>(orders).getTotal();
        page.setTotal(total);
        // 给订单填充属性值
        orderItemService.fillOrder(orders);
        model.addAttribute("page",page);
        model.addAttribute("orders",orders);
        return "admin/listOrder";
    }

    /**
     * @Describe    订单发货，订单状态从待发货改为待确认
     * @param
     * @return
     */
    @RequestMapping("admin_order_delivery")
    public String delivery(Order order) {
        // 设置发货时间
        order.setDelivery_date(new Date());
        // 修改订单状态
        order.setStatus(OrderService.waitConfirm);
        orderService.update(order);
        return "redirect:/order/getOrderList";
    }
}
