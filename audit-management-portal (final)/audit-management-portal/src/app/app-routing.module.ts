import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuditQuestionsComponent } from './audit-questions/audit-questions.component';
import { AuditResponsesComponent } from './audit-responses/audit-responses.component';
import { AuditTypeComponent } from './audit-type/audit-type.component';
import { ErrorPageComponent } from './error-page/error-page.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { ResultComponent } from './result/result.component';
import { TestGuard } from './test.guard';


const routes: Routes = [
  {path:'',component:LoginComponent},
  {path:'login',component:LoginComponent},
  {path:'home',component:HomeComponent, canActivate:[TestGuard]},
  {path:'audittype',component:AuditTypeComponent,canActivate:[TestGuard]},
  {path:'auditQuestions',component:AuditQuestionsComponent,canActivate:[TestGuard]},
  {path:'results',component:ResultComponent,canActivate:[TestGuard]},
  {path:'responses',component:AuditResponsesComponent,canActivate:[TestGuard]},
  {path:'error',component:ErrorPageComponent},
  {path:'**',component:ErrorPageComponent}
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers:[TestGuard]
})
export class AppRoutingModule { }
