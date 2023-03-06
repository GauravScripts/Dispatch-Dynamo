import { Component, ElementRef, Renderer2 } from "@angular/core";
import { BreakpointObserver, Breakpoints } from "@angular/cdk/layout";
import { NavigationEnd, Router } from "@angular/router";
import { map, Observable, shareReplay } from "rxjs";
import { Location } from "@angular/common";

@Component({
    selector: "app-user-side-hamburger-menu",
    templateUrl: "./user-side-hamburger-menu.component.html",
    styleUrls: ["./user-side-hamburger-menu.component.css"],
})
export class UserSideHamburgerMenuComponent {
    currentUrl: string;
    isHandset$: Observable<boolean> = this.breakpointObserver
        .observe(Breakpoints.Handset)
        .pipe(
            map((result) => result.matches),
            shareReplay()
        );
    constructor(

        private breakpointObserver: BreakpointObserver,
        private router: Router,
        private renderer: Renderer2,
        private el: ElementRef,
        private location: Location,

    ) {
        this.router.events.subscribe((event) => {
            if (event instanceof NavigationEnd) {
                this.currentUrl = event.url;
            }
        });
    }
    ngOnInit() {
        // this.router.events.subscribe((event) => {
        //     if (event instanceof NavigationEnd) {
        //         const currentUrl = event.url;
        //         const matSidenavContent = document.querySelector(
        //             ".mat-drawer-content"
        //         ) as HTMLElement;
        //         if (currentUrl === "/usidenav/bookOrder") {
        //             matSidenavContent.style.overflow = "scroll";
        //         } else {
        //             matSidenavContent.style.overflow = "hidden";
        //         }
        //     }
        // });
    }

    logout() {
        localStorage.clear();
        this.router.navigate(["/"]);
    }
}
