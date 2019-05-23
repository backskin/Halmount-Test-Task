package com.haulmont.testtask.model.db;

import java.sql.*;
import java.util.List;

public abstract class AbstractDBController<E,K> {

    private MySingletonDatabase db;
    private String tableName;

    AbstractDBController(String tableName) {
        db = MySingletonDatabase.getInstance();
        this.tableName = tableName;
    }

    public abstract List<E> getAll();
    public abstract E getEntityById(K id);
    public abstract E update(E entity);
    public abstract boolean delete(K id);
    public abstract boolean create(E entity);

    protected ResultSet sendQuery(String sql) {

        Statement statement;
        try {
            statement = db.getConnetion().createStatement();
            ResultSet rs = statement.executeQuery(sql);
            statement.close();
            return rs;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}