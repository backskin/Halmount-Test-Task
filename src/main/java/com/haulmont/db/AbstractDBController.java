package com.haulmont.db;

import java.sql.*;
import java.util.List;

public abstract class AbstractDBController<E,K> {

    private MySingletonDatabase db;

    AbstractDBController() {
        db = MySingletonDatabase.getInstance();
    }

    public abstract List<E> getAll();
    public abstract E getEntityById(K id);
    public abstract E update(E entity);
    public abstract boolean delete(K id);
    public abstract boolean create(E entity);

    protected ResultSet sendQuery(String sql) throws SQLException {

        Statement statement = db.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        statement.close();
        return rs;
    }
}