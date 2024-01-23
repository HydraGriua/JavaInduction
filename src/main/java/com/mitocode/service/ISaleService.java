package com.mitocode.service;

import com.mitocode.dto.IProcedureDTO;
import com.mitocode.dto.ProcedureDTO;
import com.mitocode.model.Sale;

import java.util.List;
import java.util.Map;

//import java.util.List;

public interface ISaleService extends ICRUD<Sale, Integer>{

    List<ProcedureDTO> callProcedure();
    List<ProcedureDTO> callProcedure2();
    List<IProcedureDTO> callProcedure3();

    Sale getSaleMostExpensive();
    String getBestSellerPerson(); //el mejor vendedor
    Map<String, Long> getSalesCountBySeller();
    Map<String, Double> getMostSellerProduct();

    /*Sale save(Sale category) throws Exception;
    Sale update(Sale category) throws Exception;
    List<Sale> readAll() throws Exception;
    Sale readById(Integer id) throws Exception;
    void delete(Integer id) throws Exception;*/
}
