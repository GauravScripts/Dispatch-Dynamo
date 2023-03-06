import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { map, Observable, shareReplay } from 'rxjs';

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

  constructor(private breakpointObserver: BreakpointObserver,private router:Router) { }

  logout() {
    localStorage.clear();
    this.router.navigate(['/']);
  }
}
