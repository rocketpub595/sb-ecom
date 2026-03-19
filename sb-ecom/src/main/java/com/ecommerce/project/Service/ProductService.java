package com.ecommerce.project.Service;

import com.ecommerce.project.Model.Product;
import com.ecommerce.project.Payload.ProductDTO;

public interface ProductService {
    ProductDTO addProduct(Long categoryId, Product product);
}
