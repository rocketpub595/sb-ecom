package com.ecommerce.project.Service;

import com.ecommerce.project.Model.Product;
import com.ecommerce.project.Payload.ProductDTO;
import com.ecommerce.project.Payload.ProductResponse;

public interface ProductService {
    ProductDTO addProduct(Long categoryId, Product product);

    ProductResponse getAllProducts();

    ProductResponse searchbyCategory(Long categoryId);

    ProductResponse searchProductByKeyword(String keyword);
}
