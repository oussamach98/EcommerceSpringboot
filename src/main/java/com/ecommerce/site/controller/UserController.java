package com.ecommerce.site.controller;

import com.ecommerce.site.entity.Cart;
import com.ecommerce.site.entity.Produit;
import com.ecommerce.site.entity.User;
import com.ecommerce.site.service.CartImpl;
import com.ecommerce.site.service.ICart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasRole('User')")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ICart cart;


    @GetMapping("/cart/getCartByUser/{userId}")
    public List<Cart> getCartByUser(@PathVariable("userId") int userId){
        User user = new User();
        user.setId(userId);
        return cart.cartListe(user);
    }

    @PostMapping("/cart/add/{userId}/{prodId}")
    public void addToCart(@PathVariable("userId") int userId, @PathVariable("prodId") int prodId){
        Cart c = new Cart();
        User user = new User();
        user.setId(userId);
        Produit p = new Produit();
        p.setId(prodId);

        c.setUser(user);
        c.setProduit(p);
        c.setQuantity(1);
        cart.addCart(c);
    }

    @DeleteMapping("/cart/delete/{id}")
    public void deleteCart(@PathVariable("id") int id){
        cart.deleteCart(id);
    }

    @PutMapping("/cart/update")
    public void updateCart(@RequestBody Cart c){
        cart.addCart(c);
    }
    @GetMapping("/cart/count/{userId}")
    public long countCarts(@PathVariable("userId") int userId){
        User user = new User();
        user.setId(userId);
        return cart.count(user);
    }




}
