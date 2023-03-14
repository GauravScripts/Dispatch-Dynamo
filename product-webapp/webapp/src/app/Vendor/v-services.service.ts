import { ObserversModule } from '@angular/cdk/observers';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { vector } from 'echarts';
import { Observable } from 'rxjs';
import { Vendor } from '../module/vender';

@Injectable({
  providedIn: 'root'
})
export class VServicesService {


  constructor(private httpClient: HttpClient) { }

  getvendordetails():Observable<Vendor> {
    let httpHeaders = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('Token')
    });
    let requestOptions = { headers: httpHeaders }
    return this.httpClient.get<Vendor>("http://localhost:8083/api/vendor/getvendor", requestOptions)
  }

  updatevendordetails(updateVendorDetails:any){
    let httpHeaders = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('Token')
    });
    let requestOptions = { headers: httpHeaders }
    return this.httpClient.post("http://localhost:8083/api/vendor/patchVendorDetails", updateVendorDetails,requestOptions)
  }

  addCity(city: string){
    let httpHeaders = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('Token')
    });
    let requestOptions = { headers: httpHeaders }
    return this.httpClient.put("http://localhost:8083/api/vendor/city", city,requestOptions)
  }

  deleteCity(city: string) {
    let httpHeaders = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('Token')
    });
    let requestOptions = { headers: httpHeaders }
    return this.httpClient.delete("http://localhost:8083/api/vendor/deletecity/"+city , requestOptions)
  }

  getAllCities(){
    let httpHeaders = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('Token')
    });
    let requestOptions = { headers: httpHeaders }
    return this.httpClient.get("http://localhost:8083/api/vendor/getallcities", requestOptions)
  }

  updatePrice(updateprice:number){
    let httpHeaders = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('Token')
    });
    let requestOptions = { headers: httpHeaders }
    return this.httpClient.post("http://localhost:8083/api/vendor/addprice", updateprice,requestOptions)
  }

  getInternationalPrice(){
    let httpHeaders = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('Token')
    });
    let requestOptions = { headers: httpHeaders }
    return this.httpClient.get("http://localhost:8083/api/vendor/getInternationalPrice", requestOptions)
  }
  getDomesticPrice(){
    let httpHeaders = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('Token')
    });
    let requestOptions = { headers: httpHeaders }
    return this.httpClient.get("http://localhost:8083/api/vendor/getDomesticPrice", requestOptions)
  }

  addImage(data: any) {

    let email=localStorage.getItem('email')
    return this.httpClient.post("http://localhost:8083/profileImage/"+email,data);
  }

  getImage() {
    let email = localStorage.getItem('email')
    return this.httpClient.get("http://localhost:8083/getprofileImage/"+email);
  }

  deleteImage() {
    let email = localStorage.getItem('email')
    return this.httpClient.delete("http://localhost:8083/deleteprofileImage/"+email);
  }

}
