package com.mitocode.controller;

import com.mitocode.dto.ProductDTO;
import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model.Product;
import com.mitocode.service.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService service;

    @Autowired
    @Qualifier("productMapper")
    private ModelMapper mapper;

    //@GetMapping
    /*public List<Product> readAll() throws Exception{
        return service.readAll();
    }*/
    @GetMapping
    public ResponseEntity<List<ProductDTO>> readAll() throws Exception{
        List<ProductDTO> list = service.readAll().stream().map(prod -> mapper.map(prod, ProductDTO.class)).collect(Collectors.toList());
        /*List<ProductDTO> list = service.readAll().stream().map(cat -> {
           ProductDTO dto = new ProductDTO();
           dto.setId(cat.getIdProduct());
           dto.setNameProduct(cat.getName());
           dto.setDescriptionProduct(cat.getDescription());
           dto.setEnabledProduct(cat.isEnabled());
           return dto;
        }).collect(Collectors.toList());*/
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> readById(@PathVariable("id") Integer id) throws Exception{
        ProductDTO obj = mapper.map(service.readById(id), ProductDTO.class);
        if(obj == null){
            //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO dto) throws Exception{
        Product obj = service.save(mapper.map(dto, Product.class));
        return new ResponseEntity<>(mapper.map(obj, ProductDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ProductDTO> update(@Valid @RequestBody ProductDTO dto) throws Exception{
        Product obj = service.update(mapper.map(dto, Product.class));
        return new ResponseEntity<>(mapper.map(obj, ProductDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        Product obj = service.readById(id);

        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /*@PutMapping("/{id}")
    public Product update(@PathVariable("id")Integer id, @RequestBody Product category) throws Exception{
        category.setIdProduct(id);
        return service.save(category);
    }*/


}
