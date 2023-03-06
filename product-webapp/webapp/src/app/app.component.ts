import { Component } from '@angular/core';
import { fromEvent } from 'rxjs';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  isOnline: boolean = true;
  title = 'DispatchDynamo';

  constructor(private snackBar: MatSnackBar) {
    fromEvent(window, 'online').subscribe(() => {
      this.isOnline = true;
      this.showSnackbar('Hurrah!🥳 You are Online now 🎉', 'success');
    });
    fromEvent(window, 'offline').subscribe(() => {
      this.isOnline = false;
      this.showSnackbar('You are Offline. Please check your internet connection 🥵', 'warn');
    });
  }

  private showSnackbar(message: string, panelClass: string) {
    const horizontalPosition: MatSnackBarHorizontalPosition = 'center';
    const verticalPosition: MatSnackBarVerticalPosition = 'bottom';

    this.snackBar.open(message, '', {
      duration: 3000,
      horizontalPosition,
      verticalPosition,
      panelClass: [panelClass]
    });
  }
}
