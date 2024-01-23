package com.mitocode.controller;

import com.mitocode.dto.RoleDTO;
import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model.Role;
import com.mitocode.service.IRoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private IRoleService service;

    @Autowired
    @Qualifier("roleMapper")
    private ModelMapper mapper;

    //@GetMapping
    /*public List<Role> readAll() throws Exception{
        return service.readAll();
    }*/
    @GetMapping
    public ResponseEntity<List<RoleDTO>> readAll() throws Exception{
        List<RoleDTO> list = service.readAll().stream().map(role -> mapper.map(role, RoleDTO.class)).collect(Collectors.toList());
        /*List<RoleDTO> list = service.readAll().stream().map(cat -> {
           RoleDTO dto = new RoleDTO();
           dto.setId(cat.getIdRole());
           dto.setNameRole(cat.getName());
           dto.setDescriptionRole(cat.getDescription());
           dto.setEnabledRole(cat.isEnabled());
           return dto;
        }).collect(Collectors.toList());*/
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> readById(@PathVariable("id") Integer id) throws Exception{
        RoleDTO obj = mapper.map(service.readById(id), RoleDTO.class);
        if(obj == null){
            //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RoleDTO> create(@Valid @RequestBody RoleDTO dto) throws Exception{
        Role obj = service.save(mapper.map(dto, Role.class));
        return new ResponseEntity<>(mapper.map(obj, RoleDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<RoleDTO> update(@Valid @RequestBody RoleDTO dto) throws Exception{
        Role obj = service.update(mapper.map(dto, Role.class));
        return new ResponseEntity<>(mapper.map(obj, RoleDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        Role obj = service.readById(id);

        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /*@PutMapping("/{id}")
    public Role update(@PathVariable("id")Integer id, @RequestBody Role role) throws Exception{
        role.setIdRole(id);
        return service.save(role);
    }*/


}
