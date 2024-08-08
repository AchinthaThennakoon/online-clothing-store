package org.achintha.inventoryservice.repository;

import org.achintha.inventoryservice.model.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ColorRepository extends JpaRepository<Color,Long>, JpaSpecificationExecutor<Color> {
}
