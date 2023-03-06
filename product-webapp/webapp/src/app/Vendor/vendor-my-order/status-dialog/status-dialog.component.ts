import { Component, Inject } from "@angular/core";
import { MAT_DIALOG_DATA, MatDialogRef } from "@angular/material/dialog";
import { MatDialogModule } from "@angular/material/dialog";
import { Order } from "src/app/bookorder/Order";
import { FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { VendorMyOrderComponent } from "../vendor-my-order.component";

@Component({
    selector: "app-status-dialog",
    templateUrl:"./status-dialog.component.html",
    styleUrls: ["./status-dialog.component.css"],
})
export class StatusDialogComponent {
    body1: boolean;
    body2: boolean;
    selectedStatus: string = "";
    status: string = "";
    local: Object = {};

    constructor(
        public dialogRef: MatDialogRef<StatusDialogComponent>,
        @Inject(MAT_DIALOG_DATA) public data: { status: string, body1: boolean ,body2: boolean}
        ) 
        {
        this.body1 = data.body1;
        this.body2 = data.body2;
        }
        
    prints() {
        console.log(status);
        console.log(this.selectedStatus);
        this.status = this.selectedStatus;
    }

    onNoClick(): void {
        this.dialogRef.close(this.status);
        console.log(this.selectedStatus);
        console.log(this.status);
    }

    savelocation(locationdisc:NgForm){
        this.local = locationdisc.value;
        console.log(this.local);
    }

    onNoClick2(): void {
        this.dialogRef.close(this.local);
        console.log(this.local);
    }
}
