package com.example.case_study_md3.controller.admin;

import com.example.case_study_md3.model.Order;
import com.example.case_study_md3.model.Product;
import com.example.case_study_md3.model.User;
import com.example.case_study_md3.service.OrderItemService;
import com.example.case_study_md3.service.OrderService;
import com.example.case_study_md3.service.ProductService;
import com.example.case_study_md3.service.UserService;
import com.example.case_study_md3.utils.Config;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name="DashboardServlet", urlPatterns = "/dashboard")
public class DashboardServlet extends HttpServlet {
    private ProductService productService;
    private OrderService orderService;
    private OrderItemService orderItemService;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        productService = new ProductService();
        orderService = new OrderService();
        orderItemService = new OrderItemService();
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null || !user.geteRole().name().equals("ADMIN")){
            resp.sendRedirect("/404");
            return;
        }

        List<Product> allProducts = productService.findAll();
        List<User> users = userService.findAll();
        List<Order> orders = orderService.findAll();
        List<Order> paidOrders = orderService.findAllPaidOrders();
        float totalIncome = 0;
        for (Order o: paidOrders
             ) {
            totalIncome += o.getSubTotal();
        }

        req.setAttribute("products",allProducts);
        req.setAttribute("users",users);
        req.setAttribute("orders",orders);
        req.setAttribute("totalIncome",totalIncome);
        req.getRequestDispatcher(Config.ADMIN+"dashboard.jsp").forward(req,resp);
    }
}
