package com.diorama.shop.repository;

import com.diorama.shop.model.Address;
import com.diorama.shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByUser(User user);
    List<Address> findByUserId(Long userId);
    Address findByUserIdAndIsDefaultTrue(Long userId);
}