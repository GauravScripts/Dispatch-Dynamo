import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators, FormBuilder } from '@angular/forms';
import { Router, Routes } from '@angular/router';
import { BookorderService } from '../bookorder/bookorder.service';
declare var Stripe: any;

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {
  notificationProps = {
    title: 'Password expiring',
    content: 'Your password is about to expire in n days.',
    timeOut: 5000,
    showProgressBar: true,
    pauseOnHover: true,
    clickToClose: true,
    animate: 'fromRight',
    preventDuplicates : true,
    icons : 'alert'
  };
  create() {

    const temp = this.notificationProps;
    const title = temp.title;
    const content = temp.content;
  }
  paymentForm: FormGroup;
  cardTypes = ['Credit Card', 'Debit Card'];
  
  constructor(private fb: FormBuilder,private http:HttpClient,private service:BookorderService,private route:Router) {}

  ngOnInit() {
    this.paymentForm = this.fb.group({
      cardType: ['', Validators.required],
      cardholderName: ['', Validators.required],
      cardNumber: ['', [Validators.required, Validators.pattern('/^[0-9]{16}$/')]],
      expMonth: ['', [Validators.required, Validators.pattern('[0-9]{2}')]],
      expYear: ['', [Validators.required, Validators.pattern('[0-9]{4}')]],
      cvc: ['', [Validators.required, Validators.pattern('[0-9]{3}')]]
    });
  }

  get cardTypeControl() { return this.paymentForm.get('cardType'); }
  get cardholderNameControl() { return this.paymentForm.get('cardholderName'); }
  get cardNumberControl() { return this.paymentForm.get('cardNumber'); }
  get expMonthControl() { return this.paymentForm.get('expMonth'); }
  get expYearControl() { return this.paymentForm.get('expYear'); }
  get cvcControl() { return this.paymentForm.get('cvc'); }

  chargeCard(token: string) {
    this.service.bookorder(this.service.getbookingdetails()).subscribe(data=>console.log(data))
    this.create();
    alert("You Shipment is Successfully Booked Check Your mail for Tracking id And Shipment details")
    this.route.navigateByUrl('usidenav/userDashboard');
  }

 
  submitForm() {
      const form = document.getElementsByTagName('form')[0];

      Stripe.card.createToken({
        number: form['cardNumber'].value,
        exp_month: form['expMonth'].value,
        exp_year: form['expYear'].value,
        cvc: form['cvc'].value
      }, (status: number, response: any) => {
        if (status === 200) {
          const token = response.id;
          this.chargeCard(token);
        } else {
          alert(response.error.message)
        }
      });
  }

  clearForm() {
    this.paymentForm.reset();
  }

}
