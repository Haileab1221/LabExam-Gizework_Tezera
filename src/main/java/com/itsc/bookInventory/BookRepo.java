package com.itsc.bookInventory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class BookRepo {
    private String databaseName = "bookDB";
    private String tableName = "book";
    private String useDatabase = "USE " + databaseName;
    private String createDB = "CREATE DATABASE " + databaseName;
    private String createTable = "CREATE TABLE " + tableName + " (id int auto_increment primary key, title varchar(255), author varchar(255))";

    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;

    public void createConnection() {
        String url = "jdbc:mysql://localhost:3306/?user=root";
        String username = "root";
        String password = "1221";

        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void createDBAndTable() {
        try {
            createConnection();
            statement.executeUpdate(createDB);
            statement.executeUpdate(useDatabase);
            statement.executeUpdate(createTable);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public void insertIntoTable(Book book) {
        String insertData = "INSERT INTO " + tableName + " (title, author) VALUES (?, ?)";
        try {
            createConnection();
            statement.executeUpdate(useDatabase);
            preparedStatement = connection.prepareStatement(insertData);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }
}
