package com.example.case_study_md3.service;

import com.example.case_study_md3.model.EScale;
import com.example.case_study_md3.model.EStudio;
import com.example.case_study_md3.model.Product;
import com.example.case_study_md3.model.ProductPageable;
import com.example.case_study_md3.utils.DBContext;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductService extends DBContext {
    private final String SELECT_2_SPECIAL = "select * from products\n" +
            "order by leftQuantity desc\n" +
            "limit 0, 2";
    private final String SELECT_ALL = "SELECT * FROM products where (`deleteAt` is null)";
    private final String INSERT_PRODUCT = "INSERT INTO `products` (`name`, `price`, `leftQuantity`, `createAt`," +
            " `scale`, `idCategory`, `description`, `imgLink`, `studio`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String SELECT_BY_ID = "SELECT * FROM products where id = ?";
    private final String UPDATE_PRODUCT = "UPDATE `products` SET `name` = ?, `price` = ?, `leftQuantity` = ?, `scale` = ?, `idCategory` = ?, `description` = ?, `imgLink` = ?, `studio` = ? where id = ?";
    private final String DELETE_PRODUCT = "UPDATE `products` SET `deleteAt` = ? where id = ? and `deleteAt` is null";
    private final String SELECT_ALL_ADVANCE = "select p.*,c.`name` as categoryName from products p \n" +
            "join categories c on p.idCategory = c.id \n" +
            "where (p.`name` like ? or p.price like ? or p.leftQuantity like ? or p.`description` like ? or p.studio like ?)  and p.`deleteAt` is null\n" +
            "order by %s %s \n" +
            "limit ?, ?;";
    private final String SELECT_ALL_ADVANCE_TOTAL = "select count(*) as total from products p \n" +
            "join categories c on p.idCategory = c.id \n" +
            "where (p.`name` like ? or p.price like ? or p.leftQuantity like ? or p.`description` like ? or p.studio like ?) and p.`deleteAt` is null\n";
    private final String SELECT_ALL_ADVANCE_FILTERS = "select p.*,c.`name` as categoryName from products p \n" +
            "join categories c on p.idCategory = c.id \n" +
            "where (p.`name` like ? or p.price like ? or p.leftQuantity like ? or p.`description` like ? or p.studio like ?) and p.idCategory = ? and p.scale like ? and p.`deleteAt` is null\n" +
            "order by %s %s \n" +
            "limit ?, ?;";
    private final String SELECT_ALL_ADVANCE_FILTERS_TOTAL = "select count(*) as total from products p \n" +
            "join categories c on p.idCategory = c.id \n" +
            "where (p.`name` like ? or p.price like ? or p.leftQuantity like ? or p.`description` like ? or p.studio like ?) and p.idCategory = ? and p.scale like ? and p.`deleteAt` is null\n";
    private final String SELECT_ALL_ADVANCE_CFILTER = "select p.*,c.`name` as categoryName from products p \n" +
            "join categories c on p.idCategory = c.id \n" +
            "where (p.`name` like ? or p.price like ? or p.leftQuantity like ? or p.`description` like ? or p.studio like ?) and p.idCategory = ? and p.`deleteAt` is null\n" +
            "order by %s %s \n" +
            "limit ?, ?;";
    private final String SELECT_ALL_ADVANCE_CFILTER_TOTAL = "select count(*) as total from products p \n" +
            "join categories c on p.idCategory = c.id\n" +
            "where (p.`name` like ? or p.price like ? or p.leftQuantity like ? or p.`description` like ? or p.studio like ?) and p.idCategory = ? and p.`deleteAt` is null\n";
    private final String SELECT_ALL_ADVANCE_SFILTER = "select p.*,c.`name` as categoryName from products p \n" +
            "join categories c on p.idCategory = c.id\n" +
            "where (p.`name` like ? or p.price like ? or p.leftQuantity like ? or p.`description` like ? or p.studio like ?) and p.scale like ? and p.`deleteAt` is null\n" +
            "order by %s %s \n" +
            "limit ?, ?;";
    private final String SELECT_ALL_ADVANCE_SFILTER_TOTAL = "select count(*) as total from products p \n" +
            "join categories c on p.idCategory = c.id \n" +
            "where (p.`name` like ? or p.price like ? or p.leftQuantity like ? or p.`description` like ? or p.studio like ?) and p.scale like ? and p.`deleteAt` is null\n";
    private final String SELECT_ALL_PRODUCT_ADVANCE = "select p.*,c.`name` as categoryName from products p \n" +
            "join categories c on (p.idCategory = c.id) \n" +
            "where (p.`name` like ? or p.price like ? or  p.leftQuantity like ? or p.`description` like ? or p.studio like ?) and (p.scale like ? and p.`deleteAt` is null) \n" +
            "order by %s %s \n" +
            "limit ?, ?;";
    private final String SELECT_ALL_PRODUCT_ADVANCE_COUNT_TOTAL = "select count(*) as total from products p \n" +
            "join categories c on (p.idCategory = c.id) \n" +
            "where (p.`name` like ? or p.price like ? or p.leftQuantity like ? or p.`description` like ? or p.studio like ?) and (p.scale like ? and p.`deleteAt` is null) \n";
    private final String SELECT_ALL_PRODUCT_FILTER_BY_CATEGORY = "select p.*,c.`name` as categoryName from products p \n" +
            "join categories c on p.idCategory = c.id \n" +
            "where (p.`name` like ? or p.price like ?  or p.`description` like ? or p.studio like ?) and (p.idCategory = ?  and p.`deleteAt` is null) \n" +
            "order by %s %s \n" +
            "limit ?, ?;";
    //and p.scale like ?
    private final String SELECT_ALL_PRODUCT_FILTER_BY_CATEGORY_COUNT_TOTAL = "select p.*,c.`name` as categoryName from products p \n" +
            "join categories c on p.idCategory = c.id \n" +
            "where (p.`name` like ? or p.price like ?  or p.`description` like ? or p.studio like ?) and (p.idCategory = ? and p.scale like ? and p.`deleteAt` is null) \n";

    public List<Product> findAll(){
        List<Product> products = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {

            System.out.println("Function find all product");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                Product product = getProductFromRs(rs);
                products.add(product);
            }

        }catch (SQLException sqlException){
            printSQLException(sqlException);
        }
        return products;
    }
    public List<Product> findSpecial(){
        List<Product> products = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_2_SPECIAL)) {

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Product product = getProductFromRs(rs);
                products.add(product);
            }

        }catch (SQLException sqlException){
            printSQLException(sqlException);
        }
        return products;
    }
    public List<Product> findAllAdvance(ProductPageable pageable){
        List<Product> products = new ArrayList<>();
        //where (p.`name` like ? or p.price like ? or p.leftQuantity like ? or p.`description` like ? or p.studio like ?) and `deleteAt` is null\n" +
        //            "order by %s %s\n" +
        //            "limit ?, ?;";
        String sql = "";
        try {
            Connection connection = getConnection();
            if (pageable.getIdCategory() == -1) {
                if (pageable.getScale().equals("all")){
//                    getAllProduct(connection, products, pageable);
                    PreparedStatement ps = connection.prepareStatement(String.format(SELECT_ALL_ADVANCE, pageable.getSortField(), pageable.getOrder()));

                    ps.setString(1, "%" + pageable.getKw() + "%");
                    ps.setString(2, "%" + pageable.getKw() + "%");
                    ps.setString(3, "%" + pageable.getKw() + "%");
                    ps.setString(4, "%" + pageable.getKw() + "%");
                    ps.setString(5, "%" + pageable.getKw() + "%");
                    ps.setInt(6,(pageable.getPage()-1) * pageable.getLimit());
                    ps.setInt(7,pageable.getLimit());

                    ResultSet rs = ps.executeQuery();
                    while (rs.next()){
                        Product product = getProductFromRs(rs);
                        products.add(product);
                    }

                    ps = connection.prepareStatement(SELECT_ALL_ADVANCE_TOTAL);

                    ps.setString(1, "%" + pageable.getKw() + "%");
                    ps.setString(2, "%" + pageable.getKw() + "%");
                    ps.setString(3, "%" + pageable.getKw() + "%");
                    ps.setString(4, "%" + pageable.getKw() + "%");
                    ps.setString(5, "%" + pageable.getKw() + "%");

                    rs = ps.executeQuery();
                    setTotalpage(pageable, rs);
                }else {
                    PreparedStatement ps = connection.prepareStatement(String.format(SELECT_ALL_ADVANCE_SFILTER, pageable.getSortField(), pageable.getOrder()));

                    ps.setString(1, "%" + pageable.getKw() + "%");
                    ps.setString(2, "%" + pageable.getKw() + "%");
                    ps.setString(3, "%" + pageable.getKw() + "%");
                    ps.setString(4, "%" + pageable.getKw() + "%");
                    ps.setString(5, "%" + pageable.getKw() + "%");
                    ps.setString(6,pageable.getScale());
                    ps.setInt(7,(pageable.getPage()-1) * pageable.getLimit());
                    ps.setInt(8,pageable.getLimit());

                    ResultSet rs = ps.executeQuery();
                    while (rs.next()){
                        Product product = getProductFromRs(rs);
                        products.add(product);
                    }

                    ps = connection.prepareStatement(SELECT_ALL_ADVANCE_SFILTER_TOTAL);

                    ps.setString(1, "%" + pageable.getKw() + "%");
                    ps.setString(2, "%" + pageable.getKw() + "%");
                    ps.setString(3, "%" + pageable.getKw() + "%");
                    ps.setString(4, "%" + pageable.getKw() + "%");
                    ps.setString(5, "%" + pageable.getKw() + "%");
                    ps.setString(6,pageable.getScale());

                    rs = ps.executeQuery();
                    setTotalpage(pageable,rs);

                }
            }  else {
//                getAllProductFilter(connection, products, pageable);
                if (pageable.getScale().equals("all")){
                    PreparedStatement ps = connection.prepareStatement(String.format(SELECT_ALL_ADVANCE_CFILTER, pageable.getSortField(), pageable.getOrder()));

                    ps.setString(1, "%" + pageable.getKw() + "%");
                    ps.setString(2, "%" + pageable.getKw() + "%");
                    ps.setString(3, "%" + pageable.getKw() + "%");
                    ps.setString(4, "%" + pageable.getKw() + "%");
                    ps.setString(5, "%" + pageable.getKw() + "%");
                    ps.setInt(6,pageable.getIdCategory());
                    ps.setInt(7,(pageable.getPage()-1) * pageable.getLimit());
                    ps.setInt(8,pageable.getLimit());

                    ResultSet rs = ps.executeQuery();
                    while (rs.next()){
                        Product product = getProductFromRs(rs);
                        products.add(product);
                    }

                    ps = connection.prepareStatement(SELECT_ALL_ADVANCE_CFILTER_TOTAL);

                    ps.setString(1, "%" + pageable.getKw() + "%");
                    ps.setString(2, "%" + pageable.getKw() + "%");
                    ps.setString(3, "%" + pageable.getKw() + "%");
                    ps.setString(4, "%" + pageable.getKw() + "%");
                    ps.setString(5, "%" + pageable.getKw() + "%");
                    ps.setInt(6,pageable.getIdCategory());

                    rs = ps.executeQuery();
                    setTotalpage(pageable,rs);
                }else {
                    PreparedStatement ps = connection.prepareStatement(String.format(SELECT_ALL_ADVANCE_FILTERS, pageable.getSortField(), pageable.getOrder()));

                    ps.setString(1, "%" + pageable.getKw() + "%");
                    ps.setString(2, "%" + pageable.getKw() + "%");
                    ps.setString(3, "%" + pageable.getKw() + "%");
                    ps.setString(4, "%" + pageable.getKw() + "%");
                    ps.setString(5, "%" + pageable.getKw() + "%");
                    ps.setInt(6,pageable.getIdCategory());
                    ps.setString(7,pageable.getScale());
                    ps.setInt(8,(pageable.getPage()-1) * pageable.getLimit());
                    ps.setInt(9,pageable.getLimit());

                    ResultSet rs = ps.executeQuery();
                    while (rs.next()){
                        Product product = getProductFromRs(rs);
                        products.add(product);
                    }

                    ps = connection.prepareStatement(SELECT_ALL_ADVANCE_FILTERS_TOTAL);

                    ps.setString(1, "%" + pageable.getKw() + "%");
                    ps.setString(2, "%" + pageable.getKw() + "%");
                    ps.setString(3, "%" + pageable.getKw() + "%");
                    ps.setString(4, "%" + pageable.getKw() + "%");
                    ps.setString(5, "%" + pageable.getKw() + "%");
                    ps.setInt(6,pageable.getIdCategory());
                    ps.setString(7,pageable.getScale());

                    rs = ps.executeQuery();
                    setTotalpage(pageable,rs);
                }
            }
            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return products;
    }

    private static void setTotalpage(ProductPageable pageable, ResultSet rs) throws SQLException {
        while (rs.next()){
            int total = rs.getInt("total");
            // total * 1.0 để thành số thực vì'/' sẽ ra số nguyên
            pageable.setTotalPage((int)Math.ceil(total * 1.0 / pageable.getLimit()));
        }
    }

    private void getAllProductFilter(Connection connection, List<Product> products, ProductPageable pageable) throws SQLException {
        String sql = "";
        sql = String.format(SELECT_ALL_PRODUCT_FILTER_BY_CATEGORY, pageable.getSortField(), pageable.getOrder());
        // "where (p.`name` like ? or p.price like ?  or p.`description` like ? or p.studio like ?) and (p.idCategory = ? and p.scale like ? and p.`deleteAt` is null) \n" +
        //            "order by %s %s \n" +
        //            "limit ?, ?;";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, "%" + pageable.getKw() + "%");
        ps.setString(2, "%" + pageable.getKw() + "%");
        ps.setString(3, "%" + pageable.getKw() + "%");
        ps.setString(4, "%" + pageable.getKw() + "%");
        ps.setInt(5, pageable.getIdCategory());
//        ps.setString(6, "%" + pageable.getScale() + "%");
        ps.setInt(6, (pageable.getPage() - 1) * pageable.getLimit());
        ps.setInt(7, pageable.getLimit());
        System.out.println("Funtion find advance " + ps);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Product product = getProductFromRs(rs);
            products.add(product);
        }
//            "where (p.`name` like ? or p.price like ?  or p.`description` like ? or p.studio like ?) and (p.idCategory = ? and p.scale like ? and p.`deleteAt` is null) \n";
        //Tính tổng số trang
        ps = connection.prepareStatement(SELECT_ALL_PRODUCT_FILTER_BY_CATEGORY_COUNT_TOTAL);
        ps.setString(1, "%" + pageable.getKw() + "%");
        ps.setString(2, "%" + pageable.getKw() + "%");
        ps.setString(3, "%" + pageable.getKw() + "%");
        ps.setString(4, "%" + pageable.getKw() + "%");
        ps.setInt(5, pageable.getIdCategory());
        ps.setString(6, "%" + pageable.getScale() + "%");


        rs = ps.executeQuery();
        setTotalpage(pageable, rs);
    }
    private void getAllProduct(Connection connection, List<Product> products, ProductPageable pageable) throws SQLException {
        String sql = "";
        sql = String.format(SELECT_ALL_PRODUCT_ADVANCE, pageable.getSortField(), pageable.getOrder());
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, "%" + pageable.getKw() + "%");
        ps.setString(2, "%" + pageable.getKw() + "%");
        ps.setString(3, "%" + pageable.getKw() + "%");
        ps.setString(4, "%" + pageable.getKw() + "%");
        ps.setString(5, "%" + pageable.getKw() + "%");
        if (pageable.getScale().equals("all")) {
            ps.setString(6, "%%"); //scale
        } else {
            ps.setString(6, "%" + pageable.getScale() + "%"); //scale

        }
        ps.setInt(7, (pageable.getPage() - 1) * pageable.getLimit());
        ps.setInt(8, pageable.getLimit());
        System.out.println("Funtion find advance " + ps);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Product product = getProductFromRs(rs);
            products.add(product);
        }

        //Tính tổng số trang
        ps = connection.prepareStatement(SELECT_ALL_PRODUCT_ADVANCE_COUNT_TOTAL);
        ps.setString(1, "%" + pageable.getKw() + "%");
        ps.setString(2, "%" + pageable.getKw() + "%");
        ps.setString(3, "%" + pageable.getKw() + "%");
        ps.setString(4, "%" + pageable.getKw() + "%");
        ps.setString(5, "%" + pageable.getKw() + "%");
        ps.setString(6, "%" + pageable.getKw() + "%"); //scale


        rs = ps.executeQuery();
        setTotalpage(pageable, rs);
    }

    private static Product getProductFromRs(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setName(rs.getString("name"));
        product.setPrice(rs.getFloat("price"));
        product.setLeftQuantity(rs.getInt("leftQuantity"));
        product.setCreateAt(rs.getDate("createAt"));
        product.seteScale(EScale.getScaleByScale(rs.getString("scale")));
        product.setIdCategory(rs.getInt("idCategory"));
        product.setDescription(rs.getString("description"));
        product.setImgLink(rs.getString("imgLink"));
        product.setDeleteAt(rs.getDate("deleteAt"));
        product.seteStudio(EStudio.getStudio(rs.getString("studio")));
        return product;
    }

    public Product findProduct(int id){
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {

            preparedStatement.setInt(1,id);

            System.out.println("Function find product by id");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                return getProductFromRs(rs);
            }

        }catch (SQLException sqlException){
            printSQLException(sqlException);
        }
        return null;
    }
    public void save (Product product){
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT)) {

            preparedStatement.setString(1, product.getName());
            preparedStatement.setFloat(2,product.getPrice());
            preparedStatement.setInt(3,product.getLeftQuantity());

            java.util.Date createAt = new java.util.Date();
            preparedStatement.setDate(4,new Date(createAt.getTime()));

            preparedStatement.setString(5,product.geteScale().getScale());
            preparedStatement.setInt(6,product.getIdCategory());
            preparedStatement.setString(7,product.getDescription());
            preparedStatement.setString(8,product.getImgLink());
            preparedStatement.setString(9,product.geteStudio().getName());

            System.out.println("Function save product");
            preparedStatement.executeUpdate();
        }catch (SQLException sqlException){
            printSQLException(sqlException);
        }
    }
    public void update (int id, Product product){
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT)) {

            preparedStatement.setString(1, product.getName());
            preparedStatement.setFloat(2,product.getPrice());
            preparedStatement.setInt(3,product.getLeftQuantity());
            preparedStatement.setString(4,product.geteScale().getScale());
            preparedStatement.setInt(5,product.getIdCategory());
            preparedStatement.setString(6,product.getDescription());
            preparedStatement.setString(7,product.getImgLink());
            preparedStatement.setString(8,product.geteStudio().getName());
            preparedStatement.setInt(9,id);

            System.out.println("Function Update product");
            preparedStatement.executeUpdate();

        }catch (SQLException sqlException){
            printSQLException(sqlException);
        }
    }
    public void remove (int id){
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT)) {

            java.util.Date deleteAt = new java.util.Date();
            preparedStatement.setDate(1,new Date(deleteAt.getTime()));
            preparedStatement.setInt(2,id);

            System.out.println("Function remove product");
            preparedStatement.executeUpdate();

        }catch (SQLException sqlException){
            printSQLException(sqlException);
        }
    }



}
