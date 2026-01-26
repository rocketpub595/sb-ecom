package com.ecommerce.project.Controller;
import java.util.*;

import com.ecommerce.project.Model.Category;
import com.ecommerce.project.Service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/api") if i Use this I will no longer have the need to put /api in any of the Endpoint URLs
//like @PostMapping("/public/categories/{category})
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    //@GetMapping("/api/public/categories")
    @RequestMapping(value = "/api/public/categories", method = RequestMethod.GET)
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categories = categoryService.getAllCategories();
        return  new ResponseEntity<>(categories , HttpStatus.OK);
    }

    @PutMapping("/api/admin/categories/{categoryId}")
    @RequestMapping(value = "/api/admin/categories/{categoryId}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateCategory(@PathVariable Long categoryId,@Valid @RequestBody Category NewCategory){
            String status = String.valueOf(categoryService.updateCategory( NewCategory ,categoryId));
            return new ResponseEntity<>(status ,HttpStatus.OK);

    }
    @PostMapping("/api/public/categories")
    public ResponseEntity<String> CreateCategory(@Valid @RequestBody Category category){
        categoryService.createCategory(category);
        return new ResponseEntity<>("Category Added Successfully ",HttpStatus.CREATED);
    }

    @DeleteMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<String> DeleteCategory(@PathVariable Long categoryId){
            String status = categoryService.deleteCategory(categoryId);
            return new  ResponseEntity<>(status, HttpStatus.OK);
    }
}
