package com.ecommerce.project.Service;
import com.ecommerce.project.Payload.CategoryDTO;
import com.ecommerce.project.Payload.CategoryResponse;

import java.util.List;


public interface CategoryService {
    CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize);
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    //String  updateCategory(Long categoryId , String categoryName);

    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId);

    CategoryDTO deleteCategory(Long categoryId);
}
