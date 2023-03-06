import { Component, Inject } from "@angular/core";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";
@Component({
    selector: "app-order-info-dialog",
    templateUrl: "./order-info-dialog.component.html",
    styleUrls: ["./order-info-dialog.component.css"],
})
export class OrderInfoDialogComponent {
    // order: any;
    // transition: any;

    // constructor(
    //     public dialogRef2: MatDialogRef<OrderInfoDialogComponent>,
    //     @Inject(MAT_DIALOG_DATA) public data2: any
    // ) {
    //     this.order = data2.order;
    //     this.transition = data2.transition;
    // }

    // onCancelClick(): void {
    //     this.dialogRef2.close();
    // }

    // onSaveClick(): void {
    //     const result = {
    //         order: this.order,
    //         transition: this.transition,
    //     };
    //     console.log(result);
    //     this.dialogRef2.close(result);
    // }
    
}
