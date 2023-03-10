package com.williams.service.impl;

import com.williams.exception.ModelNotFoundException;
import com.williams.repository.IGenericRepo;
import com.williams.service.ICRUD;

import java.util.List;

public abstract class CRUDImpl<T, ID> implements ICRUD<T, ID> {

    protected abstract IGenericRepo<T,ID> getRepo();
    @Override
    public T save(T t) throws Exception {
        return getRepo().save(t);
    }

    @Override
    public T update(T t, ID id) throws Exception {
        readById(id);
        return getRepo().save(t);
    }

    @Override
    public List<T> readAll() throws Exception {
        return getRepo().findAll();
    }

    @Override
    public T readById(ID id) throws Exception {
        return getRepo().findById(id).orElseThrow(()->new ModelNotFoundException("ID NOT FOUND"));
    }

    @Override
    public void delete(ID id) throws Exception {
        readById(id);
        getRepo().deleteById(id);
    }
}
