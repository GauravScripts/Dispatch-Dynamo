import { HttpClient } from "@angular/common/http";
import { Component } from "@angular/core";
import { Route, Router } from "@angular/router";
import jwt_decode from "jwt-decode";
import { UserServiceService } from "src/app/services/user-service.service";
import { UserService } from "src/app/services/user.service";
import { AuthService } from "src/app/sign-up-sign-in/user-login-registration-service/auth.service";
interface DecodedToken {
    user_email: string;
    User: {
      empty: boolean;
      present: boolean;
    };
    role: string;
    iat: number;
  }


  interface User {
    emailId: string;
    name: String;
  }
@Component({
    selector: "app-user-profile",
    templateUrl: "./user-profile.component.html",
    styleUrls: ["./user-profile.component.css"],
})
export class UserProfileComponent {
    user: User;

    constructor(
        private http: HttpClient,
        private userService: UserServiceService,
        private authService: AuthService,
        private router:Router
    ) {}
    userDetail:any;

    ngOnInit(): void {
        console.log(this.authService.getToken());
        let decoded = jwt_decode(localStorage.getItem("Token")) as DecodedToken;
        console.log(decoded);
    console.log(decoded.user_email);

        this.userService.getProfile(decoded.user_email).subscribe((res) => {
            this.user = res;
            this.userDetail=res;

        });
    }

   editUser() {

        this.router.navigateByUrl("/usidenav/editUserProfile")
    }
}
