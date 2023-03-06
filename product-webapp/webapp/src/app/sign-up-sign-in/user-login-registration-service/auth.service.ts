import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { LoginService } from './login.service';
// import jwtDecode, * as JWT from 'jwt-decode';
import jwt_decode from "jwt-decode";
import {Router} from '@angular/router';
// import {LoginDetails} from '../login.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private loginService: LoginService, private _snackBar: MatSnackBar, private _router:Router) { }


  public setToken(jwtToken: string) {
    localStorage.setItem("Token", jwtToken);
  }

  public getToken(): string | null {
    return localStorage.getItem("Token");
  }

  public clear() {
    localStorage.clear();
  }
  isLoggedIn:boolean=false;
  validateUser(customer: any) {
    this.loginService.get(customer).subscribe(
      (response: any) => {
        console.log(response);
          let token = response.Token;
          localStorage.setItem("email",response.emailId);
          let decoded = jwt_decode(token);
          console.log(decoded);
       console.log(response.Token)
        if (response.Token === null || response.Token === undefined || response.Token === '') {
          this._snackBar.open('Invalid Credential!!', 'FAILED', {
            duration: 5000,
            panelClass: ['mat-primary', 'mat-warn'],
          });
        } else {
          this.setToken(response.Token);
          if(response.role === 'Vendor'){
              console.log(response.role);
              this.isLoggedIn=true;
                this._router.navigateByUrl("/vsidenav")
                // nevigate krna h vendor dashboard
            }
          else if (response.role ==='User'){
            console.log(response.role);
            this.isLoggedIn=true;
            this._router.navigateByUrl("/usidenav/userDashboard")
              // nevigate to customer dashboard
            }
          else{
            alert("Invalid Role")
          }
        }
      },
      (error: any) => {
        this._snackBar.open('Email or Password is Wrong', 'FAILED', {
          duration: 5000,
          panelClass: ['mat-primary', 'mat-warn'],
        });
      });

  }
}
