import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { JwtModule } from "@auth0/angular-jwt";
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './login/login.component';
import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';
import { AuditTypeComponent } from './audit-type/audit-type.component';
import { AuditQuestionsComponent } from './audit-questions/audit-questions.component';
import { FooterComponent } from './footer/footer.component';
import { HttpClientModule } from '@angular/common/http';
import { ResultComponent } from './result/result.component';
import { ErrorPageComponent } from './error-page/error-page.component';
import { LogoutComponent } from './logout/logout.component';
import { AuditResponsesComponent } from './audit-responses/audit-responses.component';


export function tokenGetter() {
  return localStorage.getItem("token");
}

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HeaderComponent,
    HomeComponent,
    AuditTypeComponent,
    AuditQuestionsComponent,
    FooterComponent,
    ResultComponent,
    ErrorPageComponent,
    LogoutComponent,
    AuditResponsesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: tokenGetter,
        allowedDomains: ["http://localhost:4200/"]
      },
    }),
  ],
  providers: [],
  bootstrap: [AppComponent]
})

export class AppModule {

  
 }
