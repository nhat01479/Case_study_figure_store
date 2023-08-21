package com.example.case_study_md3.service;

import com.example.case_study_md3.model.Order;
import com.example.case_study_md3.utils.DBContext;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderService extends DBContext{
    private static final String SELECT_ALL_ORDERS = "SELECT * FROM orders";
    private static final String SELECT_ALL_PAID_ORDERS = "SELECT * FROM orders  where isPaid = true;";
    private static final String SELECT_ALL_PAID_ORDERS_USER = "SELECT * FROM orders  where isPaid = true and idUser = ?;";
    private static final String SELECT_ALL_UNPAID_ORDERS = "SELECT * FROM orders  where isPaid = false;";
    private static final String SELECT_USER_UNPAID_ORDER = "SELECT * FROM orders  where isPaid = false and idUser = ?;";
    private static final String SELECT_USER_ORDERS = "SELECT * FROM orders where idUser = ?;";
    private static final String CREATE_ORDER = "INSERT INTO `orders` (`createAt`, `idUser`, `isPaid`, `subTotal`, `discount`) VALUES (?, ?, ?, ?, ?);";
    private static final String SELECT_ORDER_BY_ID = "SELECT * FROM orders  where id = ?;";
    private static final String UPDATE_ORDER = "UPDATE `orders` SET `idUser` = ?, `isPaid` = ?, `subTotal` = ?, `discount` = ? WHERE (`id` = ?);";
    private static final String DELETE_ORDER = "DELETE FROM `orders` WHERE (`id` = ?);";
    private static final String ORDER_SET_PAID = "UPDATE `orders` SET `isPaid` = true WHERE (`id` = ?);";
    public List<Order> findAll(){
        List<Order> orders = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ORDERS)) {

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Order order = getOrderFromResultSet(rs);
                orders.add(order);
            }

        }catch (SQLException sqlException){
            printSQLException(sqlException);
        }
        return orders;
    }
    public void setOrderPaid(int id){
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ORDER_SET_PAID)) {

            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();

        }catch (SQLException sqlException){
            printSQLException(sqlException);
        }
    }
    public List<Order> findAllPaidOrders(){
        List<Order> orders = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(SELECT_ALL_PAID_ORDERS);

            System.out.println("findAllPaidOrder " + ps);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order order = getOrderFromResultSet(rs);
                orders.add(order);
            }
            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return orders;
    }
    public List<Order> findUserOrders(int idUser){
        List<Order> orders = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_ORDERS)) {

            preparedStatement.setInt(1,idUser);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Order order = getOrderFromResultSet(rs);
                orders.add(order);
            }

        }catch (SQLException sqlException){
            printSQLException(sqlException);
        }
        return orders;
    }
    public List<Order> findAllPaidOrdersByUser(int id){
        List<Order> orders = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(SELECT_ALL_PAID_ORDERS_USER);

            ps.setInt(1,id);
            System.out.println("findAllPaidOrder " + ps);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order order = getOrderFromResultSet(rs);
                orders.add(order);
            }
            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return orders;
    }
    public List<Order> findAllUnPaidOrders(){
        List<Order> orders = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(SELECT_ALL_UNPAID_ORDERS);

            System.out.println("findAllPaidOrder " + ps);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order order = getOrderFromResultSet(rs);
                orders.add(order);
            }
            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return orders;
    }
    public Order findUserUnPaidOrder(int id){
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(SELECT_USER_UNPAID_ORDER);

            ps.setInt(1,id);
            System.out.println("findAllPaidOrder " + ps);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return getOrderFromResultSet(rs);
            }
            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return null;
    }
    public Order findOrderById(int id){
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(SELECT_ORDER_BY_ID);

            ps.setInt(1,id);
            System.out.println("Find Order by ID " + ps);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order order = getOrderFromResultSet(rs);
                return order;
            }
            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return null;
    }
    public Order getOrderFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        Date sqlCreateAt = rs.getDate("createAt");
        java.util.Date createAt = null;
        if (sqlCreateAt != null) {
            createAt = new java.util.Date(sqlCreateAt.getTime());
        }
        int idUser = rs.getInt("idUser");
        boolean isPaid = rs.getBoolean("isPaid");
        float subTotal = rs.getFloat("subTotal");
        float discount = rs.getFloat("discount");

        return new Order(id, createAt, idUser, isPaid, subTotal, discount);
    }


    public void save (Order order){
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(CREATE_ORDER);
            java.util.Date createAt = new java.util.Date();
            //`createAt`, `idUser`, `isPaid`, `subTotal`, `discount`
            ps.setDate(1, new java.sql.Date(createAt.getTime()));
            ps.setInt(2, order.getIdUser());
            ps.setBoolean(3, order.isPaid());
            ps.setFloat(4, order.getSubTotal());
            ps.setFloat(5, order.getDiscount());

            System.out.println("function create " + ps);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    public void update (int id, Order order){
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(UPDATE_ORDER);
            ps.setInt(1, order.getIdUser());
            ps.setBoolean(2, order.isPaid());
            ps.setFloat(3, order.getSubTotal());
            ps.setFloat(4, order.getDiscount());
            ps.setInt(5, id);

            System.out.println("Function update"+ ps);
            ps.executeUpdate();
            connection.close();

        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    public void remove (int id){
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(DELETE_ORDER);
            ps.setInt(1, id);
            System.out.println("Function remove order" + ps);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
}
