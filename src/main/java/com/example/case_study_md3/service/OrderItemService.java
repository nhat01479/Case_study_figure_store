package com.example.case_study_md3.service;

import com.example.case_study_md3.model.OrderItem;
import com.example.case_study_md3.utils.DBContext;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemService  extends DBContext{

    private static final String SELECT_ORDERITEM_BY_IDORDER = "SELECT * FROM orderitems where idOrder = ?;";
    private static final String SELECT_ORDERITEM_BY_IDORDER_IDPRODUCT = "SELECT * FROM orderitems where idOrder = ? and idProduct = ?;";
    private static final String SAVE_ORDERITEM = "INSERT INTO `figure_store`.`orderitems` (`idOrder`, `idProduct`, `quantity`, `total`) VALUES (?, ?, ?, ?);\n";
    private static final String UPDATE_ORDERITEM = "UPDATE `figure_store`.`orderitems` SET  `idProduct` = ?, `quantity` = ?, `total` = ? WHERE (`id` = ?);\n";
    private static final String DELETE_ORDERITEM = "DELETE FROM orderitems WHERE `idOrder` = ? and `idProduct` = ?;";

    public List<OrderItem> findAllByIdOrder(int id){
        List<OrderItem> orderItems = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(SELECT_ORDERITEM_BY_IDORDER);

            ps.setInt(1,id);
            System.out.println("findAllPaidOrder " + ps);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrderItem orderItem = getOrderItemFromResultSet(rs);
                orderItems.add(orderItem);
            }
//            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return orderItems;

    }
    public OrderItem findOrderItem(int idOrder, int idProduct){
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDERITEM_BY_IDORDER_IDPRODUCT)) {

            preparedStatement.setInt(1,idOrder);
            preparedStatement.setInt(2,idProduct);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                return getOrderItemFromResultSet(rs);
            }

        }catch (SQLException sqlException){
            printSQLException(sqlException);
        }
        return null;
    }
    public OrderItem getOrderItemFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int idOrder = rs.getInt("idOrder");
        int idProduct = rs.getInt("idProduct");
        int quantity = rs.getInt("quantity");
        float total = rs.getFloat("total");

        OrderItem orderItem = new OrderItem(id, idOrder, idProduct, quantity, total);
        return orderItem;
    }
    public void save (OrderItem orderItem)  {
        Connection connection = getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(SAVE_ORDERITEM);
            ps.setInt(1, orderItem.getIdOrder());
            ps.setInt(2, orderItem.getIdProduct());
            ps.setInt(3, orderItem.getQuantity());
            ps.setFloat(4, orderItem.getTotal());

            ps.executeUpdate();
            System.out.println("save orderItem " + ps);

            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    public void update (int id, OrderItem orderItem){
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(UPDATE_ORDERITEM);
           //`idProduct` = ?, `quantity` = ?, `total` = ? WHERE (`id` = ?)
            ps.setInt(1, orderItem.getIdProduct());
            ps.setInt(2, orderItem.getQuantity());
            ps.setFloat(3, orderItem.getTotal());
            ps.setInt(4, id);

            System.out.println("Update orderItem " + ps);
            ps.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    public void remove (int idOrder, int idProduct){
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(DELETE_ORDERITEM);

            ps.setInt(1, idOrder);
            ps.setInt(2,idProduct);
            System.out.println("Remove orderItem" + ps);
            ps.executeUpdate();
            connection.close();

        } catch (SQLException e) {
            printSQLException(e);
        }
    }
}
