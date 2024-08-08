package org.achintha.inventoryservice.service;

import org.achintha.inventoryservice.dto.InventoryDTO;
import org.achintha.inventoryservice.dto.PaginatedResponse;

public interface InventoryMgtService {
    PaginatedResponse<InventoryDTO> getAll(Object requestBody, int page, int size, String[] sort, boolean search);
}
