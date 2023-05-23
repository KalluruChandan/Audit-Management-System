
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm!:FormGroup;



  username:string = "";
  password:string = "";  
  message:string = "";
  tok:string = "";
  //private status: Status = new Status;

  constructor(private router:Router,private loginService:LoginService, private formBuilder: FormBuilder) { 
    console.log("Inside Login Component")
    
  }

 

  onLogIn(){
     
    console.log(this.username);
    this.loginService.setUsername(this.username);
    this.loginService.getJwtToken(this.username,this.password).subscribe(
      (res)=>{
        // console.log(res)
        this.tok = res
        localStorage.setItem("token",res)
        localStorage.setItem("user",this.username)
        this.router.navigate(['/home'])
      },
      (err)=>{
        this.message = "Invalid credentials!!!";
      }
    )
  }



  ngOnInit(): void {

    this.loginForm=this.formBuilder.group({
      username: ['', [Validators.required,Validators.minLength(3)]],
      password:['', [Validators.required,Validators.minLength(3)]]
    });
  }

}
