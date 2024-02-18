package org.achintha.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long id;
    private String name;
    private String category;
    private Double price;
    private Long quantity;
    private Date createTime;

    //search options
    private Double minPrice;
    private Double maxPrice;
}
