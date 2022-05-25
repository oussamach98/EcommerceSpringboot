package com.ecommerce.site.dao;

import com.ecommerce.site.entity.Cart;
import com.ecommerce.site.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    public List<Cart> findByUser(User user);

    long countByUser(User user);
}
