import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent {

  password = '';

  confirmPassword = '';

  constructor(private router: Router){}

  resetPassword(){

    if(this.password==='' || this.confirmPassword===''){

      alert("Please fill all fields");

      return;

    }

    if(this.password!==this.confirmPassword){

      alert("Passwords do not match");

      return;

    }

    alert("Password Updated Successfully");

    this.router.navigate(['/login']);

  }

}