import { Component } from '@angular/core';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-recommendation',
  templateUrl: './recommendation.component.html',
  styleUrls: ['./recommendation.component.css']
})
export class RecommendationComponent {

city:any;
constructor(private userservice:UserService){
  this.userservice.getalluserdetails().subscribe(
    response=>{
      this.city=response;
      console.log(this.city);
    }
  )
};
cities:any;

showClearButton:boolean=false;
showCommonTable:boolean=true;
showSearchTable:boolean=false;
searchcities:any;
search:any;
searchText(){
  this.showCommonTable=false;
  this.showSearchTable=true;
  this.showClearButton=true;
 
this.userservice.getuserbycity(this.search).subscribe(
  response=>{
    this.searchcities=response;
    console.log(this.searchcities);
    
  }
)
}
clearcities(){
  this.showCommonTable=true;
  this.showSearchTable=false;
  this.showClearButton=false;
  this.search="";
}
}
  
  



  
