package com.mitocode.controller;

import com.mitocode.dto.ProviderDTO;
import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model.Provider;
import com.mitocode.service.IProviderService;
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
@RequestMapping("/providers")
public class ProviderController {

    @Autowired
    private IProviderService service;

    @Autowired
    @Qualifier("providerMapper")
    private ModelMapper mapper;

    //@GetMapping
    /*public List<Provider> readAll() throws Exception{
        return service.readAll();
    }*/
    @GetMapping
    public ResponseEntity<List<ProviderDTO>> readAll() throws Exception{
        List<ProviderDTO> list = service.readAll().stream().map(prov -> mapper.map(prov, ProviderDTO.class)).collect(Collectors.toList());
        /*List<ProviderDTO> list = service.readAll().stream().map(cat -> {
           ProviderDTO dto = new ProviderDTO();
           dto.setId(cat.getIdProvider());
           dto.setNameProvider(cat.getName());
           dto.setDescriptionProvider(cat.getDescription());
           dto.setEnabledProvider(cat.isEnabled());
           return dto;
        }).collect(Collectors.toList());*/
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProviderDTO> readById(@PathVariable("id") Integer id) throws Exception{
        ProviderDTO obj = mapper.map(service.readById(id), ProviderDTO.class);
        if(obj == null){
            //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProviderDTO> create(@Valid @RequestBody ProviderDTO dto) throws Exception{
        Provider obj = service.save(mapper.map(dto, Provider.class));
        return new ResponseEntity<>(mapper.map(obj, ProviderDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ProviderDTO> update(@Valid @RequestBody ProviderDTO dto) throws Exception{
        Provider obj = service.update(mapper.map(dto, Provider.class));
        return new ResponseEntity<>(mapper.map(obj, ProviderDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        Provider obj = service.readById(id);

        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /*@PutMapping("/{id}")
    public Provider update(@PathVariable("id")Integer id, @RequestBody Provider provider) throws Exception{
        provider.setIdProvider(id);
        return service.save(provider);
    }*/


}
