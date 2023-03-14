import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { data, event } from 'jquery';
import { VServicesService } from '../v-services.service';
import { Vendor } from 'src/app/module/vender';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable, ReplaySubject, Subscriber } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-v-profile',
  templateUrl: './v-profile.component.html',
  styleUrls: ['./v-profile.component.css']
})
export class VProfileComponent  {
[x: string]: any;

  

  vendor:Vendor={
    id: 0,
    name: '',
    emailId: '',
    companyName: '',
    officeContact: '',
    address: {
      addressLine1: '',
      city: '',
      state: '',
      country: '',
      zipcode: 0
    }
  };

  files:any[];
  selectedFile: File;
  base64code: any;
  myImage!: Observable<any>;

  constructor(public V_service: VServicesService, private snackBar: MatSnackBar, private http: HttpClient){

    
  }
  
  ngOnInit(): void {
    this.save();
    this.getImage();
    
    $('.js-edit').on('click', function () {
      
      $('.inpt').prop('disabled', false);
      $('#edit').hide();
      $('#submit_B').show();
      $('#cancle').show();
      });

    $('#submit_B').on('click', function() {

      $('.inpt').prop('disabled', true);
      $('#cancle').hide();
      $('#submit_B').hide();
      $('#edit').show();
    });
      

    }
    
  save() {
    this.V_service.getvendordetails().subscribe((data: Vendor) => {
      this.vendor = data
    });
  }


  update(updateVendorDetails:NgForm){
    this.V_service.updatevendordetails(updateVendorDetails.value).subscribe(data =>
      console.log(data));
  }

  imageUrl = 'https://bootdey.com/img/Content/avatar/avatar7.png';
  base64Output: string;



  onFileSelected(event:any) {
    console.log(event)
    console.log(event.target.files[0])
    this.convertFile(event.target.files[0]).subscribe(base64 => {
      this.base64Output = base64;
      this.V_service.addImage(this.base64Output).subscribe({ next: _data => this.addImagesnack() });
      this.getImage();
      window.location.reload(); 
    });
  }

  addImagesnack() {
    this.snackBar.open('Image Successfully added ', 'success', {
      duration: 3000,
      panelClass: ['mat-toolbar', 'mat-primary']
    })
  }

  convertFile(file: File): Observable<string> {
    console.log(file);
    const result = new ReplaySubject<string>(1);
    const reader = new FileReader();
    reader.readAsBinaryString(file);
    reader.onload = (event) => result.next(btoa(event.target.result.toString()));
    console.log(result);
    return result;
  }


  getImage(){
    this.V_service.getImage().subscribe(data =>{
      this.base64code=data;
        let imageUrl = "data:image/png;base64,"+this.base64code.file;
        const ele = document.getElementById("image") as HTMLImageElement;
        const src = ele.src = imageUrl;
            
    });
  }



  deleteImage(){
    this.V_service.deleteImage().subscribe({ next: data => this.deleteImagesnack() });
    window.location.reload(); 
  }

  deleteImagesnack(){
    this.snackBar.open('Image Deleted Successfully', 'success', {
      duration: 3000,
      panelClass: ['mat-toolbar', 'mat-primary']
    })
  }
}
