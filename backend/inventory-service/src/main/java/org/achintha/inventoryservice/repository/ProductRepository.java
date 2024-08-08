package org.achintha.inventoryservice.repository;

import org.achintha.inventoryservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductRepository extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product> {
//    Page<Product> findByCategory1(String category1, Pageable pageable);
}
