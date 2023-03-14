import { HttpClient } from "@angular/common/http";
import { Component } from "@angular/core";
import { FormBuilder, FormGroup } from "@angular/forms";
import { VServicesService } from "src/app/Vendor/v-services.service";
// import { Chart } from "chart.js";

@Component({
    selector: "app-user-dashboard",
    templateUrl: "./user-dashboard.component.html",
    styleUrls: ["./user-dashboard.component.css"],
})
export class UserDashboardComponent {
    beRecommendationAppBaseUrl="http://localhost:8081/api/neo4j";
    imageUrl = 'https://bootdey.com/img/Content/avatar/avatar7.png';
    constructor(private http: HttpClient, private V_service:VServicesService)
    {
this.getusercity()
    }
usercity:any
getusercity()
{
    var city:any

    this.http.get("http://localhost:8085/user-profile/getUserCityById/"+localStorage.getItem("email"),{responseType: 'text'}).subscribe(data=>
    {
        city=data
        this.getallvendors(city)
    })
     
 
}
searchcities:any
getallvendors(p:any)
{

this.http.get("http://localhost:8083/api/vendor/filteredVendorByCity/"+p).subscribe(data=>this.searchcities=data);
}



}
