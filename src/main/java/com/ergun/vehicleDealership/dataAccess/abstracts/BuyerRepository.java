package com.ergun.vehicleDealership.dataAccess.abstracts;

import com.ergun.vehicleDealership.entities.userEntities.Buyer;
import com.ergun.vehicleDealership.entities.userEntities.User;
import com.ergun.vehicleDealership.entities.vehicleEntities.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Long> {
    List<Buyer> findByLookingType(Type type);
    boolean existsByUser(User user);
    Buyer findByUser(User user);
}
