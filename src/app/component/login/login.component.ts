import { Component, OnInit } from '@angular/core';
import { Form, NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { UserAuthService } from 'src/app/services/user-auth/user-auth.service';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  msg: string = "";

  constructor(private userService: UserService, private userAuth: UserAuthService, private router: Router) { }

  ngOnInit(): void {
    this.getReturnUrl();
  }

  getReturnUrl(): void{
    const userRoles: any = this.userAuth.getRoles();
    
    if (this.userAuth.isLoggedIn()) {
      if (userRoles[0].roleName === 'Super_Admin') {
        this.router.navigate(['/admin']);
      }else{
        this.router.navigate(['/home']);
      }
    }
  }

  login(form : NgForm){
    this.userService.login(form.value).subscribe(
      (response: any) => {
        this.userAuth.setRoles(response.user.role);
        this.userAuth.setToken(response.jwtToken);
        this.userAuth.setIdUser(response.user.id);
        const role = response.user.role[0]['roleName'];
        if (role === 'Admin') {
          this.router.navigate(['/admin']);
        }else{
          this.router.navigate(['/home']);
        }
        
      },
      (error)=>{
        console.log(error);
      }
    )
    
  }
}
