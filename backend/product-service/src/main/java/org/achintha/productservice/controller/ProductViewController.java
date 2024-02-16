package org.achintha.productservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/product/")
public class ProductViewController {

    @PostMapping("view")
    public ResponseEntity<Object> view(@RequestBody(required = false) Object requestBody,
                                       @RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size,
                                       @RequestParam(defaultValue = "createdtime,desc") String[] sort,
                                       @RequestParam(defaultValue = "false") boolean search){
        return null;
    }
}
