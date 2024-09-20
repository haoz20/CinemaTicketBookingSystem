package com.cinema.dao;

import com.cinema.model.Ticket;

import java.sql.SQLException;
import java.util.List;

public class TicketDAO extends AbstractDAO<Ticket> {


    @Override
    public Ticket findById(int id) {
        return null;
    }

    @Override
    public List<Ticket> getAll() {
        return List.of();
    }

    @Override
    public void create(Ticket entity) throws SQLException {

    }

    @Override
    public void delete(Ticket entity) throws SQLException {

    }
}
