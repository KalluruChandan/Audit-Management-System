import { Component, Host, OnInit } from '@angular/core';
import { TestBed } from '@angular/core/testing';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';
import { Result } from '../models/Result';
import { Test } from '../models/Test';
import { ValidateResponse } from '../models/ValidatedResponse';

@Component({
  selector: 'app-result',
  templateUrl: './result.component.html',
  styleUrls: ['./result.component.css']
})
export class ResultComponent implements OnInit {
    
  result:Result={
    auditId:1,
    projectName:"",
    managerName:"",
    projectExecutionStatus:"",
    remedialActionDuration:""
  }
  currentUser:string = "";

  constructor(private router:Router,private loginservice:LoginService) {
    console.log("Inside result Component")
    this.currentUser = loginservice.getUsername();
    this.loginservice.getFinalRes().subscribe(
      (res)=>{
        console.log(res)
        this.result = res
      }
       ,(err)=>{
        //  localStorage.removeItem("token");
        //  localStorage.removeItem("user");
        this.router.navigate(['audittype'])
       }
    )
  }

  ngOnInit(): void {
  }

  logout(){
    localStorage.removeItem("token")
    localStorage.removeItem("questions")
    localStorage.removeItem("type")
    // localStorage.removeItem("result")
    this.router.navigate(['login'])
  }

  goBackToChecklist(){
    localStorage.removeItem("questions")
    localStorage.removeItem("type")
    this.router.navigate(['/audittype'])
  }

  backToHome(){
    localStorage.removeItem("questions")
    localStorage.removeItem("type")
  }
  

}
