package com.example.case_study_md3.service;
import com.example.case_study_md3.model.ERole;
import com.example.case_study_md3.model.User;
import com.example.case_study_md3.utils.DBContext;
import org.mindrot.jbcrypt.BCrypt;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserService extends DBContext {
    private static final String SELECT_ALL_USER = "SELECT * FROM users WHERE (deleteAt is null)";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String INSERT_USER =   "INSERT INTO `users` (`fullName`, `dob`, `address`, `phone`, `email`, `password`, `createAt`, `role`, `deleteAt`) " +
                                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String UPDATE_USER =   "UPDATE `users` " +
                                                "SET `fullName` = ?, `dob` = ?, `address` = ?, `phone` = ?, `email` = ?, `password` = ?, `createAt` = ?, `role` = ?, `deleteAt` = ? " +
                                                "WHERE (`id` = ?)";
    private static final String DELETE_USER = "UPDATE `users` SET `deleteAt` = ? WHERE (`id` = ?)";

    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    public static boolean isBCryptHash(String input) {
        try {
            BCrypt.checkpw("", input); // Check if it throws an exception
            return true; // No exception, input is a valid BCrypt hash
        } catch (IllegalArgumentException e) {
            return false; // Exception thrown, input is not a BCrypt hash
        }
    }
    public User checkUserHash(String email) {
        try {
            String query = "SELECT * FROM users WHERE email = ?";
            connection = getConnection();
            ps = connection.prepareStatement(query);
            System.out.println("Function Check User " + ps);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String fullName = rs.getString("fullName");
                Date dob = rs.getDate("dob");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                String emailD = rs.getString("email");
                String password = rs.getString("password");
                String pass;
                if (!isBCryptHash(password)) {
                    pass = BCrypt.hashpw(password, BCrypt.gensalt());
                } else {
                    pass = password;
                }

                Date createAt = rs.getDate("createAt");
                String role = rs.getString("role");
                Date deleteAt = rs.getDate("deleteAt");
                return new User(id, fullName, dob, address, phone, emailD, pass, createAt, ERole.valueOf(role), deleteAt);
            }
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return null;
    }

    public User checkUser(String email, String password) {
        try {
            String query = "SELECT * FROM users WHERE email = ? AND password = ?";
            connection = getConnection();
            ps = connection.prepareStatement(query);
            System.out.println("Function Check User " + ps);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                return getUserFromRs(rs);
            }
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return null;
    }

    public User checkAccount(String email) {
        try {
            String query = "SELECT * FROM users WHERE email = ?";
            connection = getConnection();
            ps = connection.prepareStatement(query);
            System.out.println("Function Check Account " + ps);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                return getUserFromRs(rs);
            }
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return null;
    }

    public List<User> findAll(){
       List<User> users = new ArrayList<>();
        try {
            connection = getConnection();
            ps = connection.prepareStatement(SELECT_ALL_USER);
            System.out.println("Function FindAll User " + ps);
            rs = ps.executeQuery();
            while (rs.next()) {
                User user = getUserFromRs(rs);
                users.add(user);
            }
            connection.close();
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
       return users;
    }

    private static User getUserFromRs(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String fullName = rs.getString("fullName");
        Date dob = rs.getDate("dob");
        String address = rs.getString("address");
        String phone = rs.getString("phone");
        String email = rs.getString("email");
        String password = rs.getString("password");
        Date createAt = rs.getDate("createAt");
        String role = rs.getString("role");
        Date deleteAt = rs.getDate("deleteAt");
        return new User(id, fullName, dob, address, phone, email, password, createAt, ERole.valueOf(role), deleteAt);
    }

    public User findUser(int id){
        try {
            connection = getConnection();
            ps = connection.prepareStatement(SELECT_USER_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return getUserFromRs(rs);
            }
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return null;
    }
    public void save(User user){
        try {
            connection = getConnection();
            ps = connection.prepareStatement(INSERT_USER);
            setUserFromPs(user, ps);

            ps.executeUpdate();
            System.out.println("Function save user " + ps);
            connection.close();
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
    }

    private static void setUserFromPs(User user, PreparedStatement ps) throws SQLException {
        ps.setString(1, user.getName());
        if (user.getDob() == null) {
            ps.setDate(2, null);
        } else {
            ps.setDate(2, new java.sql.Date(user.getDob().getTime()));
        }
        ps.setString(3, user.getAddress());
        ps.setString(4, user.getPhone());
        ps.setString(5, user.getEmail());
        ps.setString(6, user.getPassword());
        ps.setDate(7, new java.sql.Date(user.getCreateAt().getTime()));
        ps.setString(8, user.geteRole().name());
        if (user.getDeleteAt() == null) {
            ps.setDate(9, null);
        } else {
            ps.setDate(9, new java.sql.Date(user.getDeleteAt().getTime()));
        }
    }

    public void update (int id, User user){
        try {
            connection = getConnection();
            ps = connection.prepareStatement(UPDATE_USER);
            setUserFromPs(user, ps);
            ps.setInt(10, id);

            ps.executeUpdate();
            System.out.println("Function update user" + ps);
            connection.close();
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
    }
    public void remove (int id){
        try {
            connection = getConnection();
            ps = connection.prepareStatement(DELETE_USER);
            Date deleteAt = new Date();
            ps.setDate(1, new java.sql.Date(deleteAt.getTime()));
            ps.setInt(2, id);
            ps.executeUpdate();
            System.out.println("Function remove User " + ps);
            connection.close();
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
    }
    public int getTotalUser() {
        String query = "SELECT count(*) FROM users";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return 0;
    }
}
