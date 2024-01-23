package com.mitocode.controller;

import com.mitocode.dto.UserDTO;
import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model.User;
import com.mitocode.service.IUserService;
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
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService service;

    @Autowired
    @Qualifier("clientMapper")
    private ModelMapper mapper;

    //@GetMapping
    /*public List<User> readAll() throws Exception{
        return service.readAll();
    }*/
    @GetMapping
    public ResponseEntity<List<UserDTO>> readAll() throws Exception{
        List<UserDTO> list = service.readAll().stream().map(cat -> mapper.map(cat, UserDTO.class)).collect(Collectors.toList());
        /*List<UserDTO> list = service.readAll().stream().map(cat -> {
           UserDTO dto = new UserDTO();
           dto.setId(cat.getIdUser());
           dto.setNameUser(cat.getName());
           dto.setDescriptionUser(cat.getDescription());
           dto.setEnabledUser(cat.isEnabled());
           return dto;
        }).collect(Collectors.toList());*/
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> readById(@PathVariable("id") Integer id) throws Exception{
        UserDTO obj = mapper.map(service.readById(id), UserDTO.class);
        if(obj == null){
            //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO dto) throws Exception{
        User obj = service.save(mapper.map(dto, User.class));
        return new ResponseEntity<>(mapper.map(obj, UserDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<UserDTO> update(@Valid @RequestBody UserDTO dto) throws Exception{
        User obj = service.update(mapper.map(dto, User.class));
        return new ResponseEntity<>(mapper.map(obj, UserDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        User obj = service.readById(id);

        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /*@PutMapping("/{id}")
    public User update(@PathVariable("id")Integer id, @RequestBody User client) throws Exception{
        client.setIdUser(id);
        return service.save(client);
    }*/


}
