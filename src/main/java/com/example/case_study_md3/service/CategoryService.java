package com.example.case_study_md3.service;

import com.example.case_study_md3.model.Category;
import com.example.case_study_md3.utils.DBContext;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class CategoryService extends DBContext {
    private final String SELECT_ALL = "SELECT * FROM categories where `deleteAt` is null";
    private final String SELECT_BY_ID = "SELECT * FROM categories where `id` = ? and `deleteAt` is null";
    private final String INSERT_CATEGORY = "INSERT INTO `categories` (`name`) VALUES (?)";
    private final String UPDATE_CATEGORY = "UPDATE `categories` SET `name` = ? WHERE `id` = ? and `deleteAt` is null";
    private final String DELETE_CATEGORY = "UPDATE `categories` SET `deleteAt` = ? WHERE (`id` = ?)";
    public List<Category> findAll(){
        List<Category> categories = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {

            System.out.println("Function find all category");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Category category = getCategoryFromRs(rs);

                categories.add(category);
            }

        }catch (SQLException sqlException){
            printSQLException(sqlException);
        }
        return categories;
    }

    private static Category getCategoryFromRs(ResultSet rs) throws SQLException {
        Category category = new Category();
        category.setId(rs.getInt("id"));
        category.setName(rs.getString("name"));
        category.setDeleteAt(rs.getDate("deleteAt"));
        return category;
    }
    public Map<Integer,Category> getCategoryMap(){
        Map<Integer,Category> categoryMap = new HashMap<>();
        for (Category category: findAll()){
            categoryMap.put(category.getId(),category);
        }
        return categoryMap;
    }

    public Category findCategory(int id){
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {

            preparedStatement.setInt(1,id);
            System.out.println("Function find category");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                return getCategoryFromRs(rs);
            }
        }catch (SQLException sqlException){
            printSQLException(sqlException);
        }
        return null;
    }
    public void save(Category category){
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CATEGORY)) {

            preparedStatement.setString(1, category.getName());
            System.out.println("Function insert category");
            preparedStatement.executeUpdate();

        }catch (SQLException sqlException){
            printSQLException(sqlException);
        }
    }
    public void update(int id, Category category){
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CATEGORY)) {

            preparedStatement.setString(1,category.getName());
            preparedStatement.setInt(2,id);

            System.out.println("Function update category");
            preparedStatement.executeUpdate();

        }catch (SQLException sqlException){
            printSQLException(sqlException);
        }
    }
    public void remove(int id){
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CATEGORY)) {

            java.util.Date deleteAt = new java.util.Date();
            preparedStatement.setDate(1,new java.sql.Date(deleteAt.getTime()));
            preparedStatement.setInt(2,id);

            System.out.println("Function delete category");
            preparedStatement.executeUpdate();

        }catch (SQLException sqlException){
            printSQLException(sqlException);
        }
    }
}
