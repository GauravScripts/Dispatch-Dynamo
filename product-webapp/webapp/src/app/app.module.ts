import { NgModule } from "@angular/core";
import { MatButtonToggleModule } from "@angular/material/button-toggle";
import { MatButtonModule } from "@angular/material/button";
import { UserSideHamburgerMenuComponent } from "./user-components/user-side-hamburger-menu/user-side-hamburger-menu.component";
import { BrowserModule } from "@angular/platform-browser";
import { MatCardModule } from "@angular/material/card";
import {
    MatDialogModule,
    MatDialogRef,
    MAT_DIALOG_DATA,
} from "@angular/material/dialog";
import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { UserDashboardComponent } from "./user-components/user-dashboard/user-dashboard.component";
import { AgmCoreModule } from "@agm/core";
import { HttpClient, HttpClientModule } from "@angular/common/http";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { NavComponent } from "./home/nav/nav.component";
import { AboutusComponent } from "./home/aboutus/aboutus.component";
import { BookorderComponent } from "./bookorder/bookorder.component";
import { ContactUsComponent } from "./home/contact-us/contact-us.component";
import { UserProfileComponent } from "./user-components/user-profile/user-profile.component";
import { MatIconModule } from "@angular/material/icon";
import { HomeComponent } from "./home/home.component";
import { PaymentComponent } from "./payment/payment.component";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { MatInputModule } from "@angular/material/input";
import { MatSidenavModule } from "@angular/material/sidenav";
import { MatListModule } from "@angular/material/list";
import { MatToolbarModule } from "@angular/material/toolbar";
import { VProfileComponent } from "./Vendor/v-profile/v-profile.component";
import { MatRadioModule } from "@angular/material/radio";
import { UserProfileEditComponent } from "./user-components/user-profile-edit/user-profile-edit.component";
import { MatSnackBarModule } from "@angular/material/snack-bar";
import { UserBookingComponent } from "./user-components/user-booking/user-booking.component";
import { UserTrackingComponent } from "./user-components/user-tracking/user-tracking.component";
import { MatStepperModule } from "@angular/material/stepper";
import { MatGridListModule } from "@angular/material/grid-list";
import { TranslateLoader, TranslateModule } from "@ngx-translate/core";
import { TranslateHttpLoader } from "@ngx-translate/http-loader";
import { PagenotfoundComponent } from "./pagenotfound/pagenotfound.component";
import { SignUpSignInComponent } from "./sign-up-sign-in/sign-up-sign-in.component";
import { MatPaginatorModule } from "@angular/material/paginator";
import { NgxPaginationModule } from "ngx-pagination";
import { MatTableModule } from "@angular/material/table";
import { MatOptionModule } from "@angular/material/core";
import { MatSelectModule } from "@angular/material/select";
import { VDashboardComponent } from "./Vendor/v-dashboard/v-dashboard.component";
import { VTrackComponent } from "./Vendor/v-track/v-track.component";
import { VSupportComponent } from "./Vendor/v-support/v-support.component";
import { VSidenavComponent } from "./Vendor/v-sidenav/v-sidenav.component";
import { SidebarComponent } from "./Vendor/sidebar/sidebar.component";
import { RecommendationComponent } from "./recommendation/recommendation.component";
import { ChatBotComponent } from "./chat/chat-bot/chat-bot.component";
import { MatMenuModule } from "@angular/material/menu";
import { VAddCityComponent } from "./Vendor/v-add-city/v-add-city.component";
import { ConfirmDialogComponent } from "./Vendor/v-add-city/confirm-dialog/confirm-dialog.component";
import { NgImageSliderModule } from "ng-image-slider";
import { UserMyOrderComponent } from "./user-components/user-my-order/user-my-order.component";
import { VUpdatepriceComponent } from "./Vendor/v-updateprice/v-updateprice.component";
import { NgAisModule } from "angular-instantsearch";
import { VendorMyOrderComponent } from "./Vendor/vendor-my-order/vendor-my-order.component";
import { StatusDialogComponent } from "./Vendor/vendor-my-order/status-dialog/status-dialog.component";
import { OrderInfoDialogComponent } from './Vendor/vendor-my-order/order-info-dialog/order-info-dialog.component';
import { UserProductRegoniseComponent } from './user-components/user-product-regonise/user-product-regonise.component';
import { environment } from "../environments/environment";
import { ClassifierCameraComponent } from "./user-components/user-product-regonise/classifier-camera/classifier-camera.component";
import { ClassifierUploadComponent } from "./user-components/user-product-regonise/classifier-upload/classifier-upload.component";

