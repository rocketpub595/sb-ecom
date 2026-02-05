package com.ecommerce.project.Service;
import com.ecommerce.project.Payload.CategoryDTO;
import com.ecommerce.project.Payload.CategoryResponse;

public interface CategoryService {
    CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize ,  String sortBy , String sortOrder);
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    //String  updateCategory(Long categoryId , String categoryName);

    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId);

    CategoryDTO deleteCategory(Long categoryId);
}
