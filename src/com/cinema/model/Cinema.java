package com.cinema.model;

import java.util.ArrayList;
import java.util.List;

public class Cinema {
    private int id;
    private String name;
    private List<Theatre> theatres = new ArrayList<Theatre>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Cinema [id=" + id + ", name=" + name + "]";
    }
}
