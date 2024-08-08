package org.achintha.inventoryservice.controller;

import org.achintha.inventoryservice.dto.InventoryDTO;
import org.achintha.inventoryservice.dto.PaginatedResponse;
import org.achintha.inventoryservice.service.InventoryMgtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory/InventoryMgt/")
public class InventoryMgtController {

    private final InventoryMgtService inventoryMgtService;

    public InventoryMgtController(InventoryMgtService inventoryMgtService) {
        this.inventoryMgtService = inventoryMgtService;
    }


    @PostMapping("/view")
    public ResponseEntity<?> getAll(@RequestBody(required = false) Object requestBody,
                                         @RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size,
                                         @RequestParam(defaultValue = "createTime,desc") String[] sort,
                                         @RequestParam(defaultValue = "false") boolean search){

        PaginatedResponse<InventoryDTO> paginatedResponse = inventoryMgtService.getAll(requestBody,page,size,sort,search);
        return new ResponseEntity<>(paginatedResponse, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addItem(){
        return new ResponseEntity<>("hello", HttpStatus.OK);
    }

    @PostMapping("/update/{itemId}")
    public ResponseEntity<String> updateItem(@PathVariable Long itemId){
        return new ResponseEntity<>("hello", HttpStatus.OK);
    }

    @PostMapping("/delete/{itemId}")
    public ResponseEntity<String> deleteItem(@PathVariable Long itemId){
        return new ResponseEntity<>("hello", HttpStatus.OK);
    }

    @PostMapping("/get/{itemId}")
    public ResponseEntity<String> getItemById(@PathVariable Long itemId){
        return new ResponseEntity<>("hello", HttpStatus.OK);
    }
}
