import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  get(user: any) {
    return this.http.post('http://localhost:9201/api/v1/auth/genratetoken', user);
  }
}
