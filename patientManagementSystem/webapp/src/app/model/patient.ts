export class Patient {
    id?: string;
    name: string;
    gender: any;
    age: number;
    bloodGroup: string;
    contactNumber: string;
    email: string;
    address: string;
    description: string;
  
    constructor(
      name: string,
      gender: any,
      age: number,
      bloodGroup: string,
      contactNumber: string,
      email: string,
      address: string,
      description: string
    ) {
      this.name = name;
      this.gender = gender;
      this.age = age;
      this.bloodGroup = bloodGroup;
      this.contactNumber = contactNumber;
      this.email = email;
      this.address = address;
      this.description = description;
    }
  }
  