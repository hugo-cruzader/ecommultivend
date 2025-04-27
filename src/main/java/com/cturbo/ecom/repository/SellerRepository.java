package com.cturbo.ecom.repository;

import com.cturbo.ecom.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {
    Seller findByEmail(final String email);
}
