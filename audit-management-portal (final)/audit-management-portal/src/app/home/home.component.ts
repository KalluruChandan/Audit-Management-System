import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  currentUser:string = "";

  constructor(private router:Router,private loginService:LoginService) {
    console.log("Inside home Component") 
      // loginService.getValidatationResponse().subscribe(
      //   (res)=>{}
      //   ,(err)=>{
      //     router.navigate(['error'])
      //   }
      // )
      console.log("home component")
      this.currentUser = this.loginService.getUsername()
  }

  getAuditType(){
    this.router.navigate(['audittype']);
  }
  logout(){
    localStorage.removeItem("token")
    this.router.navigate(['login'])
  }

  ngOnInit(): void {
    console.log("home component 2")
  }
  

}
