package com.mitocode.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity//(name = "XYZ")
//@Table(name = "tbl_category")
public class Category {

    //camelCase -> lowerCamelCase -> UpperCamelCase
    //db -> snake format ____
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategory;

    @Column(length = 50, nullable = false) //name = "category_name"
    private String name;

    @Column(length = 150, nullable = false)
    private String description;

    @Column(nullable = false)
    private boolean enabled;

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Category(String name) {
        this.name = name;
    }
}
