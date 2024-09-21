package com.cinema.dao;

import com.cinema.database.PgSqlConnectionFactory;
import com.cinema.model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO extends AbstractDAO<Customer> {

    private PgSqlConnectionFactory connectionFactory;

    public CustomerDAO() {
        this.connectionFactory = new PgSqlConnectionFactory();
    }

    @Override
    public Customer findById(int id) throws SQLException {
        String query = "select * from customers where id = ?";
        try (Connection connection = this.connectionFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Customer customer = new Customer();
                    customer.setId(resultSet.getInt("id"));
                    customer.setName(resultSet.getString("name"));
                    return customer;
                }
            }
        }
        return null;
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        String query = "select * from customers";
        List<Customer> customers = new ArrayList<Customer>();
        try (Connection connection = this.connectionFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Customer customer = new Customer();
                    customer.setId(resultSet.getInt("id"));
                    customer.setName(resultSet.getString("name"));
                    customers.add(customer);
                }
                return customers;
            }

        }
    }

    @Override
    public void create(Customer customer) throws SQLException {
        String query = "Insert into customers (name) values (?)";

        try (Connection connection = this.connectionFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, customer.getName());
            preparedStatement.executeUpdate();

        }

    }

    @Override
    public void delete(Customer customer) throws SQLException {
        String query = "Delete from customers where id = ?";
        Connection connection = this.connectionFactory.createConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, customer.getId());
        preparedStatement.executeUpdate();
    }
}

