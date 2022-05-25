package com.ecommerce.site.controller;

import com.ecommerce.site.entity.Categorie;
import com.ecommerce.site.entity.Produit;
import com.ecommerce.site.entity.User;
import com.ecommerce.site.service.IGestionCategrie;
import com.ecommerce.site.service.IGestionProduit;
import com.ecommerce.site.service.JwtService;
import com.ecommerce.site.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
public class GuestController {
    @Autowired
    IGestionProduit gestionProduit;

    @Autowired
    IGestionCategrie gestionCategrie;

    @Autowired
    private UserService userService;



//    @PostConstruct
//    public void initRoleAndUser() {
//        userService.initRoleAndUser();
//    }

    @PostMapping({"/registerNewUser"})
    public User registerNewUser(@RequestBody User user) throws Exception{
        return userService.registerNewUser(user);
    }


    @GetMapping("/guest/products")
    public List<Produit> getAllProduct()
    {
        return gestionProduit.getAllProduit();
    }

    @GetMapping("/guest/cherche/{mc}")
    public List<Produit> cherche(@PathVariable("mc") String mc){
        return gestionProduit.getProduitsByKeyWord(mc);
    }

    @GetMapping("/guest/getProduit/{id}")
    public Produit getProduit(@PathVariable("id") int id){
        return gestionProduit.getProduitById(id);
    }

    //get Images url
    @GetMapping(path = "/guest/getImage/{imgName}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImage(@PathVariable("imgName") String imgName) throws IOException {

        File f = new File(System.getProperty("user.home"));
        Path p = Paths.get(f + "/Downloads/uploads/" + imgName);
        return Files.readAllBytes(p);
    }
    @GetMapping("/guest/categories")
    public List<Categorie> getAllCategories()
    {
        return gestionCategrie.getCategories();
    }
    @GetMapping("/guest/getCategorie/{id}")
    public Categorie getCategorie(@PathVariable("id") int id){
        return gestionCategrie.getCategorieById(id);
    }

    @GetMapping("/guest/geProduitByCategorie/{id}")
    public List<Produit> getProduitByCategorie(@PathVariable("id") int id){
        return gestionProduit.getAllProduitByCatId(id);
    }
    @GetMapping("/guest/chercheCat/{mc}")
    public List<Categorie> chercheCategorie(@PathVariable("mc") String mc){
        return gestionCategrie.getCategorieByKeyWord(mc);
    }

}
