package org.achintha.productservice.repository;

import org.achintha.productservice.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SizeRepository extends JpaRepository<Size,String>, JpaSpecificationExecutor<Size> {
}
