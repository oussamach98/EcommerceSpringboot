package com.ecommerce.site.dao;

import com.ecommerce.site.entity.Categorie;
import com.ecommerce.site.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProduitRepository extends JpaRepository<Produit, Integer> {
    List<Produit> findByNameContains(String key);

    List<Produit> getByCategorie(Categorie categorie);
}
