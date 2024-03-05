package org.achintha.productservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category1;
    private String category2;
    private String category3;
    private Double price;
    private Date createTime;

    @ManyToMany(mappedBy = "products")
    private Set<Supplier> suppliers = new HashSet<>();

    @OneToMany(mappedBy = "product")
    private List<Inventory> inventories;
}
