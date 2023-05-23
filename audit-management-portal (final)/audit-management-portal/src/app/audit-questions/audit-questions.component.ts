import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';
import { Question } from '../models/Questions';
import { RequestHeaderForResult } from '../models/RequestHeaderForResult';
import { Result } from '../models/Result';
import { Test } from '../models/Test';
import { ValidateResponse } from '../models/ValidatedResponse';

@Component({
  selector: 'app-audit-questions',
  templateUrl: './audit-questions.component.html',
  styleUrls: ['./audit-questions.component.css']
})
export class AuditQuestionsComponent implements OnInit {
  
  question:Question[]=[];
  type:string = "";
  // date = new Date();

  private _responses: Question[] = [];

  public get responses(): Question[] {
    return this._responses;
  }


  result:Result={
    auditId:1,
    projectName:"",
    managerName:"",
    projectExecutionStatus:"",
    remedialActionDuration:""
  }

  // req:RequestHeaderForResult={
  //   projectname:"",
  //   managerName:"",
  //   auditDetail:{
  //     auditType:"",
  //     auditDate:"",
  //     auditQuestion:this.question
  //   }
  // }

  text :string="";
  message : string = ""; 

  test : Test ={
        projectName:"",
        managerName:"",
        auditDetail:{
            auditType:"",
            auditDate:"",
            auditQuestions:this.question
          
        }
    
  }
  currentUser:string = "";

  constructor(private router:Router,private loginService:LoginService) {
    console.log("Inside questions Component")

    this.currentUser = loginService.getUsername();
    this.type = this.type + localStorage.getItem("type");
    this.test.auditDetail.auditType = this.type
    this.loginService.getQuestions(this.type).subscribe(
      (res)=>{
        this.question = res
        this.test.auditDetail.auditQuestions = res
        localStorage.setItem("questions",JSON.stringify(this.question))
      }
      // ,
      // (err)=>{
      //   this.router.navigate(['error'])
      // }
    )
    this.loginService.getValidatationResponse().subscribe(
      (res)=>{
        
        // this.req.projectname = res.projectName
        // this.req.managerName = res.userName

        this.test.projectName = res.projectName
        this.test.managerName = res.userName

      }
    )
    console.log(this.question)
  }

  responseYes(i:number):void {
    this.question[i].response = "YES";
    console.log(this.question[i])
  }

  responseNo(i:number):void {
    this.question[i].response = "NO";
    console.log(this.question[i])
  }

  getResponse(responses: Question[]) : void {
    this._responses = responses;
    //this.sendResponse();
  }
  validated(questions: Question[]) {
    for(let q of questions){
      if(q.response!="YES" && q.response!="NO"){
        return false;
      }
    }
    this._responses=questions;
    return true;
  }

  returnResult(){
    return this.result;
  }

  ngOnInit(): void {
  }
  getResults(){
    // this.req.auditDetail.auditQuestion = this.question
    // console.log(this.req)
    if(this.validated(this.question)){
    this.loginService.setRequestBody(this.test);
    this.router.navigate(['/results'])
    }else{
      this.message = "Please answer all questions to submit!!";
    }
    // this.loginService.getFinalRes(this.test).subscribe(
    //   (res)=>{
    //     this.result = res
    //     console.log(res)
    //     this.text = JSON.stringify(res)
    //     localStorage.setItem("result",this.text)
    //   }
    // )
    // this.loginService.setRequestBody(this.test);
    
  }
  logout(){
    localStorage.removeItem("token")
    localStorage.removeItem("questions")
    localStorage.removeItem("type")
    this.router.navigate(['login'])
  }
  backToHome(){
    localStorage.removeItem("questions")
    localStorage.removeItem("type")
  }

}
