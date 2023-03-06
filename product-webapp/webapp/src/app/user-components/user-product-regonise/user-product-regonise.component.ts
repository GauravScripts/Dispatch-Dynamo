import { Component } from '@angular/core';
import {SwUpdate} from "@angular/service-worker";

@Component({
  selector: 'app-user-product-regonise',
  templateUrl: './user-product-regonise.component.html',
  styleUrls: ['./user-product-regonise.component.css']
})
export class UserProductRegoniseComponent {
  promptEvent;
  update: boolean = false;

  constructor(updates: SwUpdate,) {

    updates.available.subscribe(event => {

      updates.activateUpdate().then(() => document.location.reload());
    });
  }
}
