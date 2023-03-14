import { Component, Inject } from "@angular/core";
import jwt_decode from "jwt-decode";
import { PageEvent } from "@angular/material/paginator";
import { HttpClient } from "@angular/common/http";
import { AuthService } from "src/app/sign-up-sign-in/user-login-registration-service/auth.service";
import { MatDialog } from "@angular/material/dialog";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";
import { MatDialogModule } from "@angular/material/dialog";
import { StatusDialogComponent } from "./status-dialog/status-dialog.component";
// import * as $ from "jquery";
import { data } from "jquery";
import { OrderInfoDialogComponent } from "./order-info-dialog/order-info-dialog.component";
interface order {
    trackingId: any;
    length: any;
    breadth: any;
    height: any;
    servicetype: string;
    userId: string;
    vendorId: string;
    modeOfTransport: string;
    price: any;
    fromAddress: {
        addressLine1: string;
        city: string;
        state: string;
        country: string;
        zipcode: string;
    };
    toAddress: {
        addressLine1: string;
        city: string;
        state: string;
        country: string;
        zipcode: string;
    };
    orderDateAndTime: any;
    status: string;
    transition: Transition[];
}
export interface Transition {
    trackId: any;
    locationCity: string;
    description: string;
}

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
    selector: "app-vendor-my-order",
    templateUrl: "./vendor-my-order.component.html",
    styleUrls: ["./vendor-my-order.component.css"],
})
export class VendorMyOrderComponent {
    constructor(
        private http: HttpClient,

        private authService: AuthService,
        private dialog: MatDialog,
        public dialogRef: MatDialogRef<StatusDialogComponent>,
        @Inject(MAT_DIALOG_DATA) public data: any,
        public dialogRef2: MatDialogRef<OrderInfoDialogComponent>,
        @Inject(MAT_DIALOG_DATA) public data2: any
    ) {}
    p: any;
    selectedDate: Date;
    transition: Transition;
    status: string;
    order: order;
    orders: order[] = [];

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
            .get(
                "http://localhost:7575/getAllShipmentOfVendor/"+decoded.user_email 
                // +
                    // decoded.user_email
            )
            .subscribe((orders: order[]) => {
                this.orders = orders.reverse();
            });
    }

    pageIndex = 0;
    showAddPackageHistory = false;
    openDialog(order: order) {
        const dialogRef = this.dialog.open(StatusDialogComponent, {
            data: { status: order.status, body1: true ,body2: false}
            
        });
        // console.log(order.status);

        dialogRef.afterClosed().subscribe((result) => {
            console.log(order.transition);
            console.log(result);
            if (result) {
                console.log(result);
                order.status = result;
                console.log("order"+order);
                console.log("status"+order.status);
                this.http
                    .put(
                        "http://localhost:7575/updateStatus/" +
                            order.trackingId,
                              order.status     
                    )
                    .subscribe(
                        () => {
                            console.log("Order status updated successfully");
                        },
                        (error) => {
                            console.error("Error updating order status", error);
                        }
                    );
            }
        });
    }
    openDialog2(order: order) {
        const dialogRef = this.dialog.open(StatusDialogComponent, {
            data: { body1: false, body2: true },
        });
        
        dialogRef.afterClosed().subscribe((result) => {

            if (result.value) {
                console.log(result.value);
                console.log(result.value.locationCity);

                const obj1={trackId:order.trackingId}
                const obj2= {...obj1,...result.value}

                console.log(obj2);

                this.http.post("http://localhost:7575/updateTransition/" ,obj2)
                    .subscribe(
                        () => {
                            console.log("Package History added successfully");
                        },
                        (error) => {
                            console.error("Error Adding Package History", error);
                        }
                    );
            }
         


            // formData.append('trackId',order.trackingId);
            // formData.append('locationCity',result.value.locationCity);
            // formData.append('description',result.value.description);
            // console.log(formData);

    });
    }
}
