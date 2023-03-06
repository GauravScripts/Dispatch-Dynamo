import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable, ReplaySubject } from 'rxjs';
import jwt_decode from "jwt-decode";
import { AuthService } from '../sign-up-sign-in/user-login-registration-service/auth.service';
import { VServicesService } from '../Vendor/v-services.service';

@Component({
  selector: 'app-pagenotfound',
  templateUrl: './pagenotfound.component.html',
  styleUrls: ['./pagenotfound.component.css']
})
export class PagenotfoundComponent {

  files: any[];
  selectedFile: File;

  constructor(private V_service: VServicesService, private snackBar: MatSnackBar, private authService:AuthService){
    
  }

   onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
    console.log(this.selectedFile);
  }

 onUpload() {

   interface DecodedToken {
     user_email: string;
     User: {
       empty: boolean;
       present: boolean;
     };
     role: string;
     iat: number;
   }
   console.log(this.authService.getToken());
   let decoded = jwt_decode(localStorage.getItem("Token")) as DecodedToken;
   console.log(decoded.user_email);

   console.log(this.selectedFile);
    const formData = new FormData();
    formData.append('file', this.selectedFile);
    formData.append('email', decoded.user_email);
   console.log(formData.append('image', this.selectedFile!, this.selectedFile?.name));
   console.log(formData.append('email', decoded.user_email));
    console.log(formData);

    this.V_service.addImage(formData).subscribe({ next: data => this.snack(data), error: e => alert(`${e.message}\n${e.status}`) });
  }

  snack(data: any) {
    console.log("inside snack bar")
    this.snackBar.open('Image Successfully added with ID: ' + data, 'success', {
      duration: 3000,
      panelClass: ['mat-toolbar', 'mat-primary']
    })
  }
}

