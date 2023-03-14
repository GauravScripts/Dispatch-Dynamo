import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import * as echarts from 'echarts';
import { data } from 'jquery';
import jwt_decode from "jwt-decode";
import { AuthService } from 'src/app/sign-up-sign-in/user-login-registration-service/auth.service';



@Component({
  selector: 'app-v-dashboard',
  templateUrl: './v-dashboard.component.html',
  styleUrls: ['./v-dashboard.component.css']
})
export class VDashboardComponent implements OnInit {



  constructor(private http: HttpClient, private authService:AuthService) { }

  ngOnInit(): void {
    interface DecodedToken {
      user_email: string;
      User: {
        empty: boolean;
        present: boolean;
      };
      role: string;
      iat: number;
    }


    let decoded = jwt_decode(localStorage.getItem("Token")) as DecodedToken;



    
    // Call the API endpoint to fetch the chart data
    this.http.get<any>('http://localhost:7575/countDailyShipmentOfVendor/'+decoded.user_email).subscribe(data => {

   
      // Create the chart using the fetched data
      var chartDom = document.getElementById('main');
      var myChart = echarts.init(chartDom);
      var option;

      option = {
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: [
          {
            type: 'category',
            data: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
            axisTick: {
              alignWithLabel: true
            }
          }
        ],
        yAxis: [
          {
            type: 'value'
          }
        ],
        series: [
          {
            name: 'Direct',
            type: 'bar',
            barWidth: '50%',
            data: [0,0,data,0,0,0,0,0,0,0,0,0], // Use the values from the fetched data
          }
        ]
      };

      option && myChart.setOption(option);
    });
  }

}

  


