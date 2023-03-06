import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  constructor(private http:HttpClient) { }
  post(customer:any){
    return this.http.post('http://localhost:9201/api/v1/auth/adduser',customer);
  }

}
