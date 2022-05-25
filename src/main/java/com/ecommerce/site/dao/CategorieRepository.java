package com.ecommerce.site.dao;
import com.ecommerce.site.entity.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CategorieRepository extends JpaRepository<Categorie, Integer> {
    List<Categorie> findByNomContains(String key);
}
