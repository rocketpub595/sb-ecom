package com.ecommerce.project.Service;

import com.ecommerce.project.Exceptions.APIExceptions;
import com.ecommerce.project.Exceptions.ResourceNotFoundException;
import com.ecommerce.project.Model.Category;
import com.ecommerce.project.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if(categories.isEmpty()){
            throw new APIExceptions("Category Empty. Please add at least one category");
        }
        return categories;
    }

    @Override
    public void createCategory(Category category) {
        Category savedCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if(savedCategory != null){
            throw new APIExceptions("Category "+category.getCategoryName()+" already exists");
        }
        categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        Category savedCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category" , "CategoryId",  categoryId));

        category.setCategoryId(categoryId);
        savedCategory = categoryRepository.save(category);
        return savedCategory;
    }

    @Override
    public String deleteCategory(Long categoryId) {
        List<Category> categories = categoryRepository.findAll();
        Category category = categories.stream().filter(c->c.getCategoryId() == (categoryId))
                .findFirst()
                .orElseThrow(() ->new ResourceNotFoundException("Category" , "categoryId" , categoryId));

        categoryRepository.delete(category);
        return "Category with Category Id "+categoryId+" has been deleted";
    }
}