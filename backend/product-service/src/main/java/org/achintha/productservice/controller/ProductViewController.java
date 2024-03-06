package org.achintha.productservice.controller;

import org.achintha.productservice.dto.PaginatedResponse;
import org.achintha.productservice.dto.ProductDTO;
import org.achintha.productservice.service.ProductViewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product/")
public class ProductViewController {

    private final ProductViewService productViewService;

    public ProductViewController(ProductViewService productViewService) {
        this.productViewService = productViewService;
    }


    @PostMapping("getAllProducts")
    public ResponseEntity<PaginatedResponse<ProductDTO>> view(@RequestBody(required = false) Object requestBody,
                                                  @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size,
                                                  @RequestParam(defaultValue = "createTime,desc") String[] sort,
                                                  @RequestParam(defaultValue = "false") boolean search){

        PaginatedResponse<ProductDTO> productDTOS = productViewService.getProducts(requestBody,page,size,sort,search);
        return new ResponseEntity<>(productDTOS, HttpStatus.OK);
    }
}
