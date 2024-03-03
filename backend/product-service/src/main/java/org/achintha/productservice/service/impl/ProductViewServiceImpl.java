package org.achintha.productservice.service.impl;

import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.achintha.productservice.dto.InventoryDTO;
import org.achintha.productservice.dto.ProductDTO;
import org.achintha.productservice.model.Inventory;
import org.achintha.productservice.model.Product;
import org.achintha.productservice.repository.ProductRepository;
import org.achintha.productservice.service.ProductViewService;
import org.achintha.productservice.util.Common;
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
public class ProductViewServiceImpl implements ProductViewService {

    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;

    private final Common common;

    public ProductViewServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, Common common) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.common = common;
    }


    @Override
    public List<ProductDTO> getProducts(Object requestBody, int page, int size, String[] sort, boolean search) {

        Page<Product> products;
        Specification<Product> specification = null;
        List<ProductDTO> productDTOList = new ArrayList<>();

        //map request body to DTO
        if (search && requestBody!= null){
            
            ProductDTO productDTO = modelMapper.map(requestBody,ProductDTO.class);

            //filtering
            specification = makeSpecification(productDTO);
        }

        //paging,sorting
        List<Sort.Order> orders = common.getSort(sort);
        Pageable pageable = PageRequest.of(page,size, Sort.by(orders));

        
        //final result
        products = productRepository.findAll(specification,pageable);

        products.forEach(product -> {
            ProductDTO productDTO = new ProductDTO();

            // todo:map requests
            productDTO.setId(product.getId());
            productDTO.setName(product.getName());
            productDTO.setPrice(product.getPrice());

            productDTO.setInventoryDTOList(mapProductToInventory(product.getInventories()));

            productDTOList.add(productDTO);
        });

        return productDTOList;
    }

    private List<InventoryDTO> mapProductToInventory(List<Inventory> inventories) {
        List<InventoryDTO> returnList = new ArrayList<>();
        inventories.forEach(inventory -> {
            InventoryDTO dto = new InventoryDTO();
            dto.setInventoryId(inventory.getInventoryId());
            dto.setSize(inventory.getSize().getSize());
            dto.setColorId(inventory.getColor().getColorId());
            dto.setColorName(inventory.getColor().getColorName());
            dto.setQuantity(inventory.getQuantity());

            returnList.add(dto);
        });

        return returnList;
    }

    private Specification<Product> makeSpecification(ProductDTO productDTO) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (productDTO.getMinPrice()!=null){
                predicates.add(criteriaBuilder.greaterThan(root.get("price"),productDTO.getMinPrice()));
            }
            if (productDTO.getMaxPrice()!=null){
                predicates.add(criteriaBuilder.lessThan(root.get("price"),productDTO.getMaxPrice()));
            }

            //todo: add filters for color,size,in stock



            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
