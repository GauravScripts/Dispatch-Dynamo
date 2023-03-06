import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import {MatDialogModule} from '@angular/material/dialog'; 

@Component({
  selector: 'app-confirm-dialog',
  template: `
    <h2 mat-dialog-title>Confirm</h2>
    <div mat-dialog-content>
      {{ data.message }}
    </div>
    <div mat-dialog-actions>
      <button mat-button class="cancel-button" (click)="onNoClick()">Cancel</button>
      <button mat-button color="warn" [mat-dialog-close]="true">Delete</button>
      <!-- BUG: Getting the error Can't bind to 'mat-dialog-close' since it isn't a known property of 'button'.ngtsc(-998002)
 -->
    </div>
  `,
  styles: [`
    .cancel-button:focus {
      outline: none;
    }
  `]
})
export class ConfirmDialogComponent {
  constructor(
      @Inject(MAT_DIALOG_DATA) public data: any,
      public dialogRef: MatDialogRef<ConfirmDialogComponent>
  ) {}

  onNoClick(): void {
    this.dialogRef.close(false);
  }
}
