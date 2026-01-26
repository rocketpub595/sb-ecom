package com.ecommerce.project.Service;
import com.ecommerce.project.Model.Category;

import java.util.List;


public interface CategoryService {
    List<Category> getAllCategories();
    void createCategory(Category category);
    //String  updateCategory(Long categoryId , String categoryName);

    Category updateCategory(Category category, Long categoryId);

    String deleteCategory(Long categoryId);
}
