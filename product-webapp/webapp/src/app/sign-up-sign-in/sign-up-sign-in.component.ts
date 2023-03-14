import { Component, OnInit } from '@angular/core';
import * as $ from 'jquery';
import {AbstractControl, FormBuilder, ValidationErrors, ValidatorFn, Validators} from '@angular/forms';
import {MatSnackBar} from '@angular/material/snack-bar';
import {RegistrationService} from './user-login-registration-service/registration.service';
import {AuthService} from './user-login-registration-service/auth.service';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {TranslateService} from '@ngx-translate/core';

@Component({
  selector: 'app-sign-up-sign-in',
  templateUrl: './sign-up-sign-in.component.html',
  styleUrls: ['./sign-up-sign-in.component.css']
})
export class SignUpSignInComponent implements OnInit {

  constructor(private router:Router,private fb: FormBuilder, private _snackBar: MatSnackBar, private signupService:RegistrationService, private loginService:AuthService,private http:HttpClient) {
  }


  ngOnInit(): void {

    'use strict';

////// page side animation

    $('.login-section').on('click', function() {
      $(this).addClass('section-open');
      $('.login-section').removeClass('section-close');
      $('.signup-section').addClass('section-close');
      $('.signup-section').removeClass('section-open');
    });

    $('.signup-section').on('click', function() {
      $(this).addClass('section-open');
      $('.signup-section').removeClass('section-close');
      $('.login-section').addClass('section-close');
      $('.login-section').removeClass('section-open');
      $('.login-form').slideDown();
      $('.forget-form').slideUp();
    });

////// custom placeholder

    $('.login-page_input').on('change', function() {
      var input = $(this);
      // @ts-ignore
      if (input.val().length) {
        input.addClass('hide-placeholder');
      } else {
        input.removeClass('hide-placeholder');
      }
    });

//// forget password

    $('.login-page_forget a').on('click', function(e) {
      e.preventDefault();
      $('.login-form').slideUp();
      $('.forget-form').slideDown();
    });
  }
  customerSignUp = this.fb.group({
    name: ['', Validators.required],
    emailId: ['', Validators.compose([
      Validators.required,
      Validators.pattern('[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,4}$'),
    ])],
    password: ['', Validators.compose([
      Validators.required,
      Validators.pattern(
          '^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d\\W]{8,63}$'
      ),
    ])],
    cnfPassword: ['', Validators.required],
    role: ['', Validators.required],
    otp:['', Validators.compose([
      Validators.required,
      Validators.pattern('^[0-9]{6}$')
    ])]

  }, {validator: verifypwd1('password', 'cnfPassword')})


  otpform=false;
  otpvalue:any;
  getOTP(){
    this.http.post("http://localhost:9201/api/v1/auth/getOtp",this.customerSignUp.value.emailId)
        .subscribe(data=>this.otpvalue=data)
    document.getElementById("getOtpButton").style.display="none";
    this.otpform=true;
}
  otpvalidation:any;
  saveCustomer(data:any) {
    this.signupService.post(data).subscribe(() => {
    let x = document.getElementsByClassName('login-form-area')[0] as HTMLElement;
    x.click();
      this._snackBar.open('Congrats, you have submitted the form!!', 'success', {
        duration: 5000,
        panelClass: ['mat-toolbar', 'mat-primary'],});
    })
  }

  registering(){
  if(this.otpvalue==this.otpvalidation && this.otpvalidation!=null && this.otpvalue!=null)
  {
    this.saveCustomer(this.customerSignUp.value);


  }
  else
  {
    this._snackBar.open('Wrong OTP', 'failure', {
      duration: 5000,
      panelClass: ['mat-toolbar', 'mat-danger'],});
  }
}

  loginCustomer = this.fb.group({
    emailId: ['', Validators.compose([
      Validators.required,
      Validators.pattern('[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,4}$'),
    ])],
    password: ['', Validators.required],
  });
    login() {
      
      this.loginService.validateUser(this.loginCustomer.value);
    }
  forgotPassword = this.fb.group({
    emailId: ['', Validators.compose([
      Validators.required,
      Validators.pattern('[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,4}$'),
    ])]
  });
  passwordOTP(){
    if(this.forgotPassword.value.emailId!=null) {
      this.http.post("http://localhost:9201/api/v1/auth/getOtp", this.forgotPassword.value.emailId)
          .subscribe(data => this.otpvalue = data)
      this.otpforgetform = true;
      document.getElementById("forgot-pass-email").style.display = "none";
    }
    else{
        this._snackBar.open('Enter Email Id', 'failure', {
            duration: 5000,
            panelClass: ['mat-toolbar', 'mat-danger'],});
    }
  }

  newPassword=false
  otpVerify:any;
  otpforgetform=false;
  verifyOTP() {
    if (this.otpvalue == this.otpVerify && this.otpvalue != null && this.otpVerify != null) {
        this.newPassword = true;
        document.getElementById("otp-verify-button").style.display = "none";
    }
  }


emailId:any;
  Password:any;
  forgotpass:any = [];

  changePassword(){
    this.forgotpass.push(this.emailId);
    this.forgotpass.push(this.Password);
    this.http.post("http://localhost:9201/api/v1/auth/updatepassword",this.forgotpass)
        .subscribe(data=>console.log(data));
  //   reload the window
    window.location.reload();
  }

}
export function verifypwd1(pass: string, cnfPass: string): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
        const password = control.get(pass)?.value;
        const cnfPassword = control.get(cnfPass)?.value;
        if (password != cnfPassword) {
            const err = {'noMatch': true};
            control.get(cnfPass)?.setErrors(err);
            return err;
        }
        return null;
    }
}