export function translateHttpLoaderFactory(http: HttpClient) {
    return new TranslateHttpLoader(http);
}
import { MatTooltipModule } from '@angular/material/tooltip';
import { ServiceWorkerModule } from "@angular/service-worker";
import { MatSlideToggleModule } from "@angular/material/slide-toggle";
import { FooterComponent } from './home/footer/footer.component';
import { ServiceComponent } from "./home/service/service.component";


@NgModule({
    declarations: [
        AppComponent,
        UserDashboardComponent,
        NavComponent,
        AboutusComponent,
        ContactUsComponent,
        UserProfileComponent,
        UserBookingComponent,
        HomeComponent,
        PaymentComponent,
        VProfileComponent,
        UserSideHamburgerMenuComponent,
        UserProfileEditComponent,
        BookorderComponent,
        UserTrackingComponent,
        PagenotfoundComponent,
        SignUpSignInComponent,
        VDashboardComponent,
        VTrackComponent,
        VSupportComponent,
        VSidenavComponent,
        SidebarComponent,
        RecommendationComponent,
        ChatBotComponent,
        VAddCityComponent,
        ConfirmDialogComponent,
        UserMyOrderComponent,
        VendorMyOrderComponent,
        VUpdatepriceComponent,
        StatusDialogComponent,
        OrderInfoDialogComponent,
        UserProductRegoniseComponent,
        ClassifierCameraComponent,
        ClassifierUploadComponent,
        VSidenavComponent,
        VProfileComponent,
        FooterComponent,
        ServiceComponent,
    ],
    providers: [
        {
            provide: MatDialogRef,
            useValue: [],
        },
        {
            provide: MAT_DIALOG_DATA,
            useValue: [],
        },
    ],
    bootstrap: [AppComponent],
    imports: [
        BrowserModule,
        MatSelectModule,
        AppRoutingModule,
        HttpClientModule,
        MatTableModule,
        FormsModule,
        ReactiveFormsModule,
        BrowserAnimationsModule,
        MatIconModule,
        MatInputModule,
        MatButtonModule,
        MatPaginatorModule,
        MatCardModule,
        MatSidenavModule,
        MatListModule,
        MatToolbarModule,
        MatRadioModule,
        MatSnackBarModule,
        MatStepperModule,
        MatGridListModule,
        MatDialogModule,
        MatPaginatorModule,
        MatButtonToggleModule,
        MatTableModule,
        MatSelectModule,
        MatTooltipModule,
        NgxPaginationModule,
        AgmCoreModule.forRoot({
            apiKey: "AIzaSyAxGSC7dQ5r24mN-Yr01aBECc1s_x_7psM",
        }),
        TranslateModule.forRoot({
            loader: {
                provide: TranslateLoader,
                useFactory: translateHttpLoaderFactory,
                deps: [HttpClient],
            },
        }),
        MatPaginatorModule,
        MatTableModule,
        MatOptionModule,
        MatSelectModule,
        MatMenuModule,
        NgImageSliderModule,
        NgAisModule.forRoot(),
        ServiceWorkerModule.register('ngsw-worker.js', { enabled: environment.production }),
        MatSlideToggleModule,

    ],
})
export class AppModule { }
