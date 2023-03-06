export class Vendor {
    id: number;
    name: string;
    emailId: string;
    companyName: string;
    officeContact: string;
    address:{
        addressLine1: string;
        city: string;
        state: string;
        country: string;
        zipcode: number;
    }
}