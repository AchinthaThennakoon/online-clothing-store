package org.achintha.inventoryservice.service.impl;

import jakarta.persistence.criteria.Predicate;
import org.achintha.inventoryservice.dto.InventoryDTO;
import org.achintha.inventoryservice.dto.PaginatedResponse;
import org.achintha.inventoryservice.model.Inventory;
import org.achintha.inventoryservice.repository.InventoryRepository;
import org.achintha.inventoryservice.service.InventoryMgtService;
import org.achintha.inventoryservice.util.Common;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryMgtServiceImpl implements InventoryMgtService {

    private final ModelMapper modelMapper;

    private final Common common;
    private final InventoryRepository inventoryRepository;

    public InventoryMgtServiceImpl(ModelMapper modelMapper, Common common, InventoryRepository inventoryRepository) {
        this.modelMapper = modelMapper;
        this.common = common;
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public PaginatedResponse<InventoryDTO> getAll(Object requestBody, int page, int size, String[] sort, boolean search) {

        Page<Inventory> inventories;
        Specification<Inventory> specification = null;
        List<InventoryDTO> inventoryDTOList = null;

        PaginatedResponse<InventoryDTO> response = new PaginatedResponse<>();
        response.setCurrentPage(page);

        //map request body to DTO
        if (search && requestBody!= null){

            InventoryDTO inventoryDTO = modelMapper.map(requestBody,InventoryDTO.class);

            //filtering
            specification = makeSpecification(inventoryDTO);
        }

        //paging,sorting
        List<Sort.Order> orders = common.getSort(sort);
        Pageable pageable = PageRequest.of(page,size, Sort.by(orders));


        //final result
        inventories = inventoryRepository.findAll(specification,pageable);

        if (!inventories.isEmpty()) {
            inventoryDTOList = mapProductResultToDTO(inventories);

            response.setData(inventoryDTOList);
            response.setTotalPages(inventories.getTotalPages());
            response.setTotalElements(inventories.getTotalElements());
        }

        return response;
    }

    private Specification<Inventory> makeSpecification(InventoryDTO inventoryDTO) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (inventoryDTO.getInventoryId()!=null){
                predicates.add(criteriaBuilder.equal(root.get("price"),inventoryDTO.getInventoryId()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private List<InventoryDTO> mapProductResultToDTO(Page<Inventory> inventories) {
        List<InventoryDTO> inventoryDTOList = new ArrayList<>();
        inventories.forEach(inventory -> {
            InventoryDTO inventoryDTO = new InventoryDTO();

            inventoryDTO.setInventoryId(inventory.getInventoryId());
            inventoryDTO.setProductId(inventory.getProduct().getId());
            inventoryDTO.setName(inventory.getProduct().getName());
            inventoryDTO.setSize(inventory.getSize().getSize());
            inventoryDTO.setColorId(inventory.getColor().getColorId());
            inventoryDTO.setColorName(inventory.getColor().getColorName());
            inventoryDTO.setQuantity(inventory.getQuantity());


            inventoryDTOList.add(inventoryDTO);

        });

        return  inventoryDTOList;
    }


}
