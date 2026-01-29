package com.ecommerce.project.Service;
import com.ecommerce.project.Model.Category;
import com.ecommerce.project.Payload.CategoryDTO;
import com.ecommerce.project.Payload.CategoryResponse;

import java.util.List;


public interface CategoryService {
    CategoryResponse getAllCategories();
    void createCategory(CategoryDTO categoryDTO);
    //String  updateCategory(Long categoryId , String categoryName);

    Category updateCategory(Category category, Long categoryId);

    String deleteCategory(Long categoryId);
}
