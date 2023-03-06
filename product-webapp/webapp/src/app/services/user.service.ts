import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  
  constructor(private httpClinte: HttpClient) { }
  beRecommendationAppBaseUrl="http://localhost:8081/api/neo4j";
  getalluserdetails(){
    return this.httpClinte.get(this.beRecommendationAppBaseUrl+"/getalluserdetails");
  }
  getuserbycity(cityobj : any){
    return this.httpClinte.get(this.beRecommendationAppBaseUrl+"/Users-By-City/"+cityobj);
  }
  // getSearchedProperties(search) {
  //   return this.httpClinte.get(this. beRecommendationAppBaseUrl+ "/Users-By-City" + search);
  // }

  signup(signup: any) {
    return this.httpClinte.post("http://localhost:9201/adduser",signup);
  }

  loginCheck(logindata: any) {
    return this.httpClinte.post("http://localhost:9201/genratetoken", logindata);
  }
  getUserByEmailId(emailId:any){
    return this.httpClinte.get("http://localhost:8085/user-profile/getUserById/"+emailId);
  }
  updateUser(EmailValidator:any,userDetails:any){
    return this.httpClinte.put("http://localhost:8085/user-profile/updateUserById/"+EmailValidator,userDetails)
  }
}
