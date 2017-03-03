package controller;

import model.User;
import java.sql.*;
import java.util.*;

/**
 * Created by NortonWEI on 2/3/2017.
 */
public class DatabaseConnector {
    private String username = "";
    private String password = "";
    private Connection connection;

    public DatabaseConnector() {
        this.username = "root";
        this.password = "";
    }

    private void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        try {
            String dbUrl = "jdbc:mysql://101.78.175.101:3380/movie_ticketing_system_db";
            connection = DriverManager
                    .getConnection(dbUrl,username, password);
            System.out.println("Database connected to " + dbUrl);
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
            return;
        }
    }

    public ArrayList<Integer> listUserId() {
        ArrayList<Integer> userIdList = new ArrayList<Integer>();
        connect();
        try {
            Statement statement = connection.createStatement();
            String sqlStr = "SELECT ID FROM USER";
            ResultSet resultSet = statement.executeQuery(sqlStr);

            while (resultSet.next()) {
                userIdList.add(resultSet.getInt(1));
            }
            resultSet.close();
            statement.close();
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userIdList;
    }

    public void createUser(User user) {
        try {
            connect();
            Statement statement = connection.createStatement();
            String sqlStr = "INSERT INTO USER VALUES (" +
                    user.getUserId() + ", " +
                    "'" + user.getName() + "', " +
                    "'" + user.getUsername() + "', " +
                    "'" + user.getPassword() + "', " +
                    "str_to_date('" + user.getBirthday() + "', '%d-%m-%Y'), " +
                    user.getGender() + ", " +
                    user.getTel() +
                    ")";
            statement.executeUpdate(sqlStr);
            statement.close();
            close();
            System.out.println("Added user with id: " + user.getUserId());
        } catch (SQLException e) {
            System.out.println("Create user failed!");
            e.printStackTrace();
            return;
        }
    }

    public User findUser(String username) {
        User foundUser = null;
        connect();
        try {
            Statement statement = connection.createStatement();
            String sqlStr = "SELECT * FROM USER " +
                    "WHERE USERNAME = '" + username + "'";
            ResultSet resultSet = statement.executeQuery(sqlStr);
            if (resultSet.next()) {
                System.out.println("Found user with id: " + resultSet.getInt(1));
                foundUser = new User(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getInt(6), resultSet.getInt(7));
            } else {
                System.out.println("User not found!");
            }
            resultSet.close();
            statement.close();
            close();
        } catch (SQLException e) {
            System.out.println("Find user failed!");
            e.printStackTrace();
        }
        return foundUser;
    }

    public void updateUser(String name, String username, String password, String birthday, int gender, int tel) {
        try {
            User foundUser = findUser(username);
            if (foundUser != null) {
                connect();
                Statement statement = connection.createStatement();
                String sqlStr = "UPDATE USER " +
                        "SET NAME = '" +
                        name + "', PASSWORD = '" +
                        password + "', BIRTHDAY = str_to_date('" +
                        birthday + "', '%d-%m-%Y'), GENDER = " +
                        gender + ", TEL = " +
                        tel + " " +
                        "WHERE ID = " + foundUser.getUserId();
                statement.executeUpdate(sqlStr);
                statement.close();
                close();
                System.out.println("Updated user with id: " + foundUser.getUserId());
            }
        } catch (SQLException e) {
            System.out.println("Update user failed!");
            e.printStackTrace();
            return;
        }
    }

    private void close() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Database disconnected!");
            }
        } catch (SQLException e) {
            System.out.println("Database disconnection failed!");
            e.printStackTrace();
            return;
        }
    }
}
