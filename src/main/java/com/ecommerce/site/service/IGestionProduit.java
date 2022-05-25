package com.ecommerce.site.service;


import com.ecommerce.site.entity.Produit;

import java.util.List;


public interface IGestionProduit {
	void addProduit(Produit p);
	void modifier(Produit p);
	void delete(int id);
	List<Produit> getAllProduit();
	List<Produit> getAllProduitByCatId(int id);
	Produit getProduitById(int id);
	List<Produit> getProduitsByKeyWord(String key);
	
	
}
