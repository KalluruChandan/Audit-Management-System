import { HttpClient, HttpHeaders } from '@angular/common/http';
// import { Token } from '@angular/compiler';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
// import AuditDetail from './models/AuditDetail';
import { AuthRequest } from './models/AuthRequest';
import { Question } from './models/Questions';
// import { RequestHeaderForResult } from './models/RequestHeaderForResult';
import { Result } from './models/Result';
import { Test } from './models/Test';
import { ValidateResponse } from './models/ValidatedResponse';


@Injectable({
  providedIn: 'root'
})
export class LoginService {
  
  // url:string = "http://audit-management-lb-1392091543.eu-west-3.elb.amazonaws.com:8084/auth/authenticate";
  // urlForChecklist : string = "http://audit-management-lb-1392091543.eu-west-3.elb.amazonaws.com:8081/checklist/auditchecklistquestions";
  // urlForResult :string = "http://audit-management-lb-1392091543.eu-west-3.elb.amazonaws.com:8083/severity/projectexecutionstatus";
  // validationUrl:string = "http://audit-management-lb-1392091543.eu-west-3.elb.amazonaws.com:8084/auth/validate";
  // responseUrl:string="http://audit-management-lb-1392091543.eu-west-3.elb.amazonaws.com:8083/severity/responses";

  url:string = "http://localhost:8084/auth/authenticate";
  urlForChecklist : string = "http://localhost:8081/checklist/auditchecklistquestions";
  urlForResult :string = "http://localhost:8083/severity/projectexecutionstatus";
  validationUrl:string = "http://localhost:8084/auth/validate";
  responseUrl:string="http://localhost:8083/severity/responses";

  test1 : Test | null = null;

  tok : string = "";

  userName:string = "";

  constructor(private httpClient:HttpClient){}

  user:AuthRequest={
    userName:'',
    password:''
  }

  setRequestBody(test:Test){
    this.test1 = test
  }


  getUsername(){
    return this.userName
  }

  setUsername(username:string){
    this.userName = username
  }

  getJwtToken(username:string,password:string){
    this.user.userName = username
    this.user.password = password
    return this.httpClient.post(this.url,this.user,{ responseType : 'text'});
  }

  getQuestions(type:string):Observable<Question[]>{
    return this.httpClient.get<Question[]>(this.urlForChecklist+"/"+type,{headers:{'Authorization':'Bearer '+localStorage.getItem(("token"))}})
  }

  getValidatationResponse():Observable<ValidateResponse>{
    return this.httpClient.get<ValidateResponse>(this.validationUrl,{headers:{'Authorization':'Bearer '+localStorage.getItem(("token"))}})
  }

  getFinalRes():Observable<Result>{
    console.log(this.test1)
    return this.httpClient.post<Result>(this.urlForResult,this.test1,{headers:{'Authorization':'Bearer '+localStorage.getItem(("token"))}})
  }

  getResponses():Observable<Result[]>{
     return this.httpClient.get<Result[]>(this.responseUrl,{headers:{'Authorization':'Bearer '+localStorage.getItem(("token"))}})
  }

  // mockService():Observable<string>{
  //   if(localStorage.getItem(("token"))?.length!=0){
  //     return this.httpClient.get<string>(this.urlForCheck,{headers:{'Authorization':'Bearer '+localStorage.getItem(("token"))}});
  //   }
  //   else{
  //     return this.httpClient.get<string>(this.urlForCheck);
  //   }
  // }
}
