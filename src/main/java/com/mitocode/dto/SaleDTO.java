package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mitocode.model.SaleDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaleDTO {

    private Integer idSale;

    @NotNull
    @JsonIncludeProperties(value = {"idClient"})
    private ClientDTO client;

    @NotNull
    @JsonIncludeProperties(value = {"idUser", "username"})
    private UserDTO user;

    @NotNull
    private LocalDateTime datetime; //ISO Date

    @NotNull
    @Min(value = 1)
    private double total;

    @NotNull
    @Min(value = 1)
    private double tax;

    @NotNull
    private boolean enabled;

    @NotNull
    @JsonManagedReference
    private List<SaleDetailDTO> details;
}
