package org.achintha.productservice.service;

import org.achintha.productservice.dto.PaginatedResponse;
import org.achintha.productservice.dto.ProductDTO;

import java.util.List;

public interface ProductViewService {
    PaginatedResponse<ProductDTO> getProducts(Object requestBody, int page, int size, String[] sort, boolean search);
}
