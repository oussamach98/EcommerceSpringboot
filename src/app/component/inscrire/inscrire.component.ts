import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { UserAuthService } from 'src/app/services/user-auth/user-auth.service';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-inscrire',
  templateUrl: './inscrire.component.html',
  styleUrls: ['./inscrire.component.css']
})
export class InscrireComponent implements OnInit {

  constructor(private userService : UserService, private userAuth: UserAuthService, private router: Router) { }

  ngOnInit() {
  }

  logup(loginForm : NgForm){
    const user : User = loginForm.value;  
    this.userService.logup(user).subscribe(
      (response: any) => { 
        
        this.router.navigate(['/login']);
        
      },
      (error)=>{
        console.log(error);
        
      }
    )
  }

}
