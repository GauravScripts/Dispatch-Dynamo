import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { citydata } from './CityData';

@Injectable({
  providedIn: 'root'
})
export class BookorderService {

  constructor(private http:HttpClient) { }


  getcitydetails(p:String)
  {
     return this.http.get<citydata>("http://localhost:7575/getcitydetails/"+p)
  }

  getpricedetails(p:any)
  {   
 return this.http.post("http://localhost:7575/getpricelist",p)    
  }

  generateTrackId() {
    var a =
        "TR" +
        Math.floor(Math.random() * 100) +
        Math.floor(Math.random() * 100) +
        Math.floor(Math.random() * 100) +
        Math.floor(Math.random() * 100) +
        Math.floor(Math.random() * 10) +
        Math.floor(Math.random() * 10);
    return a;
}

bookdetails:any

setbookorder(bookingdetails:any)
{
  this.bookdetails=bookingdetails
}

getbookingdetails()
{
  return this.bookdetails;
}


bookorder(p:any)
{

  return this.http.post("http://localhost:7575/addShipment",p)
}


}
