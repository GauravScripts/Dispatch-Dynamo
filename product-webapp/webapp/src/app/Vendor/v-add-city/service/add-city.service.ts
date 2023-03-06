import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import * as countrycitystatejson from 'countrycitystatejson';

@Injectable({
  providedIn: 'root'
})
export class AddCityService {

  constructor(private httpClient: HttpClient) { }

  private countryData = countrycitystatejson;

  getCountries() {
    return this.countryData.getCountries();
  }

  getStatesByCountry(countryShotName: string) {
    return this.countryData.getStatesByShort(countryShotName);
  }

  getCitiesByState(country: string, state: string) {
    return this.countryData.getCities(country, state);
  }

  getallcities() {
    let httpHeaders = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('Token')
    });
    let requestoption = { headers: httpHeaders };
    return this.httpClient.get<Array<string>>("http://localhost:8083/api/vendor/getallcities", requestoption);
  }
  deleteCity(p: any) {
    let httpHeaders = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('Token')
    });
    let requestoption = { headers: httpHeaders };
    return this.httpClient.delete("http://localhost:8083/api/vendor/deletecity/" + p, requestoption)
  }
  addCity(city: any) {
    let httpHeaders = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('Token')
    });
    let requestoption = { headers: httpHeaders };
    return this.httpClient.put("http://localhost:8083/api/vendor/city", city, requestoption)
  }
}
