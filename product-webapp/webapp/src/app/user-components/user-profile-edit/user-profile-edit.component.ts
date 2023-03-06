import { HttpClient } from "@angular/common/http";
import { Component } from "@angular/core";
import { UserServiceService } from "src/app/services/user-service.service";
import { AuthService } from "src/app/sign-up-sign-in/user-login-registration-service/auth.service";
import jwt_decode from "jwt-decode";
import { MatSnackBar } from "@angular/material/snack-bar";
import {
    FormBuilder,
    FormControl,
    FormGroup,
    Validators,
} from "@angular/forms";
import { UserService } from "src/app/services/user.service";
import { Router } from "@angular/router";

interface DecodedToken {
    user_email: string;
    User: {
        empty: boolean;
        present: boolean;
    };
    role: string;
    iat: number;
}

@Component({
    selector: "app-user-profile-edit",
    templateUrl: "./user-profile-edit.component.html",
    styleUrls: ["./user-profile-edit.component.css"],
})
export class UserProfileEditComponent {
    // user: User;
    disabled: true;

    constructor(
        private router: Router,
        private http: HttpClient,
        private userService: UserServiceService,
        private userService1: UserService,
        private authService: AuthService,
        private _snackBar: MatSnackBar,
        private fb: FormBuilder
    ) {}

    updateForm = new FormGroup({
        name: new FormControl(),
        emailId: new FormControl({ disabled: true }),
        mobileNo: new FormControl(),

        address: new FormGroup({
            addressLine1: new FormControl(),
            city: new FormControl(),
            state: new FormControl(),
            country: new FormControl(),
            zipcode: new FormControl(),
        }),
    });

    openSnackBar() {
        this._snackBar.open('Update Successfully', 'success', {
            duration: 3000,
            panelClass: ['mat-toolbar', 'mat-primary']
        })
    }

    updateUser(emailId: any) {
        console.log(this.updateForm.value);

        this.userService
            .updateUser(emailId, this.updateForm.value)
            .subscribe((res) => {
                // this.userDetail=res;
                console.log(res);
                this.router.navigateByUrl("/usidenav/userprofile");
            });
    }

    userDetail: any;

    ngOnInit(): void {
        console.log(this.authService.getToken());
        let decoded = jwt_decode(localStorage.getItem("Token")) as DecodedToken;
        console.log(decoded);
        console.log(decoded.user_email);

        this.userService.getProfile(decoded.user_email).subscribe((res) => {
            console.log("Hii user");
            // this.user = res;
            this.userDetail = res;
        });
    }
}
