package com.mitocode.service;

import com.mitocode.model.Category;
import com.mitocode.repo.ICategoryRepo;
import com.mitocode.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class CategoryServiceTest {

    @InjectMocks
    private CategoryServiceImpl service;

    @MockBean
    private ICategoryRepo repo;

    private Category CATEGORY_1;
    private Category CATEGORY_2;
    private Category CATEGORY_3;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);

        Category CATEGORY_1 = new Category(1, "TV", "Televisor", true);
        Category CATEGORY_2 = new Category(2, "PS", "Play Station", true);
        Category CATEGORY_3 = new Category(3, "BOOKS", "Some books", true);

        List<Category> categories = Arrays.asList(CATEGORY_1, CATEGORY_2, CATEGORY_3);
        Mockito.when(repo.findAll()).thenReturn(categories);
        Mockito.when(repo.findById(any())).thenReturn(Optional.of(CATEGORY_1));
        Mockito.when(repo.save(any())).thenReturn(CATEGORY_1);
    }

    @Test
    public void readAllTest() throws Exception{
        List<Category> response = service.readAll();

        assertNotNull(response);
        assertFalse(response.isEmpty());
        assertEquals(response.size(), 3);
    }

    @Test
    public void readByIdTest() throws Exception{
        Category response = service.readById(1);
        assertNotNull(response);
    }

    @Test
    public void save() throws Exception{
        Category response = service.save(CATEGORY_1);
        assertNotNull(response);
    }

    @Test
    public void update() throws Exception{
        Category response = service.update(CATEGORY_1);
        assertNotNull(response);
    }

    @Test
    public void delete() throws Exception{
        repo.deleteById(1);
        //repo.deleteById(1);

        verify(repo, times(2)).deleteById(1);
    }
}
