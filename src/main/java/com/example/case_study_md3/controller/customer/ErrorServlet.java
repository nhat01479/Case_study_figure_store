package com.example.case_study_md3.controller.customer;

import com.example.case_study_md3.model.Order;
import com.example.case_study_md3.model.OrderItem;
import com.example.case_study_md3.model.Product;
import com.example.case_study_md3.model.User;
import com.example.case_study_md3.service.OrderItemService;
import com.example.case_study_md3.service.OrderService;
import com.example.case_study_md3.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ErrorServlet", value = "/404")
public class ErrorServlet extends HttpServlet {
    private ProductService productService;
    private OrderService orderService;
    private OrderItemService orderItemService;

    @Override
    public void init() throws ServletException {
        productService = new ProductService();
        orderService = new OrderService();
        orderItemService = new OrderItemService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            Order unpaidOrder = orderService.findUserUnPaidOrder(user.getId());
            List<Product> products = productService.findAll();
            List<OrderItem> orderItems = null;
            if (unpaidOrder != null) {
                orderItems = orderItemService.findAllByIdOrder(unpaidOrder.getId());
                unpaidOrder.setOrderItems(orderItems);
            }
            request.setAttribute("order", unpaidOrder);
            request.setAttribute("products", products);
        }
        request.getRequestDispatcher("/WEB-INF/homepage/404.jsp").forward(request, response);
    }
}
