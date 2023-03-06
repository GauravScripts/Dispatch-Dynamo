import { Component } from '@angular/core';
export const VENDORS = [
  { id: 1, name: "Vendor 1", rating: 4.5 },
  { id: 2, name: "Vendor 2", rating: 4.8 },
  { id: 3, name: "Vendor 3", rating: 4.2 },
  { id: 4, name: "Vendor 4", rating: 4.7 }
];
@Component({
  selector: 'app-user-booking',
  templateUrl: './user-booking.component.html',
  styleUrls: ['./user-booking.component.css']
})
export class UserBookingComponent {
  vendors = VENDORS;
  ngOnInit() {
    console.log(this.vendors); // This will log the contents of the vendors array to the console
  }
  
}
