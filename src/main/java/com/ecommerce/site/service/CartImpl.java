package com.ecommerce.site.service;

import com.ecommerce.site.dao.CartRepository;
import com.ecommerce.site.entity.Cart;
import com.ecommerce.site.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartImpl implements ICart{


    @Autowired
    private CartRepository  cartRepository;

    @Override
    public List<Cart> cartListe(User u) {
        return cartRepository.findByUser(u);
    }

    @Override
    public void addCart(Cart c) {
        cartRepository.save(c);
    }

    @Override
    public void deleteCart(int id) {
        cartRepository.deleteById(id);
    }

    @Override
    public long count(User user){
        return cartRepository.countByUser(user);
    }
}
