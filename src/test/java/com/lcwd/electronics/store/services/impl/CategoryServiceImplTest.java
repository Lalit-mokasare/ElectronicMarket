package com.lcwd.electronics.store.services.impl;

import com.lcwd.electronics.store.dtos.CategoryDto;
import com.lcwd.electronics.store.dtos.PagebleResponse;
import com.lcwd.electronics.store.dtos.UserDto;
import com.lcwd.electronics.store.entities.Category;
import com.lcwd.electronics.store.entities.User;
import com.lcwd.electronics.store.repositories.CategoryRepository;
import com.lcwd.electronics.store.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
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


    Category category1;

     Category category2;


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
    void getAllTest() {

         category1=Category.builder()
                .title("")
                .description("This is mobile category")
                .coverImage("mobile.png")
                .build();


        category2=Category.builder()
                .title("Mobile")
                .description("This is mobile category")
                .coverImage("mobile.png")
                .build();

        List<Category> categoryList= Arrays.asList(category,category1,category2);
        Page<Category> page =new PageImpl<>(categoryList);

        Mockito.when(categoryRepository.findAll((Pageable) Mockito.any())).thenReturn(page);

        PagebleResponse<CategoryDto> allCategory=categoryServiceimpl.getAll(1,2,"title","asc");
        Assertions.assertEquals(3,allCategory.getContent().size());

    }

    @Test
    void get() {
    }
}