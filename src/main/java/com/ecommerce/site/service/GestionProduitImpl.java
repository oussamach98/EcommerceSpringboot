package com.ecommerce.site.service;

import com.ecommerce.site.dao.CategorieRepository;
import com.ecommerce.site.dao.ProduitRepository;
import com.ecommerce.site.entity.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GestionProduitImpl implements IGestionProduit{


    ProduitRepository pr;

    public GestionProduitImpl(ProduitRepository pr){
        this.pr = pr;
    }
    @Autowired
    CategorieRepository cr;
    @Override
    public void addProduit(Produit p) {
        pr.save(p);
    }

    @Override
    public void modifier(Produit p) {
        pr.save(p);
    }

    @Override
    public void delete(int id) {
        pr.deleteById(id);
    }

    @Override
    public List<Produit> getAllProduit() {
        return pr.findAll();
    }

    @Override
    public List<Produit> getAllProduitByCatId(int id) {
        return pr.getByCategorie(cr.findById(id).get());
    }

    @Override
    public Produit getProduitById(int id) {
        return pr.findById(id).get();
    }

    @Override
    public List<Produit> getProduitsByKeyWord(String key) {
        return pr.findByNameContains(key);
    }
}
