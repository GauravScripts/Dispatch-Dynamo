import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { AboutusComponent } from "./home/aboutus/aboutus.component";
import { ContactUsComponent } from "./home/contact-us/contact-us.component";
import { HomeComponent } from "./home/home.component";
import { PaymentComponent } from "./payment/payment.component";
import { UserDashboardComponent } from "./user-components/user-dashboard/user-dashboard.component";
import { BookorderComponent } from "./bookorder/bookorder.component";
import { UserProfileComponent } from "./user-components/user-profile/user-profile.component";
import { UserProfileEditComponent } from "./user-components/user-profile-edit/user-profile-edit.component";
import { SignUpSignInComponent } from "./sign-up-sign-in/sign-up-sign-in.component";
import { VDashboardComponent } from "./Vendor/v-dashboard/v-dashboard.component";
import { VProfileComponent } from "./Vendor/v-profile/v-profile.component";
import { VSupportComponent } from "./Vendor/v-support/v-support.component";
import { VTrackComponent } from "./Vendor/v-track/v-track.component";
import { VSidenavComponent } from "./Vendor/v-sidenav/v-sidenav.component";
import { VAddCityComponent } from "./Vendor/v-add-city/v-add-city.component";
import { RecommendationComponent } from "./recommendation/recommendation.component";
import { VUpdatepriceComponent } from "./Vendor/v-updateprice/v-updateprice.component";
import { UserTrackingComponent } from "./user-components/user-tracking/user-tracking.component";
import { UserSideHamburgerMenuComponent } from "./user-components/user-side-hamburger-menu/user-side-hamburger-menu.component";
import { UserMyOrderComponent } from "./user-components/user-my-order/user-my-order.component";
import { VendorMyOrderComponent } from "./Vendor/vendor-my-order/vendor-my-order.component";
import {
    ClassifierUploadComponent
} from "./user-components/user-product-regonise/classifier-upload/classifier-upload.component";
import {
    ClassifierCameraComponent
} from "./user-components/user-product-regonise/classifier-camera/classifier-camera.component";
import { UserProductRegoniseComponent } from "./user-components/user-product-regonise/user-product-regonise.component";
import { AuthGuard } from "./auth.guard";
import { PagenotfoundComponent } from "./pagenotfound/pagenotfound.component";
import { ServiceComponent } from "./home/service/service.component";


const routes: Routes = [
    { path: "", component: HomeComponent },
    { path: "getStarted", component: SignUpSignInComponent },
    { path: "about", component: AboutusComponent },
    { path: "contact", component: ContactUsComponent },
    { path: "service", component: ServiceComponent },
    { path: "payment", component: PaymentComponent },

    {
        path: "usidenav",
        component: UserSideHamburgerMenuComponent, canActivate: [AuthGuard],
        children: [
            // { path: "", component: UserDashboardComponent , },
            { path: "userprofile", component: UserProfileComponent, canActivate: [AuthGuard] },
            { path: "editUserProfile", component: UserProfileEditComponent, canActivate: [AuthGuard] },
            { path: "userOrders", component: UserMyOrderComponent, canActivate: [AuthGuard] },
            { path: "userTracking", component: UserTrackingComponent, canActivate: [AuthGuard] },
            { path: "bookOrder", component: BookorderComponent, canActivate: [AuthGuard] },
            { path: "userDashboard", component: UserDashboardComponent, canActivate: [AuthGuard] },
            { path: "userOrder", component: UserMyOrderComponent, canActivate: [AuthGuard] },
            { path: "upload", component: ClassifierUploadComponent, canActivate: [AuthGuard] },
        ],
    },

    { path: "recommendation", component: RecommendationComponent },

    {
        path: "vsidenav",
        component: VSidenavComponent,
        children: [
            { path: "", component: VDashboardComponent, canActivate: [AuthGuard] },
            { path: "vdashboard", component: VDashboardComponent, canActivate: [AuthGuard] },
            { path: "vorders", component: VendorMyOrderComponent, canActivate: [AuthGuard] },
            { path: "vcity", component: VAddCityComponent, canActivate: [AuthGuard] },
            { path: "vprofile", component: VProfileComponent },
            { path: "vsupport", component: VSupportComponent, canActivate: [AuthGuard] },
            { path: "vtrack", component: VTrackComponent, canActivate: [AuthGuard] },
            { path: "vupdateprice", component: VUpdatepriceComponent, canActivate: [AuthGuard] },
        ],
    },
    { path: 'cam', component: ClassifierCameraComponent },
    { path: "**", component: PagenotfoundComponent }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule],
})
export class AppRoutingModule { }
