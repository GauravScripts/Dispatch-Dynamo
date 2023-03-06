import {Component, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';
import { AddCityService } from './service/add-city.service';
import {MatDialog} from '@angular/material/dialog';
import {MatSnackBar} from '@angular/material/snack-bar';
import {ConfirmDialogComponent} from './confirm-dialog/confirm-dialog.component';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';


@Component({
  selector: 'app-v-add-city',
  templateUrl: './v-add-city.component.html',
  styleUrls: ['./v-add-city.component.css']
})
export class VAddCityComponent implements OnInit {


  constructor(private addcityservice: AddCityService, private dialog: MatDialog, private snackBar: MatSnackBar) {
    this.getallcitie();
    let c = this.addcityservice.getallcities();
    c.subscribe(data => {
      this.citiee = data;
    });


    this.countries = this.addcityservice.getCountries();
    this.form = new FormGroup({
      country: this.country,
      state: this.state,
      city: this.city,
    });
    // const users: UserData[] = [];
    // for (let i = 1; i <= 100; i++) { users.push(this.createNewUser(i)); }
// for (let i = 0; i < this.citiee.length; i++) {
// let a:string =i.toString();
// let b:string =this.citiee[i];
//   users.push(
//       {
//         id: a,
//         name: b
//       }
//   );
// }
//     this.dataSource = new MatTableDataSource(users);

  }

  citiee: any = [];
  countries: any;
  states: string[];
  cities: string[];

  //form se aane wali city
  form: FormGroup;
  country = new FormControl(null, [Validators.required]);
  state = new FormControl({ value: null, disabled: true }, [Validators.required]);
  city = new FormControl({ value: null, disabled: true }, [Validators.required]);


  ngOnInit() {
    this.country.valueChanges.subscribe((country) => {
      this.state.reset();
      this.state.disable();
      if (country) {
        this.states = this.addcityservice.getStatesByCountry(country);
        this.state.enable();
      }
    });

    this.state.valueChanges.subscribe((state) => {
      this.city.reset();
      this.city.disable();
      if (state) {
        this.cities = this.addcityservice.getCitiesByState(this.country.value, state);
        this.city.enable();
      }
    });

    this.city.valueChanges.subscribe((city) => {
      this.addCity(city);

    });

  }

  getallcitie() {
    this.addcityservice.getallcities().subscribe(data => {
      this.citiee = data;

      const users: UserData[] = [];
      for(let i = 0; i < this.citiee.length; i++) {
        let a:string =i.toString();
        let b:string =this.citiee[i];

        users.push(
            {
              id: a,
              name: b
            }
        );
      }
      this.dataSource = new MatTableDataSource(users);
    });


  }

  deleteCity(p: any) {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: '250px',
      data: { message: `Are you sure you want to delete ${p}?` }

    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.addcityservice.deleteCity(p).subscribe(data => {

          this.cities = this.cities.filter(c => c !== p);
          this.snackBar.open(`${p} deleted.`, 'Close', { duration: 2000 });
          this.getallcitie();
        });
      }
    });
  }




  addCity(city: string) {


    this.addcityservice.addCity(city).subscribe(data => {
      this.cities.push(city);
      city = '';
      this.getallcitie();
    });
  }
  displayedColumns = ['id', 'name', 'Action'];
  dataSource: MatTableDataSource<UserData>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
}



export interface UserData {
  id: string;
  name: string;
}
