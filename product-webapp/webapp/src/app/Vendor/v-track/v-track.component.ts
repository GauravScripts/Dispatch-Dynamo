import { HttpClient } from "@angular/common/http";
import { Component, ViewChild } from "@angular/core";
import { MatPaginator, PageEvent } from "@angular/material/paginator";
import { VenderService } from "src/app/services/vender.service";
import { Status } from "tslint/lib/runner";
// export interface OrderDetail {
//     item: string;
//     status: string;
// }
export interface Transition {
    locationCity: string;
    description: string;
    dateAndTime: string;
}
export interface Package {
    id: number;
    senderName: string;
    senderAddress: string;
    receiverName: string;
    receiverAddress: string;
    weight: number;
    status: string;
    transition: Transition[];
}
@Component({
    selector: "app-v-track",
    templateUrl: "./v-track.component.html",
    styleUrls: ["./v-track.component.css"],
})
export class VTrackComponent {
 constructor(private vservice:VenderService){}
 shipments:any;
 search:any;
    getallshipments(){
        this.vservice.getallshipments(this.search).subscribe(
            response=>{
                this.shipments=response;

            }
        )
    }
    showPackageHistory=false;
    @ViewChild(MatPaginator) paginator: MatPaginator;
    transition: any[] = [];
    // showPackageHistory: boolean = false;
    pageSize = 5;
    resultsLength = 0;
    
    p: number=1;
    // pageIndex = 0;
    showAddPackageHistory = false;
    pagedTransition = this.transition.slice(0, this.pageSize);
    paginate;
    // status: string = Status[this.package.status];
    newTransition: Transition = {
        locationCity: "",
        description: "",
        dateAndTime: "",
    };
    // constructor(private http: HttpClient) {}

    // fetchData() {
    //     this.http
    //         .get<any>("http://localhost:7575/getshipment/" + this.trackingId)
    //         .subscribe((data) => {
    //             // assign data to variables
    //             this.trackingId = data.trackingId;
    //             this.userId = data.userId;
    //             this.status = data.status;
    //             this.transportMode = data.modeOfTransport;
    //             this.price = data.price;
    //             this.fromAddress = data.fromAddress;
    //             this.toAddress = data.toAddress;
    //             this.transition = [];
    //             // data.transition.forEach((t) => {
    //             //     this.transition.push({
    //             //         locationCity: t.locationCity,
    //             //         description: t.description,
    //             //         dateAndTime: t.dateAndTime,
    //             //     });
    //             // });
    //             this.pagedTransition = data.transition;
    //         });
    // }
    // package: Package = {
    //     id: 1,
    //     senderName: "John Doe",
    //     senderAddress: "123 Main St, Anytown USA",
    //     receiverName: "Jane Smith",
    //     receiverAddress: "456 Oak St, Anytown USA",
    //     weight: 1.23,
    //     status: "Ordered",
    //     transition: [
    //         {
    //             locationCity: "Anytown USA",
    //             description: "Package received by shipper",
    //             dateAndTime: "2022-02-23 10:23:34",
    //         },
    //         {
    //             locationCity: "Anytown USA",
    //             description: "Package received by carrier",
    //             dateAndTime: "2022-02-23 12:45:12",
    //         },
    //         {
    //             locationCity: "New York, NY",
    //             description: "Package arrived at destination",
    //             dateAndTime: "2022-02-24 07:56:43",
    //         },
    //         {
    //             locationCity: "New York, NY",
    //             description: "Package out for delivery",
    //             dateAndTime: "2022-02-24 08:12:08",
    //         },
    //         {
    //             locationCity: "New York, NY",
    //             description: "Package delivered",
    //             dateAndTime: "2022-02-24 13:27:17",
    //         },
    //     ],
    // };

    // ngOnInit(): void {
    //     this.updatePagedTransition();
    // }

    // togglePackageHistory(): void {
    //     this.showPackageHistory = !this.showPackageHistory;
    //     this.updatePagedTransition();
    // }

    // onPageChange(event: any): void {
    //     this.updatePagedTransition();
    // }

    // private updatePagedTransition(): void {
    //     const itemsPerPage = 5;
    //     const startIndex = (this.p - 1) * itemsPerPage;
    //     const endIndex = startIndex + itemsPerPage;
    //     this.pagedTransition = this.package.transition.slice(
    //         startIndex,
    //         endIndex
    //     );
    // }

    // onStatusChange(status: string): void {
    //     this.package.status = status;
    // }

    // addTransition(locationCity: string, description: string): void {
    //     const dateAndTime = new Date().toISOString();
    //     this.newTransition = {
    //         locationCity,
    //         description,
    //         dateAndTime,
    //     };
    //     this.package.transition.push(this.newTransition);
    //     this.newTransition = {
    //         locationCity: "",
    //         description: "",
    //         dateAndTime: "",
    //     };
    //     this.updatePagedTransition();
    // }
    // showAddPackageHistoryForm(): void {
    //     this.showAddPackageHistory = true;
    // }
}

// onPageChange(event: PageEvent) {
//     this.pageIndex = event.pageIndex;
//     this.pageSize = event.pageSize;
//     const startIndex = this.pageIndex * this.pageSize;
//     const endIndex = startIndex + this.pageSize;
//     this.pagedTransition = this.transition.slice(startIndex, endIndex);
//     this.fetchData();
// }
// }
