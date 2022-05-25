package com.ecommerce.site.service;


import com.ecommerce.site.entity.Categorie;

import java.util.List;


public interface IGestionCategrie {
	List<Categorie> getCategories();
	Categorie findCategorie(int id);
	void addCategorie(Categorie c);
	void deleteCategorie(int id);

    List<Categorie> getCategorieByKeyWord(String key);

	Categorie getCategorieById(int id);

	void editCat(Categorie c);
}
