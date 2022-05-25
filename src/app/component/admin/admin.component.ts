import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { Produit } from 'src/app/models/produit';
import { ProduitService } from 'src/app/services/guest/produit.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  idc;
  imgUrl = "http://localhost:8080/guest/getImage/";
  
  produits :Produit[] = [];
  constructor(private produitService: ProduitService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.route.paramMap.subscribe((params: ParamMap) =>
    {
      this.idc =+ params.get("idc");

      if (this.idc != 0) {
        this.produitService.getProduitByCategorie(this.idc).subscribe(
          res => {
            this.produits = res;
          }
        )
      }
     
    } 
    
  )
    if (this.idc) {
      this.produitService.getProduitByCategorie(this.idc).subscribe(
        res =>{
          this.produits = res;
        }
      )
    }else{
      this.getProducts();
    }
  }


  getProducts(): void {
    this.produitService.getProducts().subscribe(
      res=>{
        this.produits = res;        
      }
    )
  }

  openForm(id ?: number){
    if (id) {
      this.router.navigate(["/admin/product/" + id]);
    }else{
      this.router.navigate(["/admin/product"]);
    }
  }

  onDelete(produit: Produit){
    this.produitService.deleteProduit(produit.id).subscribe(
      () => {
        this.getProducts();
      }
    )
  }

  searchProduits(mc: string){
    if (mc == "") {
      this.getProducts()
    }else{
      this.produitService.getProduitBymotCle(mc).subscribe(
        res => {
          this.produits = res;
        }
      )
    }
    
  }
  openCategorieForm(){
    this.router.navigate(["/admin/categories"]);
  }
}
