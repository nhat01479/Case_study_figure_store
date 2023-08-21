package com.example.case_study_md3.controller.customer;

import com.example.case_study_md3.model.*;
import com.example.case_study_md3.service.CategoryService;
import com.example.case_study_md3.service.OrderItemService;
import com.example.case_study_md3.service.OrderService;
import com.example.case_study_md3.service.ProductService;
import com.example.case_study_md3.utils.Config;

import java.io.*;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

//@WebServlet(name = "ProductServlet", value = {"/product" , "/"})
public class ProductServlet extends HttpServlet {
    private ProductService productService = new ProductService();
    private CategoryService categoryService = new CategoryService();
    private OrderService orderService = new OrderService();
    private OrderItemService orderItemService = new OrderItemService();

    public void init() {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");


        ProductPageable pageable = new ProductPageable();
        inputProductPageable(req, pageable);

        EScale[] scales = EScale.values();
        Map<Integer, Category> categoryMap = categoryService.getCategoryMap();
        List<Product> allProducts = productService.findAll();

//        /product?page=&limit=&kw=&sortField=&order=&idCategory=&totalPage=&scale=
//        ${requestScope.pageable.getPage()},${requestScope.pageable.getLimit()},'${requestScope.pageable.getKw()}','${requestScope.pageable.getSortField()}','${requestScope.pageable.getOrder()}',${requestScope.pageable.getIdCategory()},'${requestScope.pageable.getScale()}'
//        pageable.getPage();pageable.getLimit();pageable.getKw();pageable.getSortField();pageable.getOrder();pageable.getIdCategory();pageable.getTotalPage();pageable.getScale();

        User user = (User) req.getSession().getAttribute("user");
        Order unpaidOrder = new Order();
        List<OrderItem> orderItems = null;
        if (user != null){
            unpaidOrder = orderService.findUserUnPaidOrder(user.getId());
            if (unpaidOrder != null){
                orderItems = orderItemService.findAllByIdOrder(unpaidOrder.getId());
                unpaidOrder.setOrderItems(orderItems);
            }
        }

        String action = req.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "view":
                showView(req, resp, scales, categoryMap, allProducts, unpaidOrder);
                break;
            default:
                showListProduct(req, resp, scales, categoryMap, allProducts, unpaidOrder);
                break;
        }
    }

    private void showListProduct(HttpServletRequest req, HttpServletResponse resp, EScale[] scales, Map<Integer, Category> categoryMap, List<Product> allProducts, Order unpaidOrder) throws ServletException, IOException {
        List<Product> specials = productService.findSpecial();
        ProductPageable pageable = new ProductPageable();
        inputProductPageable(req, pageable);
        System.out.println("........................." + pageable.getScale());
        List<Product> products = productService.findAllAdvance(pageable);

        //allProducts là tất cả product, kích thước không bị ảnh hưởng bởi pageable. Dùng cho head, hiện mini cart
        req.setAttribute("allProducts", allProducts);
        req.setAttribute("order", unpaidOrder);
        req.setAttribute("pageable", pageable);
        req.setAttribute("scales", scales);
        req.setAttribute("specials",specials);
        req.setAttribute("products",products);
        req.setAttribute("categoryMap", categoryMap);
        req.getRequestDispatcher("/WEB-INF/homepage/home.jsp").forward(req, resp);
    }

    private void showView(HttpServletRequest req, HttpServletResponse resp, EScale[] scales, Map<Integer, Category> categoryMap, List<Product> allProducts, Order unpaidOrder) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productService.findProduct(id);
        String adMess = req.getParameter("mess");

        req.setAttribute("adMess",adMess);
        req.setAttribute("allProducts", allProducts);
        req.setAttribute("order", unpaidOrder);
        req.setAttribute("scales", scales);
        req.setAttribute("product",product);
        req.setAttribute("categoryMap", categoryMap);
        req.getRequestDispatcher("/WEB-INF/admin/product/view-product.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        Map<Integer, Category> categoryMap = categoryService.getCategoryMap();
        List<Product> products = productService.findAll();
        String action = req.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){

            default:
                req.setAttribute("products",products);
                req.setAttribute("categoryMap",categoryMap);
                req.getRequestDispatcher("/WEB-INF/homepage/home.jsp").forward(req,resp);
                break;
        }
    }
    public void inputProductPageable(HttpServletRequest request, ProductPageable pageable) {
        if (request.getParameter("kw") != null) {
            String kw = request.getParameter("kw");
            pageable.setKw(kw);
        }
        if (request.getParameter("page") != null) {
            int page = Integer.parseInt(request.getParameter("page"));
            pageable.setPage(page);
        }
        if (request.getParameter("limit") != null) {
            int limit = Integer.parseInt(request.getParameter("limit"));
            pageable.setLimit(limit);
        }
        if (request.getParameter("sortField") != null) {
            String sortField = request.getParameter("sortField");
            pageable.setSortField(sortField);
        }
        if (request.getParameter("order") != null) {
            String order = request.getParameter("order");
            pageable.setOrder(order);
        }
        if (request.getParameter("idCategory") != null) {
            int idCategory = Integer.parseInt(request.getParameter("idCategory"));
            pageable.setIdCategory(idCategory);
        }
        if (request.getParameter("scale") != null) {
            String scale = request.getParameter("scale");
            pageable.setScale(scale);
        }
    }

    public void destroy() {
    }

}