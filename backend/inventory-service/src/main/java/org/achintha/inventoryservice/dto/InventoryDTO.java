package org.achintha.inventoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDTO {
    private Long inventoryId;
    private Long productId;
    private String name;
    private Long colorId;
    private String colorName;
    private String size;
    private Long quantity;

}
