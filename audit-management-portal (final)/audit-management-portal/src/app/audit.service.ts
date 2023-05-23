import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuditService {

  url:string=" http://localhost:8072/AuditCheckListQuestions";

  constructor(private httpClient:HttpClient) { 
    // this.httpClient.get("");
  }
  
}

