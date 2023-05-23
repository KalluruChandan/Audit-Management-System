import { Component, OnInit } from '@angular/core';
import { LoginService } from '../login.service';
import { Result } from '../models/Result';

@Component({
  selector: 'app-audit-responses',
  templateUrl: './audit-responses.component.html',
  styleUrls: ['./audit-responses.component.css']
})
export class AuditResponsesComponent implements OnInit {

  responses:Result[]=[]

  currentUser:any = localStorage.getItem("user")
 

  constructor(private loginService:LoginService) {
     this.loginService.getResponses().subscribe(
       (res)=>{
          this.responses=res.filter((response)=>response.managerName === this.currentUser)
     })

   }

  

  ngOnInit(): void {
  }

}
