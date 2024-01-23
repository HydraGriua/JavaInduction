package com.mitocode.controller;

import com.mitocode.dto.CategoryDTO;
import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model.Category;
import com.mitocode.service.ICategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private ICategoryService service;

    @Autowired
    @Qualifier("categoryMapper")
    private ModelMapper mapper;

    //@GetMapping
    /*public List<Category> readAll() throws Exception{
        return service.readAll();
    }*/
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> readAll() throws Exception{
        List<CategoryDTO> list = service.readAll().stream().map(cat -> mapper.map(cat, CategoryDTO.class)).collect(Collectors.toList());
        /*List<CategoryDTO> list = service.readAll().stream().map(cat -> {
           CategoryDTO dto = new CategoryDTO();
           dto.setId(cat.getIdCategory());
           dto.setNameCategory(cat.getName());
           dto.setDescriptionCategory(cat.getDescription());
           dto.setEnabledCategory(cat.isEnabled());
           return dto;
        }).collect(Collectors.toList());*/
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> readById(@PathVariable("id") Integer id) throws Exception{
        CategoryDTO obj = mapper.map(service.readById(id), CategoryDTO.class);
        if(obj == null){
            //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> create(@Valid @RequestBody CategoryDTO dto) throws Exception{
        Category obj = service.save(mapper.map(dto, Category.class));
        return new ResponseEntity<>(mapper.map(obj, CategoryDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CategoryDTO> update(@Valid @RequestBody CategoryDTO dto) throws Exception{
        Category obj = service.readById(dto.getId());

        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + dto.getId());
        }

        Category cat = service.update(mapper.map(dto, Category.class));
        return new ResponseEntity<>(mapper.map(cat, CategoryDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        Category obj = service.readById(id);

        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /*@PutMapping("/{id}")
    public Category update(@PathVariable("id")Integer id, @RequestBody Category category) throws Exception{
        category.setIdCategory(id);
        return service.save(category);
    }*/

    /////////////////////queries//////////////////
    @GetMapping("/find/name/{name}")
    public ResponseEntity<List<CategoryDTO>> findByName(@PathVariable("name") String name){
        List<CategoryDTO> lst = service.findByName(name).stream().map(cat -> mapper.map(cat, CategoryDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(lst, HttpStatus.OK);
    }

    @GetMapping("/find/name")
    public ResponseEntity<List<CategoryDTO>> findByNameAndEnabled(@RequestParam("name") String name, @RequestParam("status") boolean status){
        List<CategoryDTO> lst = service.findByNameAndEnabled(name, status).stream().map(cat -> mapper.map(cat, CategoryDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(lst, HttpStatus.OK);
    }

    @GetMapping("/get/name/description3")
    public ResponseEntity<List<CategoryDTO>> getByNameAndDescription3() throws Exception {
        List<CategoryDTO> lst = service.getByNameAndDescription3().stream().map(cat -> mapper.map(cat, CategoryDTO.class)).collect(Collectors.toList());

        return new ResponseEntity<>(lst, HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<Category>> findPage(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ){

        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Category> pageResponse = service.findPage(pageRequest);

        return new ResponseEntity<>(pageResponse, HttpStatus.OK);
    }

    @GetMapping("/pagination2")
    public ResponseEntity<Page<Category>> findPage(Pageable pageable){

        //PageRequest pageRequest = PageRequest.of(page, size);
        Page<Category> pageResponse = service.findPage(pageable);

        return new ResponseEntity<>(pageResponse, HttpStatus.OK);
    }

    @GetMapping("/order")
    public ResponseEntity<List<CategoryDTO>> findAllOrder(
            @RequestParam(name = "param", defaultValue = "ASC") String param
    ){
        List<CategoryDTO> lst = service.findAllOrder(param).stream().map(cat -> mapper.map(cat, CategoryDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(lst, HttpStatus.OK);
    }


}
