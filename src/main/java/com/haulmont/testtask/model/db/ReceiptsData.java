package com.haulmont.testtask.model.db;

import com.haulmont.testtask.model.entities.Receipt;

import java.util.List;

public class ReceiptsData extends AbstractDBController<Receipt, Long> {

    public ReceiptsData(){
        super();
    }

    @Override
    public List<Receipt> getAll() {
        return null;
    }

    @Override
    public Receipt getEntityById(Long id) {
        return null;
    }

    @Override
    public Receipt update(Receipt entity) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean create(Receipt entity) {
        return false;
    }
}
