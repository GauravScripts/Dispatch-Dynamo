import { HttpClient } from "@angular/common/http";
import { Component } from "@angular/core";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { MatPaginator, PageEvent } from "@angular/material/paginator";
import { MatTableDataSource } from "@angular/material/table";
import { Router} from "@angular/router";
import { BookorderService } from "./bookorder.service";

// import { Order } from './Order';

@Component({
    selector: "app-bookorder",
    templateUrl: "./bookorder.component.html",
    styleUrls: ["./bookorder.component.css"],
})
export class BookorderComponent {

    transportForm: FormGroup;
    pageSize = 10;
    pageIndex = 0;

    currentStep = 1;
    constructor(private formBuilder: FormBuilder, private http: HttpClient,private service:BookorderService,private route:Router) {
        this.transportForm = this.formBuilder.group({
            weight: ["", Validators.required],
            length:["",Validators.required],
            breadth:["",Validators.required],
            height:["",Validators.required],
            from: this.formBuilder.group({
                addressLine1: ["", Validators.required],
                city: ["", Validators.required],
                state: ["", Validators.required],
                country: ["", Validators.required],
                zipcode: ["", Validators.required],
            }),
            to: this.formBuilder.group({
                addressLine1: ["", Validators.required],
                city: ["", Validators.required],
                state: ["", Validators.required],
                country: ["", Validators.required],
                zipcode: ["", Validators.required],
            }),
            modeOfTransport: ["", Validators.required],
            servicetype:["",Validators.required],
            shipmentType:["",Validators.required]
        });
    }

    vendors: any;
    dataSource = new MatTableDataSource();
    longitudecity1:any
    latitudecity1:any
    longitudecity2:any
    latitudecity2:any


    ngOnInit() {
        this.dataSource.data = this.vendors;
    }

    show=false;
    onSubmit() {
        console.log(this.transportForm.value);
        this.show=true;
        this.service.getcitydetails(this.transportForm.value.from.city).subscribe(data=>
            {
            this.latitudecity1=data.lat
            this.longitudecity1=data.lon
            }
            );
        this.service.getcitydetails(this.transportForm.value.to.city).subscribe(data=>
            {
            this.latitudecity2=data.lat
            this.longitudecity2=data.lon
        });


        var data = {
            weight:this.transportForm.value.weight,
            length:this.transportForm.value.length,
            breadth:this.transportForm.value.breadth,
            height:this.transportForm.value.height,
            modeOfTransport:this.transportForm.value.modeOfTransport,
            from: this.transportForm.value.from,
            to: this.transportForm.value.to,
            shipmentType:this.transportForm.value.shipmentType
        };

        if(this.transportForm.value.shipmentType=="International")
        {
            this.sign=this.dollar
        }
        else
        if(this.transportForm.value.shipmentType=="Domestic")
        {
            this.sign=this.rupee
        }
        else
        {
            this.sign="";
        }
        console.log(data)
        this.service.getpricedetails(data).subscribe(data=>this.vendors=data)
    }
rupee:String="fa-sharp fa-solid fa-indian-rupee-sign"
dollar:String="fa-sharp fa-solid fa-dollar-sign"
sign:String=""   



    bookOrder(p) {
        var bookingdetails = {
            weight:this.transportForm.value.weight,
            length:this.transportForm.value.length,
            breadth:this.transportForm.value.breadth,
            height:this.transportForm.value.height,
            servicetype:this.transportForm.value.servicetype,
            trackId: this.service.generateTrackId(),
            userId: localStorage.getItem("email"),
            vendorId: p.vendorId,
            modeOfTransport: p.modeOfTransport,
            price: p.price,
            fromAddress: this.transportForm.value.from,
            toAddress: this.transportForm.value.to,
            paymentType:this.transportForm.value.shipmentType
        };
        console.log(bookingdetails);
        
        this.service.setbookorder(bookingdetails);
        this.route.navigateByUrl('payment');
    }


    nextStep() {
        this.currentStep++;
    }

    previousStep() {
        this.currentStep--;
    }

    onPageChange(event: PageEvent) {
        this.pageSize = event.pageSize;
        this.pageIndex = event.pageIndex;
    }


    displayedColumns: string[] = [
        "vendorId",
        "vendorName",
        "vendorContact",
        "modeOfTransport",
        "price",
        "bookShipment",
    ];
    
}
