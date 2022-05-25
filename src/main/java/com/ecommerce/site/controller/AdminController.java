package com.ecommerce.site.controller;


import com.ecommerce.site.entity.Categorie;
import com.ecommerce.site.entity.Produit;
import com.ecommerce.site.service.IGestionCategrie;
import com.ecommerce.site.service.IGestionProduit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@RestController
@PreAuthorize("hasRole('Admin')")
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    IGestionCategrie iGestionCategrie;

    @Autowired
    IGestionProduit gestionProduit;

    //define a location
    public static final String DIRECTORY = System.getProperty("user.home") + "/Downloads/uploads";

    @PostMapping("/add")
    public void addProduit(@RequestBody Produit p){
        gestionProduit.addProduit(p);
    }

    @PostMapping("/addCat")
    public void addCategorie(@RequestBody Categorie c){
        iGestionCategrie.addCategorie(c);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") int id){
        gestionProduit.delete(id);
    }

    @PutMapping("/edit")
    public void editProduit(@RequestBody Produit p){
        gestionProduit.addProduit(p);
    }

    @PutMapping("/editCat")
    public void editCategorie(@RequestBody Categorie c){
        iGestionCategrie.editCat(c);
    }


    //Categories
    @DeleteMapping("/deleteCat/{id}")
    public void deleteCategorie(@PathVariable("id") int id){
        iGestionCategrie.deleteCategorie(id);
    }


    //method to uploads files
    @PostMapping("/upload")
    public ResponseEntity<List<String>> uploadFiles(@RequestParam("files") MultipartFile file)
            throws IOException {
        List<String> filenames = new ArrayList<>();
        //get file name
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        //get the location
        Path fileStorage = get(DIRECTORY, filename).toAbsolutePath().normalize();
        //save file in the location line(35)
        Files.copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
        filenames.add(filename);

        return ResponseEntity.ok().body(filenames);
    }
}
