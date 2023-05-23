//import { Token } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';
import { LoginComponent } from '../login/login.component';
import { AuthRequest } from '../models/AuthRequest';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

currentUser:any = "";


  constructor(public router:Router,public loginService: LoginService) { }

  ngOnInit(): void {
  }

  get userInfo(): any{
  //  this.currentUser= this.loginService.getUsername();
    this.currentUser=localStorage.getItem("user")
    var s=localStorage.getItem("token")
    //this.loginService.getLoginStatus
    return s
  }

  // get isLoggedIn(): any{
  // return this.loginService.getLoginStatus
  // }

  logout(){
    localStorage.removeItem("token")
    localStorage.removeItem("user")
    localStorage.removeItem("type")
    localStorage.removeItem("questions")

    this.currentUser="";
    this.userInfo.length=0
    this.router.navigate(['login'])
  }

}
