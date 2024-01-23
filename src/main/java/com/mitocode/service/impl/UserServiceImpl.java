package com.mitocode.service.impl;

import com.mitocode.model.User;
import com.mitocode.repo.IUserRepo;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends CRUDImpl<User, Integer> implements IUserService {

    @Autowired
    private IUserRepo repo;


    @Override
    protected IGenericRepo<User, Integer> getRepo() {
        return repo;
    }
}
