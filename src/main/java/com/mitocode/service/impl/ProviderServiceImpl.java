package com.mitocode.service.impl;

import com.mitocode.model.Provider;
import com.mitocode.repo.IProviderRepo;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.service.IProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProviderServiceImpl extends CRUDImpl<Provider, Integer> implements IProviderService {

    @Autowired
    private IProviderRepo repo;


    @Override
    protected IGenericRepo<Provider, Integer> getRepo() {
        return repo;
    }
}
