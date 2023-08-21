package com.example.case_study_md3.controller.admin;

import com.example.case_study_md3.model.*;
import com.example.case_study_md3.service.CategoryService;
import com.example.case_study_md3.service.ProductService;
import com.example.case_study_md3.utils.ValidateUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet (name="AdminProductServlet", urlPatterns = "/product-manager")
public class AdminProductServlet extends HttpServlet {
    private ProductService productService;
    private CategoryService categoryService;
    private static final String LIST_PRODUCT_ADMIN = "/WEB-INF/admin/product/";


    @Override
    public void init() throws ServletException {
        productService = new ProductService();
        categoryService = new CategoryService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null || !user.geteRole().name().equals("ADMIN")){
            resp.sendRedirect("/404");
            return;
        }
        String action = req.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action) {
            case "create":
                showCreateForm(req, resp);
                break;
            case "edit":
                showEditForm(req, resp);
                break;
            default:
                showListProduct(req, resp);
        }
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productService.findProduct(id);
        //set attribute category, scale, studio
        req.setAttribute("product", product);
        actionSetAttribute(req);
        req.getRequestDispatcher(LIST_PRODUCT_ADMIN + "edit-product.jsp").forward(req, resp);
    }

    private void showCreateForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EScale[] scales = EScale.values();
        EStudio[] studios = EStudio.values();
        Map<Integer, Category> categoryMap = categoryService.getCategoryMap();

        req.setAttribute("scales",scales);
        req.setAttribute("studios",studios);
        req.setAttribute("categoryMap",categoryMap);
        req.getRequestDispatcher(LIST_PRODUCT_ADMIN + "create-product.jsp").forward(req, resp);
    }

    private void showListProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        ProductPageable pageable = new ProductPageable();
        inputProductPageable(req, pageable);

        actionSetAttribute(req);

        List<Product> allProducts = productService.findAll();
//        List<Product> products = productService.findAllAdvance(pageable);


//        List<Product> products = productService.findAll();
        req.setAttribute("pageable", pageable);
        req.setAttribute("products", allProducts);
        req.getRequestDispatcher(LIST_PRODUCT_ADMIN + "list-product.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action) {
            case "create":
                createProduct(req, resp);
                break;
            case "edit":
                editProduct(req, resp);
                break;
            case "delete":
                deleteProduct(req, resp);
                break;
            default:
                showListProduct(req, resp);
        }
    }

    private void editProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        List<String> errors = new ArrayList<>();
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productService.findProduct(id);

        inputProduct(req, product, errors);

        if (errors.isEmpty()) {
            productService.update(id, product);
            resp.sendRedirect("/product-manager");
        } else {
            req.setAttribute("errors", errors);
            req.setAttribute("product", product);
            actionSetAttribute(req);
            req.getRequestDispatcher(LIST_PRODUCT_ADMIN + "edit-product.jsp").forward(req, resp);

        }

    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int idDelete = Integer.parseInt(req.getParameter("idDelete"));
        productService.remove(idDelete);

        List<Product> products = productService.findAll();

        resp.sendRedirect("/product-manager");
    }

    private void createProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> errors = new ArrayList<>();
        Product product = new Product();

        inputProduct(req, product, errors);
        if (errors.isEmpty()) {
            productService.save(product);
            req.setAttribute("message", "Thêm sản phẩm thành công");
        } else {
            req.setAttribute("errors", errors);
            req.setAttribute("product", product);
        }

        actionSetAttribute(req);

        req.getRequestDispatcher(LIST_PRODUCT_ADMIN + "create-product.jsp").forward(req, resp);
    }
    public void inputProduct(HttpServletRequest req, Product product, List<String> errors) {
        String name = req.getParameter("name");
        if (!ValidateUtils.isProductNameValid(name)) {
            errors.add("Tên không hợp lệ");
        }
        product.setName(name);

        try {
            String price = req.getParameter("price");
            if (!ValidateUtils.isPrice(price)) {
                errors.add("Giá từ 100 đến 9999 USD");
            }
            product.setPrice(Float.parseFloat(price));
        } catch (NumberFormatException e) {
            errors.add("Giá là số thực có 1 hoặc 2 số đằng sau dấu thập phân");
            product.setPrice(500);
        }

        try {
            String quantity = req.getParameter("quantity");
            if (!ValidateUtils.isQuantity(quantity)) {
                errors.add("Số lượng từ 1 - 99");
            }
            product.setLeftQuantity(Integer.parseInt(quantity));
        } catch (NumberFormatException e) {
            errors.add("Số lượng là số nguyên");
            product.setLeftQuantity(10);
        }


        try {
            String scaleName = req.getParameter("scale");
            EScale scale = EScale.getScaleByScale(scaleName);
            if (scale == null) {
                errors.add("Vui lòng chọn tỉ lệ");
                EScale sDefault = EScale.S6;
                product.seteScale(sDefault);
            } else {
                product.seteScale(scale);
            }
        } catch (Exception e) {
            System.out.println("loi" + e);
        }

        int idCategory = Integer.parseInt(req.getParameter("category"));
        Category category = categoryService.findCategory(idCategory);
        if (category == null) {
            errors.add("Không tìm thấy loại sản phẩm");
            product.setIdCategory(1);
        }else {
            product.setIdCategory(idCategory);
        }

        String desc = req.getParameter("description");
        if (!ValidateUtils.isDescriptionValid(desc)) {
            errors.add("Mô tả không hợp lệ");
        }
        product.setDescription(desc);

        String studio = req.getParameter("studio");
        EStudio eStudio = EStudio.getStudio(studio);
        if (eStudio == null) {
            errors.add("Vui lòng chọn Studio");
            EStudio studioDefault = EStudio.STU1;
            product.seteStudio(studioDefault);
        } else {
            product.seteStudio(eStudio);
        }

        String imgLink = req.getParameter("imgLink");
        if (!ValidateUtils.isLink(imgLink)) {
            errors.add("Link ảnh không hợp lệ");
        }
        product.setImgLink(imgLink);
    }
    public void actionSetAttribute(HttpServletRequest req){

        EScale[] scales = EScale.values();
        EStudio[] studios = EStudio.values();
        Map<Integer, Category> categoryMap = categoryService.getCategoryMap();
        req.setAttribute("scales", scales);
        req.setAttribute("studios", studios);
        req.setAttribute("categoryMap", categoryMap);
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
        if (request.getParameter("sortfield") != null) {
            String sortField = request.getParameter("sortfield");
            pageable.setSortField(sortField);
        }
        if (request.getParameter("order") != null) {
            String order = request.getParameter("order");
            pageable.setOrder(order);
        }
        if (request.getParameter("category") != null) {
            int idCategory = Integer.parseInt(request.getParameter("category"));
            pageable.setIdCategory(idCategory);
        }
        if (request.getParameter("scale") != null) {
            String scale = request.getParameter("scale");
            pageable.setScale(scale);
        }
    }

}
