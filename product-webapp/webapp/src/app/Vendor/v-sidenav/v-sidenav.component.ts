import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { map, Observable, shareReplay } from 'rxjs';
import { Vendor } from 'src/app/module/vender';
import { VServicesService } from '../v-services.service';

@Component({
  selector: 'app-v-sidenav',
  templateUrl: './v-sidenav.component.html',
  styleUrls: ['./v-sidenav.component.css']
})
export class VSidenavComponent {
  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );
  vendor: Vendor;

  constructor(private breakpointObserver: BreakpointObserver, private router: Router, private V_service:VServicesService) {
    this.V_service.getvendordetails().subscribe((data: Vendor) => {
      this.vendor = data
   });
  }

  logout() {
    localStorage.clear();
    this.router.navigate(['/']);
  }
}
