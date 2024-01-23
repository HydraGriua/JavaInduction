package com.mitocode.service;

import com.mitocode.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICategoryService extends ICRUD<Category, Integer>{

    List<Category> findByName(String name);
    List<Category> findByNameAndEnabled(String name, boolean enabled);

    List<Category> getByNameAndDescription3();

    Page<Category> findPage(Pageable pageable);

    List<Category> findAllOrder(String param);
}
