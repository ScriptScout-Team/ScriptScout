import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {

  fullName: string = '';
  email: string = '';
  phone: string = '';
  password: string = '';
  confirmPassword: string = '';

  constructor(private router: Router){}

  register(){

    if(
      this.fullName==='' ||
      this.email==='' ||
      this.phone==='' ||
      this.password==='' ||
      this.confirmPassword===''
    ){

      alert("Please fill all fields");
      return;

    }

    if(this.password!==this.confirmPassword){

      alert("Passwords do not match");
      return;

    }

    alert("Registration Successful");

    this.router.navigate(['/login']);

  }

  

}