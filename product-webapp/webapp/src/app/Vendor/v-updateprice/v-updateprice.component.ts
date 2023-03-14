import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

import { price } from 'src/app/module/price';
import { VServicesService } from '../v-services.service';

@Component({
  selector: 'app-v-updateprice',
  templateUrl: './v-updateprice.component.html',
  styleUrls: ['./v-updateprice.component.css']
})
export class VUpdatepriceComponent {
  price1:any
  price2:any

  constructor(public V_service: VServicesService){
    this.getDomesticPrice();
    this.getInternationalPrice();
  }

  

  update(updateprice:NgForm){

    this.V_service.updatePrice(updateprice.value).subscribe(data => 
      {

        this.getInternationalPrice();
        this.getDomesticPrice();
      }) 
  }

  getInternationalPrice(){
    this.V_service.getInternationalPrice().subscribe((data:any) =>{
      this.price1=data

    });
  }

  getDomesticPrice(){
    this.V_service.getDomesticPrice().subscribe((data:any) =>{
      this.price2=data
      console.log(this.price2);
    });
  }
}
