package com.mitocode.service.impl;

import com.mitocode.model.Client;
import com.mitocode.repo.IClientRepo;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl extends CRUDImpl<Client, Integer> implements IClientService {

    @Autowired
    private IClientRepo repo;


    @Override
    protected IGenericRepo<Client, Integer> getRepo() {
        return repo;
    }
}
