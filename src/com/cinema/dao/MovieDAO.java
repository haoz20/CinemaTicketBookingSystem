package com.cinema.dao;

import com.cinema.database.PgSqlConnectionFactory;
import com.cinema.model.*;

import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO extends AbstractDAO<Movie> {

    private PgSqlConnectionFactory connectionFactory;

    public MovieDAO() {
        this.connectionFactory = new PgSqlConnectionFactory();
    }

    @Override
    public Movie findById(int id) throws SQLException {
        String query = "select * form movies where id = ?";
        try (Connection connection = this.connectionFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Movie movie = new Movie();
                    movie.setId(resultSet.getInt("id"));
                    movie.setName(resultSet.getString("name"));
                    return movie;
                }
            }
        }
        return null;
    }

    @Override
    public List<Movie> getAll() throws SQLException {
        String query = "select * from movies";
        List<Movie> movies = new ArrayList<>();
        try (Connection connection = this.connectionFactory.createConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Movie movie = new Movie();
                    movie.setId(resultSet.getInt("id"));
                    movie.setName(resultSet.getString("name"));
                    movies.add(movie);
                }
            }
        }
        return movies;
    }

    @Override
    public void create(Movie movie) throws SQLException {
        String query = "insert into movies (names) values (?)";
        try (Connection connection = this.connectionFactory.createConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, movie.getName());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void delete(Movie movie) throws SQLException {
        String query = "delete from movies where id = ?";
        try (Connection connection = this.connectionFactory.createConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, movie.getId());
            preparedStatement.executeUpdate();
        }
    }
}
