package com.cinema.dao;

import com.cinema.database.PgSqlConnectionFactory;
import com.cinema.model.Cinema;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CinemaDAO extends AbstractDAO<Cinema> {

    private PgSqlConnectionFactory connectionFactory;

    public CinemaDAO(Connection connection) {
        this.connectionFactory = new PgSqlConnectionFactory();
    }

    @Override
    public Cinema findById(int id) throws SQLException {
        String query = "select * from cinemas where id = ?";
        try (Connection connection = this.connectionFactory.createConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Cinema cinema = new Cinema();
                    cinema.setId(resultSet.getInt("id"));
                    cinema.setName(resultSet.getString("name"));
                    return cinema;
                }

            }
        }
        return null;
    }

    @Override
    public List<Cinema> getAll() throws SQLException {
        String query = "select * from cinemas";
        List<Cinema> cinemas = new ArrayList<Cinema>();
        try (Connection connection = this.connectionFactory.createConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Cinema cinema = new Cinema();
                    cinema.setId(resultSet.getInt("id"));
                    cinema.setName(resultSet.getString("name"));
                    cinemas.add(cinema);
                }
            }
            return cinemas;
        }
    }

    @Override
    public void create(Cinema cinema) throws SQLException {
        String query = "insert into cinemas (name) values (?)";
        try (Connection connection = this.connectionFactory.createConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, cinema.getName());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void delete(Cinema cinema) throws SQLException {
        String query = "delete form cinemas where id = ?";
        try (Connection connection = this.connectionFactory.createConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, cinema.getId());
            preparedStatement.executeUpdate();
        }
    }
}
