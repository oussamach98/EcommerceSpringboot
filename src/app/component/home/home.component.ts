import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { Cart } from 'src/app/models/Cart';
import { Produit } from 'src/app/models/produit';
import { CartService } from 'src/app/services/cart/cart.service';
import { ProduitService } from 'src/app/services/guest/produit.service';
import { UserAuthService } from 'src/app/services/user-auth/user-auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  idc;
  mc: string;
  products: Produit[];
  cart: Cart;
  
  imgUrl = "http://localhost:8080/guest/getImage/";
  numCart: number;
  msg: string = "";
  constructor(private produitService: ProduitService, private ar: ActivatedRoute, private cartService: CartService, private userAuth: UserAuthService) { }

  ngOnInit() {
    this.ar.paramMap.subscribe((params: ParamMap) =>
    {
      this.mc = params.get("mc");
      this.idc =+ params.get("idc");

      if (this.idc != 0) {
        this.filterByCategorys();

      }else{
        this.searchProduits(this.mc);
      }
      
    })
    if (this.mc == undefined && this.idc == 0) {
      this.getProduits();
    }
  }

  getProduits(){
    this.produitService.getProducts().subscribe(
      res => {
        this.products = res;
      }
    )
  }
  searchProduits(mc: string){
    if (mc == "") {
      this.getProduits()
    }else{
      this.produitService.getProduitBymotCle(mc).subscribe(
        res => {
          this.products = res;
        }
      )
    }
    
  }

  filterByCategorys(){
    this.produitService.getProduitByCategorie(this.idc).subscribe(
      res => {
        this.products = res;
      }
    )
  }
  addToCart(prodId: number){
    this.cartService.addCartsByUser(+this.userAuth.getIdUser(), prodId, this.cart).subscribe(
      () =>{
        this.getNumberOftheCart();
        this.msg = "Ajout réussi";
      }
    )
  }

  getNumberOftheCart() {
    this.cartService.getNombreDeCartsByUser(+this.userAuth.getIdUser()).subscribe(
      res=>{
        this.numCart = res;
      }
    )
  }
}
