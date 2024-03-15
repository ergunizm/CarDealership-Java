package com.ergun.vehicleDealership.dataAccess.abstracts;

import com.ergun.vehicleDealership.entities.userEntities.Seller;
import com.ergun.vehicleDealership.entities.userEntities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository  extends JpaRepository<Seller, Long> {
    Seller findByUser(User user);
    boolean existsByUser(User user);
}
