package com.mitocode.config;

import com.mitocode.dto.ProductDTO;
import com.mitocode.model.Product;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean("categoryMapper")
    public ModelMapper categoryMapper(){
        return new ModelMapper();
    }

    @Bean("productMapper")
    public ModelMapper modelMapper(){
        ModelMapper mapper = new ModelMapper();
        //TypeMap<ProductDTO, Product> typeMap = mapper.createTypeMap(ProductDTO.class, Product.class);
        //typeMap.addMapping(ProductDTO::getIdCategoria, (dest, v) -> dest.getCategory().setIdCategory((Integer) v));
        //typeMap.addMapping(ProductDTO::getIdCategory, (dest, v) -> dest.getCategory().setIdCategory((Integer) v));
        return mapper;
    }


    @Bean("roleMapper")
    public ModelMapper roleMapper(){
        return new ModelMapper();
    }

    @Bean("clientMapper")
    public ModelMapper clientMapper(){
        return new ModelMapper();
    }

    @Bean("providerMapper")
    public ModelMapper providerMapper(){
        return new ModelMapper();
    }

    @Bean("userMapper")
    public ModelMapper userMapper() {
        return new ModelMapper();
    }

    @Bean("saleMapper")
    public ModelMapper saleMapper() {
        return new ModelMapper();
    }
}
