import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';
import { Question } from '../models/Questions';

@Component({
  selector: 'app-audit-type',
  templateUrl: './audit-type.component.html',
  styleUrls: ['./audit-type.component.css']
})
export class AuditTypeComponent implements OnInit {

  type:string = "Internal";
  question:Question[]=[];
  currentUser :string = "";
  
  constructor(private router:Router,private loginService:LoginService) {
  
    console.log("Inside Checklist Component")

    // loginService.getValidatationResponse().subscribe(
    //   (res)=>{}
    //   ,(err)=>{
    //     console.log("access error")
    //     router.navigate(['error'])
    //   }
    // )

    // console.log("")
    console.log(this.type)
    this.currentUser = loginService.getUsername()
  }

  ngOnInit(): void {
  }
  getAuditQuestions(){
    // console.log(this.type)
    localStorage.setItem("type",this.type)
    this.router.navigate(['auditQuestions']);
  }
  logout(){
    localStorage.removeItem("token")
    this.router.navigate(['login'])
  }

}
