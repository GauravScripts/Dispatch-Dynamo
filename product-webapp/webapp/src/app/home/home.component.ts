import { Component } from '@angular/core';
import {Router} from '@angular/router';
import Typed from 'typed.js/src/typed.js';
import {AuthService} from "../sign-up-sign-in/user-login-registration-service/auth.service";
import jwt_decode from "jwt-decode";
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  imageObject = [{
    image: 'https://cdn.smartkarrot.com/wp-content/uploads/2020/09/Satisfied-Customers.png',
    thumbImage: 'https://cdn.smartkarrot.com/wp-content/uploads/2020/09/Satisfied-Customers.png',
    title: 'Our Happy Customers'
  }, {
    image: 'https://watermark.lovepik.com/photo/20211208/large/lovepik-logistics-worker-pulling-goods-picture_501641115.jpg',
    thumbImage: 'https://watermark.lovepik.com/photo/20211208/large/lovepik-logistics-worker-pulling-goods-picture_501641115.jpg',
    title: 'Logistics Worker'
  }, {
    image: 'https://amdistro.com/wp-content/uploads/2020/02/AdobeStock_271350327.jpeg',
    thumbImage: 'https://amdistro.com/wp-content/uploads/2020/02/AdobeStock_271350327.jpeg',
    title: 'Deliver the goods on time'
  },{
    image: 'https://thumbs.dreamstime.com/b/happy-delivery-man-customer-men-white-background-65379929.jpg',
    thumbImage: 'https://thumbs.dreamstime.com/b/happy-delivery-man-customer-men-white-background-65379929.jpg',
    title: '100% Statisfaction'
  }, {
    video: 'https://synthesia-ttv-data.s3-eu-west-1.amazonaws.com/video_data/d8383a8f-0420-4c93-94b8-deac09f027f5/transfers/target_transfer.mp4',
    posterImage: 'https://dpbnri2zg3lc2.cloudfront.net/en/wp-content/uploads/2021/01/web_dev_pillar_page.jpg',
    title: 'Website Credits'
  }, {
    image: 'https://sanjayv.github.io/ng-image-slider/contents/assets/img/slider/2.jpg',
    thumbImage: 'https://sanjayv.github.io/ng-image-slider/contents/assets/img/slider/2.jpg',
    title: 'Example two with title.'
  }];




    constructor(private _router: Router,
  private authService:AuthService
    ) { }

 
  getstarted(){
    const token = this.authService.getToken();
    if (token) {
      const decoded: any = jwt_decode(token);
      if (decoded.role === 'Vendor') {
        this.authService.isLoggedIn = true;
        this.authService.clear();
        this.authService.setToken(token);
        localStorage.setItem('email', decoded.email);
        this._router.navigateByUrl('/vsidenav');
      } else if (decoded.role === 'User') {
        this.authService.isLoggedIn = true;
        this.authService.clear();
        this.authService.setToken(token);
        localStorage.setItem('email', decoded.email);
        this._router.navigateByUrl('/usidenav/userDashboard');
      } else {

      this._router.navigate(['/getStarted']);
      }
    }
    else{
      this._router.navigate(['/getStarted']);
    }
  }
  ngOnInit() {
    const options = {
      strings: [
        'Connecting customers to couriers.',
        'Trustworthy Supply Chain Management.',
        'Speedy shipping made simple.',
      ],
      typeSpeed: 100,
      backSpeed: 20,
      showCursor: true,
      cursorChar: '|',
      loop: true
    };
    const typed = new Typed('.typed-element', options);
  }}
