package com.ecommerce.site;

import com.ecommerce.site.dao.CartRepository;
import com.ecommerce.site.entity.Cart;
import com.ecommerce.site.entity.Produit;
import com.ecommerce.site.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class ShoppingCartTest {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void tesAddOneCart(){
        Produit produit = testEntityManager.find(Produit.class, 4);
        User user = testEntityManager.find(User.class, 2);

        Cart cart = new Cart();
        cart.setProduit(produit);
        cart.setUser(user);
        cart.setQuantity(5);
        Cart savedCart =  cartRepository.save(cart);

        assert(savedCart.getId() > 0);
    }

    @Test
    public void getCartByUser(){
        User user = new User();
        user.setId(2);

        List<Cart> carts = cartRepository.findByUser(user);
        assert(carts.size() > 0);
    }
}
