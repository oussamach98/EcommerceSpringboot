package com.ecommerce.site.service;

import com.ecommerce.site.dao.CategorieRepository;
import com.ecommerce.site.dao.ProduitRepository;
import com.ecommerce.site.entity.Categorie;
import com.ecommerce.site.entity.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GestionCategoryIml implements IGestionCategrie {


    CategorieRepository cr;
    public GestionCategoryIml(CategorieRepository cr){
        this.cr = cr;
    }

    @Autowired
    ProduitRepository pr;

    @Override
    public List<Categorie> getCategories() {
        return cr.findAll();
    }

    @Override
    public Categorie findCategorie(int id) {
        return cr.findById(id).get();
    }

    @Override
    public void addCategorie(Categorie c) {
        cr.save(c);
    }

    @Override
    public void deleteCategorie(int id) {
        Categorie categorie = cr.findById(id).get();
        //List<Produit> produits = pr.getByCategorie(categorie);
        if (categorie.getProduits() != null){
            for (Produit p: categorie.getProduits()) {
                p.setCategorie(null);
                //pr.save(p);
            }
        }

        cr.delete(categorie);
    }

    @Override
    public List<Categorie> getCategorieByKeyWord(String key) {
        return cr.findByNomContains(key);
    }

    @Override
    public Categorie getCategorieById(int id) {
        return cr.findById(id).get();
    }

    @Override
    public void editCat(Categorie c) {
        cr.save(c);
    }
}
