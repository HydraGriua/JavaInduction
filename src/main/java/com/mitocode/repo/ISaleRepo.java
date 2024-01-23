package com.mitocode.repo;

import com.mitocode.dto.IProcedureDTO;
import com.mitocode.dto.ProcedureDTO;
import com.mitocode.model.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISaleRepo extends IGenericRepo<Sale, Integer> {

    //[2,	"30/11/2022"]
    @Query(value = "select * from fn_sales()", nativeQuery = true)
    List<Object[]> callProcedure();

    /*@Query(value = "select * from fn_sales(:name, :descrip)", nativeQuery = true)
    List<Object[]> callProcedure(@Param("name") String name, @Param("descrip") String description);*/

    //Es el del SqlResultSetMapping
    @Query(name = "Sale.fn_sales", nativeQuery = true)
    List<ProcedureDTO> callProcedure2();

    //Es el del NamedStoredProcedureQuery getFnSales
    @Procedure(procedureName = "fn_sales")
    List<IProcedureDTO> callProcedure3();

    //Es el del NamedStoredProcedureQuery getFnSales2
    @Procedure(procedureName = "fn_salesParameter" )
    List<IProcedureDTO> callProcedure4(@Param("p_id_client") Integer idClient);
}
