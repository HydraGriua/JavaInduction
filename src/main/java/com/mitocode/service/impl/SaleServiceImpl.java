package com.mitocode.service.impl;

import com.mitocode.dto.IProcedureDTO;
import com.mitocode.dto.ProcedureDTO;
import com.mitocode.model.Sale;
import com.mitocode.model.SaleDetail;
import com.mitocode.repo.ISaleRepo;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.service.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Stream;
import static java.util.stream.Collectors.*;

@Service
public class SaleServiceImpl extends CRUDImpl<Sale, Integer> implements ISaleService {

    @Autowired
    private ISaleRepo repo;


    @Override
    protected IGenericRepo<Sale, Integer> getRepo() {
        return repo;
    }

    @Override
    public List<ProcedureDTO> callProcedure() {
        //List<Object[]>
        //[2,	"30/11/2022"]
        List<ProcedureDTO> lst = new ArrayList<>();
        repo.callProcedure().forEach( e -> {
            ProcedureDTO dto = new ProcedureDTO();
            dto.setQuantityfn((Integer) e[0]);
            dto.setDatetimefn((String) e[1]);
            lst.add(dto);
        });
        return lst;
    }

    @Override
    public List<ProcedureDTO> callProcedure2() {
        return repo.callProcedure2();
    }

    @Transactional
    @Override
    public List<IProcedureDTO> callProcedure3() {
        return repo.callProcedure3();
    }

    @Override
    public Sale getSaleMostExpensive() {
        return repo.findAll()
                .stream()
                .max(Comparator.comparing(Sale::getTotal))
                .orElse(new Sale());
    }

    @Override
    public String getBestSellerPerson() {
        Map<String, Double> byUser = repo.findAll()
                .stream()
                .collect(groupingBy(s -> s.getUser().getUsername(), summingDouble(Sale::getTotal)));
        /*
            select id_user, sum(total) from sale
            group by id_user
         */

        //System.out.println(byUser);
        System.out.println(byUser.entrySet());

        String user = Collections.max(byUser.entrySet(), Comparator.comparingDouble(Map.Entry::getValue)).getKey();

        return user;
    }

    @Override
    public Map<String, Long> getSalesCountBySeller() {
        return repo.findAll()
                .stream()
                .collect(groupingBy(s -> s.getUser().getUsername(), counting()));
    }

    @Override
    public Map<String, Double> getMostSellerProduct() {
        Stream<List<SaleDetail>> stream = repo.findAll().stream().map(Sale::getDetails);
        Stream<SaleDetail> streamDetail = stream.flatMap(Collection::stream); //list -> list.stream()

        Map<String, Double> byProduct = streamDetail
                .collect(groupingBy(d -> d.getProduct().getName(), summingDouble(SaleDetail::getQuantity)));

        return byProduct.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new //() -> new HashMap<>()
                ));
    }
}
