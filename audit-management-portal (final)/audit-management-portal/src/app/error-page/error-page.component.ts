import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-error-page',
  templateUrl: './error-page.component.html',
  styleUrls: ['./error-page.component.css']
})
export class ErrorPageComponent implements OnInit {

  constructor() { 
    console.log("Inside error page Component")
    localStorage.removeItem("token")
    localStorage.removeItem("user")
    localStorage.removeItem("questions")
    localStorage.removeItem("type")
  }

  ngOnInit(): void {
  }

}
