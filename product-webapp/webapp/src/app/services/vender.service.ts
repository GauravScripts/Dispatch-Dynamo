import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class VenderService {

  constructor(private httpClient: HttpClient) { }
  getallcities()
  {
   return this.httpClient.get("http://localhost:8083//api/vendor/getallcities");
  }

  deletecit(p:any)
  {

    return this.httpClient.delete("http://localhost:8083/api/vendor/deletecity/"+p)
  }

  addCity(p:any)
  {
    alert(p)
    return this.httpClient.put("http://localhost:8083/api/vendor/city",p)
  }

  getprice()
  {
    return this.httpClient.get("http://localhost:8083/api/vendor/getPrice")
  }

  updateprice(p:any)
  {
    return this.httpClient.post("http://localhost:8083/api/vendor/addprice",p)
  }


  beRecommendationAppBaseUrl="http://localhost:7575"
  getallshipments(trackid:any){
    return this.httpClient.get(this.beRecommendationAppBaseUrl+"/getshipment/"+trackid)
  }

}
