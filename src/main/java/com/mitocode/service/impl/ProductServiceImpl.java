package com.mitocode.service.impl;

import com.mitocode.model.Product;
import com.mitocode.repo.IProductRepo;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends CRUDImpl<Product, Integer> implements IProductService {

    @Autowired
    private IProductRepo repo;

    @Override
    protected IGenericRepo<Product, Integer> getRepo() {
        return repo;
    }


    /*@Override
    public Product save(Product category) throws Exception {
        return repo.save(category);
    }

    @Override
    public Product update(Product category) throws Exception {
        return repo.save(category);
    }

    @Override
    public List<Product> readAll() throws Exception {
        return repo.findAll();
    }

    @Override
    public Product readById(Integer id) throws Exception {
        //Optional<Product> op = repo.findById(id);
        //return op.isPresent() ? op.get() : new Product();
        //return op.orElse(new Product());
        return repo.findById(id).orElse(new Product());
    }

    @Override
    public void delete(Integer id) throws Exception {
        repo.deleteById(id);
    }*/
}
