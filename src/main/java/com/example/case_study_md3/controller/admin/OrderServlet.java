package com.example.case_study_md3.controller.admin;

import com.example.case_study_md3.model.Order;
import com.example.case_study_md3.model.OrderItem;
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

@WebServlet(name = "OrderServlet", value = "/order")
public class OrderServlet extends HttpServlet {
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
        String action = req.getParameter("action");
        if (user == null || !user.geteRole().name().equals("ADMIN")){
            resp.sendRedirect("/404");
            return;
        }
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "view":
                showView(req, resp);
                break;
            case "edit":
                showEdit(req, resp);
                break;
            case "change":
                doChange(req,resp);
                break;
            default:
                List<User> users = userService.findAll();
                List<Order> orders = orderService.findAll();

                req.setAttribute("orders",orders);
                req.setAttribute("users",users);
                req.getRequestDispatcher(Config.ADMIN_TO_ORDER+"list-order.jsp").forward(req,resp);
                break;
        }
    }

    private void showEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idOrder = Integer.parseInt(req.getParameter("id"));
        List<Product> products = productService.findAll();
        List<OrderItem> orderItems = orderItemService.findAllByIdOrder(idOrder);
        Order order = orderService.findOrderById(idOrder);
        if (order.isPaid()){
            resp.sendRedirect("/order");
            return;
        }
        order.setOrderItems(orderItems);

        req.setAttribute("order",order);
        req.setAttribute("products",products);
        req.getRequestDispatcher(Config.ADMIN_TO_ORDER+"edit-order.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "view":
                break;
            case "edit":
                int idOrder = Integer.parseInt(request.getParameter("idOrder"));
                int idProduct = Integer.parseInt(request.getParameter("idProduct"));
                orderItemService.remove(idOrder,idProduct);
                Order order = orderService.findOrderById(idOrder);
                updateOrderSubTotal(order);

                response.sendRedirect("/order?action=edit&id="+idOrder);
                break;
            default:
                break;
        }
    }

    private void showView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idOrder = Integer.parseInt(req.getParameter("id"));
        List<Product> products = productService.findAll();
        List<OrderItem> orderItems = orderItemService.findAllByIdOrder(idOrder);
        Order order = orderService.findOrderById(idOrder);
        order.setOrderItems(orderItems);

        req.setAttribute("order",order);
        req.setAttribute("products",products);
        req.getRequestDispatcher(Config.ADMIN_TO_ORDER+"view-order.jsp").forward(req, resp);
    }
    private void updateOrderSubTotal(Order unpaidOrder) {
        float subTotal = getSubTotal(unpaidOrder);
        unpaidOrder.setSubTotal(subTotal);
        orderService.update(unpaidOrder.getId(), unpaidOrder);
    }
    private float getSubTotal(Order unpaidOrder) {
        float subTotal = 0;
        List<OrderItem> orderItems = orderItemService.findAllByIdOrder(unpaidOrder.getId());
        for (OrderItem o : orderItems){
            subTotal += o.getTotal();
        }
        return subTotal;
    }
    private void doChange(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idProduct = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findProduct(idProduct);

        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int idOrder = Integer.parseInt(request.getParameter("idOrder"));

        if (quantity < 0){
            response.sendRedirect("/order?action=edit&id="+idOrder);
            return;
        }

        Order unpaidOrder = orderService.findOrderById(idOrder);
        if (unpaidOrder.isPaid()){
            response.sendRedirect("/order");
            return;
        }
        String cartMess;

        if (quantity > product.getLeftQuantity() || quantity > 10){
            cartMess = "Số lượng muốn mua không hợp lệ !";

            request.setAttribute("product",product);
            request.setAttribute("cartMess",cartMess);
//            response.sendRedirect("/product?action=view&id="+idProduct);
//            return;
            request.getRequestDispatcher(Config.ADMIN_TO_PRODUCT+"view-product.jsp").forward(request,response);
        }else {
            OrderItem orderItem = orderItemService.findOrderItem(unpaidOrder.getId(), idProduct);
            if (orderItem != null){
                if (quantity == 0){
                    orderItemService.remove(unpaidOrder.getId(),idProduct);
                    Order order = orderService.findOrderById(unpaidOrder.getId());
                    updateOrderSubTotal(order);

                    response.sendRedirect("/order?action=edit&id="+idOrder);
                    return;
                }

                orderItem.setQuantity(quantity);
                orderItem.setTotal(quantity * product.getPrice());
                orderItemService.update(orderItem.getId(), orderItem);
            }
            updateOrderSubTotal(unpaidOrder);

            response.sendRedirect("/order?action=edit&id="+idOrder);
        }
    }
}
