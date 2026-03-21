package com.ecommerce.project.Payload;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data

@AllArgsConstructor
public class ProductDTO {
    @JsonCreator
    public ProductDTO() {

    }
    private Long productId;
    private String productName;
    private String image;
    private Integer quantity;
    private double price;
    private double discount;
    private double specialPrice;
}
