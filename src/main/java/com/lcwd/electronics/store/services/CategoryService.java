package com.lcwd.electronics.store.services;


import com.lcwd.electronics.store.dtos.CategoryDto;
import com.lcwd.electronics.store.dtos.PagebleResponse;
import com.lcwd.electronics.store.entities.Category;

public interface CategoryService {

    // create

    CategoryDto create(CategoryDto categoryDto);

    // update

    CategoryDto update(CategoryDto categoryDto, String catetgoryId);

    // delete

    void delete(String categoryId);

    // get all

    // List<CategoryDto>getAll();
    PagebleResponse<CategoryDto> getall(int pageNumber, int pageSize, String sortBy, String sortDir);

    // get single category

    //  List<CategoryDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir);

    CategoryDto get(String categoryId);

    // search


}













