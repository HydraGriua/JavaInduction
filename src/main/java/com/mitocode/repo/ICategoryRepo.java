package com.mitocode.repo;

import com.mitocode.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

//@Repository
public interface ICategoryRepo extends IGenericRepo<Category, Integer> {

    //SELECT * FROM Category c WHERE c.name LIKE ?;
    //DerivedQueries
    List<Category> findByName(String name);
    List<Category> findByNameLike(String name);
    List<Category> findByNameContains(String name);
    //%XYZ% -> findByNameContains
    //%XYZ -> findByNameStartsWith
    //XYZ% -> findByNameEndsWith
    List<Category> findByNameAndEnabled(String name, boolean enabled);
    List<Category> findByNameOrEnabled(String name, boolean enabled);
    List<Category> findByEnabled(boolean enabled);
    List<Category> findByEnabledTrue();
    List<Category> findByEnabledFalse();
    Category findOneByName(String name);
    List<Category> findTop3ByName(String name);
    List<Category> findByNameIs(String name);
    List<Category> findByNameIsNot(String name);
    List<Category> findByNameIsNull();
    List<Category> findByNameIsNotNull();
    List<Category> findByNameEqualsIgnoreCase(String name);

    List<Category> findByIdCategoryLessThan(Integer id);
    List<Category> findByIdCategoryLessThanEqual(Integer id);
    List<Category> findByIdCategoryGreaterThan(Integer id);
    List<Category> findByIdCategoryGreaterThanEqual(Integer id);
    List<Category> findByIdCategoryBetween(Integer id1, Integer id2);
    List<Category> findByNameOrderByDescription(String name);
    List<Category> findByNameOrderByDescriptionAsc(String name);
    List<Category> findByNameOrderByDescriptionDesc(String name);


    //JPQL Java Persistence Query Language
    @Query("FROM Category c WHERE c.name = ?1 AND c.description LIKE %?2%")
    //@Query("FROM Sale s WHERE s.client.cardId = ?1")
    List<Category> getByNameAndDescription1(String name, String description);

    @Query("FROM Category c WHERE c.name = :name AND c.description LIKE %:description%")
    List<Category> getByNameAndDescription2(@Param("name") String name, @Param("description") String description);

    @Query("SELECT new Category(c.name, c.description) FROM Category c")
    List<Category> getByNameAndDescription3();

    @Query("SELECT new Category(c.name) FROM Category c")
    List<Category> getByNameAndDescription4();

    //////////////////////////////////////////////////////

    //SQL NativeQuery
    @Query(value = "SELECT * FROM category WHERE name = :name", nativeQuery = true)
    List<Category> getByNameSQL(@Param("name") String name);

    @Modifying //para INSERT, UPDATE, DELETE
    @Query(value = "UPDATE category SET name = :name", nativeQuery = true)
    Integer updateNames(@Param("name") String name);
}
