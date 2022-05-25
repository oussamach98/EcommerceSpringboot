package com.ecommerce.site.service;

import com.ecommerce.site.entity.Cart;
import com.ecommerce.site.entity.User;

import java.util.List;

public interface ICart {

    List<Cart> cartListe(User u);
    void addCart(Cart c);
    void deleteCart(int id);
    long count(User user);
}
