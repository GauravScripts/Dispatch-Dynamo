import { HttpClient } from "@angular/common/http";
import { Component } from "@angular/core";
import { PageEvent } from "@angular/material/paginator";
import { VenderService } from "src/app/services/vender.service";

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
    selector: "app-user-tracking",
    templateUrl: "./user-tracking.component.html",
    styleUrls: ["./user-tracking.component.css"],
})
export class UserTrackingComponent {
    constructor(private vservice:VenderService){}
    shipments:any;
    search:any;
       getallshipments(){
           this.vservice.getallshipments(this.search).subscribe(
               response=>{
                   this.shipments=response;
                   console.log(this.shipments);
               }
           )
       }
       showPackageHistory=false;
      
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
}
