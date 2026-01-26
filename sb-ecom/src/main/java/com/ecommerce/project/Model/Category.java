package com.ecommerce.project.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Categories")
@Data
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long categoryId;

    @NotBlank
    @Size(min = 5 , message = "Category name must contain 5 Characters")
    private String categoryName;

    //I do not think this constructor was in the tutorial
//    public Category(long categoryId, String categoryName) {
//        this.categoryId = categoryId;
//        this.categoryName = categoryName;
//    }


//    public Category() {
//    }
//
//    public long getCategoryId() {
//        return categoryId;
//    }
//
//    public void setCategoryId(long categoryId) {
//        this.categoryId = categoryId;
//    }
//
//    public String getCategoryName() {
//        return categoryName;
//    }
//
//    public void setCategoryName(String categoryName) {
//        this.categoryName = categoryName;
//    }
}
