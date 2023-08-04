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

import java.util.Optional;

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
    void updateTest() {

        String categoryid="edfg";
        CategoryDto categoryDto=CategoryDto.builder()
                .title("Java developer")
                .description("this is backend java developer")
                .coverImage("abc.png")
                .build();

        Mockito.when(categoryRepository.findById(Mockito.anyString())).thenReturn(Optional.of(category));
        Mockito.when(categoryRepository.save(Mockito.any())).thenReturn(category);

        CategoryDto updatecategory = categoryServiceimpl.update(categoryDto, categoryid);
        System.out.println(updatecategory.getTitle());
        System.out.println(updatecategory.getDescription());

        Assertions.assertNotNull(categoryDto);
        Assertions.assertEquals(categoryDto.getTitle(),updatecategory.getTitle(),"Title is not valid");






    }

    @Test
    void deleteTest() {

        String categoryid="ghgh";

        Mockito.when(categoryRepository.findById(Mockito.any())).thenReturn(Optional.of(category));

        categoryServiceimpl.delete(categoryid);

        Mockito.verify(categoryRepository,Mockito.times(1)).delete(category);


    }

    @Test
    void getAll() {
    }

    @Test
    void get() {
    }
}