package com.lcwd.electronics.store.services.impl;

import com.lcwd.electronics.store.dtos.CategoryDto;
import com.lcwd.electronics.store.dtos.UserDto;
import com.lcwd.electronics.store.entities.Category;
import com.lcwd.electronics.store.repositories.CategoryRepository;
import com.lcwd.electronics.store.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryServiceImplTest {


    @MockBean
    private CategoryRepository categoryRepository;


    @Autowired
    private CategoryServiceImpl categoryServiceimpl;

    @Autowired
    private ModelMapper modelMapper;

    Category category;



    CategoryDto categoryDto;

    @BeforeEach
    public  void  init(){

        category=Category.builder()
                .title("Mobile")
                .description("This is mobile category")
                .coverImage("mobile.png")
                .build();

        categoryDto=CategoryDto.builder()
                .title("Phone")
                .description("This is Phone Category")
                .coverImage("phone.png")
                .build();



    }


    @Test
    void createTest() {

      // Arrange
        Mockito.when(categoryRepository.save(Mockito.any())).thenReturn(category);


        // Actual call

        CategoryDto category1 = categoryServiceimpl.create(modelMapper.map(category, CategoryDto.class));
         System.out.println(category1.getTitle());

        // Assert

        Assertions.assertNotNull(category1);
        Assertions.assertEquals("Mobile",category1.getTitle());

    }





    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void getAll() {
    }

    @Test
    void get() {
    }
}