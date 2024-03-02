package org.achintha.productservice.service;

import org.achintha.productservice.dto.ProductDTO;

import java.util.List;

public interface ProductViewService {
    List<ProductDTO> getProducts(Object requestBody, int page, int size, String[] sort, boolean search);
}
