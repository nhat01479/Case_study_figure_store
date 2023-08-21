package com.example.case_study_md3.controller.customer;

import com.example.case_study_md3.model.Order;
import com.example.case_study_md3.model.OrderItem;
import com.example.case_study_md3.model.Product;
import com.example.case_study_md3.model.User;
import com.example.case_study_md3.service.OrderItemService;
import com.example.case_study_md3.service.OrderService;
import com.example.case_study_md3.service.ProductService;
import com.example.case_study_md3.service.UserService;
import com.example.case_study_md3.utils.Config;
import com.example.case_study_md3.utils.ValidateUtils;


import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "CartServlet", value = "/cart")
public class CartServlet extends HttpServlet {
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

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add":
                addToCart(request, response, action, session);
                break;
            case "change":
                changeQuantity(request, response, action, session);
                break;
            case "check":
                showCheckout(request, response, session);
            default:
                showCart(request, response, session);
                break;
        }
    }

    private void showCheckout(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException, ServletException {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("/user?action=login");
            return;
        }
        Order unpaidOrder = orderService.findUserUnPaidOrder(user.getId());
        List<Product> allProducts = productService.findAll();
        List<OrderItem> orderItems = orderItemService.findAllByIdOrder(unpaidOrder.getId());
        unpaidOrder.setOrderItems(orderItems);

        request.setAttribute("order", unpaidOrder);
        request.setAttribute("products", allProducts);
        request.getRequestDispatcher(Config.HOMEPAGE + "checkout.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "remove":
                removeCartItem(request, response);
                break;
            case "check":
                doCheckout(request, response, session);
                break;
            case "view":
                int idOrder = Integer.parseInt(request.getParameter("idOrder"));
                Order order = orderService.findOrderById(idOrder);
                List<Product> products = productService.findAll();
                List<OrderItem> orderItems = orderItemService.findAllByIdOrder(order.getId());
                order.setOrderItems(orderItems);

                request.setAttribute("viewOrder", order);
                request.setAttribute("viewProducts", products);
                request.getRequestDispatcher("/WEB-INF/homepage/cart-view.jsp").forward(request, response);
                break;
            default:
                break;
        }
    }

    private void doCheckout(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException, ServletException {
        Map<String, String> errorsMap = new HashMap<>();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("/user?action=login");
            return;
        }
        Order unpaidOrder = orderService.findUserUnPaidOrder(user.getId());
        if (unpaidOrder == null) {
            response.sendRedirect("/product");
            return;
        }

        List<OrderItem> orderItems = orderItemService.findAllByIdOrder(unpaidOrder.getId());

        updateErrorsInput(request, errorsMap);

        if (errorsMap.isEmpty()) {
            updateProductLQuantity(orderItems);

            orderService.setOrderPaid(unpaidOrder.getId());
            String successMess = "Thanh toán thành công! Bạn đã bị scam tiền. Tiếp tục shopping để bị scam ^^";

            request.setAttribute("successMess", successMess);
        } else {
            List<Product> allProducts = productService.findAll();
            unpaidOrder.setOrderItems(orderItems);

            request.setAttribute("errorsMap", errorsMap);
            request.setAttribute("order", unpaidOrder);
            request.setAttribute("products", allProducts);
        }

        request.getRequestDispatcher(Config.HOMEPAGE + "checkout.jsp").forward(request, response);
    }

    private void removeCartItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idOrder = Integer.parseInt(request.getParameter("idOrder"));
        int idProduct = Integer.parseInt(request.getParameter("idProduct"));
        String doFrom = request.getParameter("doFrom");
        orderItemService.remove(idOrder, idProduct);
        Order order = orderService.findOrderById(idOrder);
        updateOrderSubTotal(order);

        if (doFrom == null){
            response.sendRedirect("/cart");
        }else {
            response.sendRedirect("/");
        }
    }

    private void updateProductLQuantity(List<OrderItem> orderItems) {
        for (OrderItem o : orderItems) {
            Product product = productService.findProduct(o.getIdProduct());
            int newQuantity = product.getLeftQuantity() - o.getQuantity();
            product.setLeftQuantity(newQuantity);
            productService.update(o.getIdProduct(), product);
        }
    }

    private static void updateErrorsInput(HttpServletRequest request, Map<String, String> errorsMap) {
        String fullName = request.getParameter("fullName");
        if (!ValidateUtils.isNameValid(fullName)) {
            errorsMap.put("nameInvalid", "Tên không hợp lệ. Tên phải từ 8-30 ký tự và bđ là chữ cái");
        }

        String address = request.getParameter("address");
        if (!ValidateUtils.isAddressValid(address)) {
            errorsMap.put("addressInvalid", "Địa chỉ không hợp lệ");
        }

        String phone = request.getParameter("phone");
        if (!ValidateUtils.isPhoneValid(phone)) {
            errorsMap.put("phoneInvalid", "Phone không hợp lệ");
        }

        String email = request.getParameter("email");
        if (!ValidateUtils.isEmailValid(email)) {
            errorsMap.put("emailInvalid", "Email không hợp lệ");
        }
    }

    private void changeQuantity(HttpServletRequest request, HttpServletResponse response, String action, HttpSession session) throws ServletException, IOException {
        addToCart(request, response, action, session);

    }

    private void showCart(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
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
        request.getRequestDispatcher("/WEB-INF/homepage/cart.jsp").forward(request, response);
    }

    private void addToCart(HttpServletRequest request, HttpServletResponse response, String action, HttpSession session) throws ServletException, IOException {
        String doFrom = request.getParameter("doFrom");
        int idProduct = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findProduct(idProduct);

        List<Product> allProducts = productService.findAll();
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        if (quantity < 0) {
            response.sendRedirect("/cart");
            return;
        }

        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("/user?action=login");
            return;
        }
        Order unpaidOrder = orderService.findUserUnPaidOrder(user.getId());
        String cartMess;

        if (quantity > product.getLeftQuantity() || quantity > 10) {
            cartMess = "Số lượng muốn mua không hợp lệ !";

            request.setAttribute("product", product);
            request.setAttribute("cartMess", cartMess);
//            response.sendRedirect("/product?action=view&id="+idProduct);
//            return;
            request.getRequestDispatcher(Config.ADMIN_TO_PRODUCT + "view-product.jsp").forward(request, response);
        } else {
            if (unpaidOrder == null) {
                Order newOrder = new Order();
                newOrder.setIdUser(user.getId());
                orderService.save(newOrder);
                unpaidOrder = orderService.findUserUnPaidOrder(user.getId());

                OrderItem orderItem = new OrderItem();
                setNullOrderItem(idProduct, product, quantity, unpaidOrder, orderItem);
                orderItemService.save(orderItem);

                float subTotal = getSubTotal(unpaidOrder);
                newOrder.setSubTotal(subTotal);
                orderService.update(unpaidOrder.getId(), newOrder);
            } else {
                OrderItem orderItem = orderItemService.findOrderItem(unpaidOrder.getId(), idProduct);
                if (orderItem != null) {
                    if (action.equals("add")) {
                        quantity = orderItem.getQuantity() + quantity;
                    }

                    if (quantity == 0) {
                        orderItemService.remove(unpaidOrder.getId(), idProduct);
                        Order order = orderService.findOrderById(unpaidOrder.getId());
                        updateOrderSubTotal(order);

                        response.sendRedirect("/cart");
                        return;
                    }

                    if (quantity > 10) {
                        cartMess = "Số lượng muốn mua không hợp lệ !";

                        request.setAttribute("product", product);
                        request.setAttribute("cartMess", cartMess);
                        request.getRequestDispatcher(Config.ADMIN_TO_PRODUCT + "view-product.jsp").forward(request, response);
                        return;
                    }

                    orderItem.setQuantity(quantity);
                    orderItem.setTotal(quantity * product.getPrice());
                    orderItemService.update(orderItem.getId(), orderItem);

                } else {
                    orderItem = new OrderItem();
                    setNullOrderItem(idProduct, product, quantity, unpaidOrder, orderItem);
                    orderItemService.save(orderItem);

                }
                updateOrderSubTotal(unpaidOrder);
            }

            if (doFrom.equals("home")){
                response.sendRedirect("/");
            }else if (doFrom.equals("viewProduct")){
                response.sendRedirect("/product?action=view&id="+idProduct);
            }else {
                response.sendRedirect("/cart");
            }
        }

    }

    private void updateOrderSubTotal(Order unpaidOrder) {
        float subTotal = getSubTotal(unpaidOrder);
        unpaidOrder.setSubTotal(subTotal);
        orderService.update(unpaidOrder.getId(), unpaidOrder);
    }

    private static void setNullOrderItem(int idProduct, Product product, int quantity, Order unpaidOrder, OrderItem orderItem) {
        orderItem.setIdOrder(unpaidOrder.getId());
        orderItem.setIdProduct(idProduct);
        orderItem.setQuantity(quantity);
        orderItem.setTotal(quantity * product.getPrice());
    }

    private float getSubTotal(Order unpaidOrder) {
        float subTotal = 0;
        List<OrderItem> orderItems = orderItemService.findAllByIdOrder(unpaidOrder.getId());
        for (OrderItem o : orderItems) {
            subTotal += o.getTotal();
        }
        return subTotal;
    }

    /**
     * private void addProductToOrder(int idProduct, int quantity, Order order) {
     * OrderItem orderItem = new OrderItem(idProduct, quantity);
     * order.getOrderItems().add(orderItem);
     * }
     * <p>
     * private void updateProductInOrder(int id, int quantity, Order order) {
     * for (int i = 0; i < order.getOrderItems().size(); i++) {
     * if (order.getOrderItems().get(i).getIdProduct() == id) {
     * OrderItem temp = order.getOrderItems().get(i);
     * temp.setQuantity(quantity);
     * break;
     * }
     * }
     * }
     * <p>
     * <p>
     * private boolean checkIdProductExistOrder(int idProduct, Order order) {
     * if (order.getOrderItems() == null) {
     * return false;
     * }
     * for (int i = 0; i < order.getOrderItems().size(); i++) {
     * if (order.getOrderItems().get(i).getIdProduct() == idProduct) {
     * return true;
     * }
     * }
     * return false;
     * }
     **/

    public void destroy() {
    }

    public static void main(String[] args) {

    }
}