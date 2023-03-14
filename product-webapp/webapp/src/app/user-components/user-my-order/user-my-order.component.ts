import { HttpClient } from "@angular/common/http";
import { Component } from "@angular/core";
import { AuthService } from "src/app/sign-up-sign-in/user-login-registration-service/auth.service";
import jwt_decode from "jwt-decode";
import { PageEvent } from "@angular/material/paginator";
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
    selector: "app-user-my-order",
    templateUrl: "./user-my-order.component.html",
    styleUrls: ["./user-my-order.component.css"],
})
export class UserMyOrderComponent {
    constructor(
        private http: HttpClient,

        private authService: AuthService
    ) {}
    p: any;
    selectedDate: Date;
    orders: any[] = [];
    // displayedOrders: any[]; // orders to display on the current page
    // currentPage = 1; // current page number
    // totalPages: number; // total number of pages
    // currentPage: number = 1;
    // ordersPerPage: number = 5;
    // totalOrders: number;
    // pages: number;
    ordersPerPage = 5;
    pageSize = 4; // items per page
    currentPage = 1; // current page

    get totalPages() {
        return Math.ceil(this.orders.length / this.ordersPerPage);
    }

    get pages() {
        const start = (this.currentPage - 1) * this.ordersPerPage;
        const end = start + this.ordersPerPage;
        return this.orders.slice(start, end).reduce((acc, curr, i) => {
            const page = Math.floor(i / this.ordersPerPage);
            acc[page] = [...(acc[page] || []), curr];
            return acc;
        }, []);
    }

    get pageNumbers() {
        const numPages = this.totalPages;
        const startPage = Math.max(1, this.currentPage - 2);
        const endPage = Math.min(numPages, this.currentPage + 2);
        return Array.from(
            { length: endPage - startPage + 1 },
            (_, i) => startPage + i
        );
    }
    ngOnInit(): void {
        console.log(this.authService.getToken());
        let decoded = jwt_decode(localStorage.getItem("Token")) as DecodedToken;
        console.log(decoded);
        console.log(decoded.user_email);
        this.http
        .get("http://localhost:7575/getAllShipmentOfUser/"+ decoded.user_email)
            .subscribe((orders: any[]) => {
                this.orders = orders;
                this.orders.reverse();
                console.log(this.orders)
            });
    }
 
    pageIndex = 0;
    showAddPackageHistory = false;
    onPageChange(event: PageEvent) {
        this.pageIndex = event.pageIndex;
        this.pageSize = event.pageSize;
        const startIndex = this.pageIndex * this.pageSize;
        const endIndex = startIndex + this.pageSize;
       
    }
}
