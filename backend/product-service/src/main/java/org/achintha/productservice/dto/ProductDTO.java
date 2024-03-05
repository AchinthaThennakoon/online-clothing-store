package org.achintha.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long id;
    private String name;
    private String category1;
    private String category2;
    private String category3;
    private Double price;
    private Long quantity;
    private Date createTime;
    private List<InventoryDTO> inventoryDTOList;

    //search options
    private Double minPrice;
    private Double maxPrice;
    private String color;

}
