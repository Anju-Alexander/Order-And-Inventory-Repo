package com.korber.inventory.repository;

import com.korber.inventory.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    //sort by expiry date
    List<Inventory> findByProductIdOrderByExpiryDateAsc(Long ProductID);
}
