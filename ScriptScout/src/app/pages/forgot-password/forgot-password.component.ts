import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent {

  email: string = '';

  constructor(private router: Router) {}

  sendResetLink() {

    if (this.email === '') {

      alert('Please enter your email');

      return;

    }

    this.router.navigate(['/reset-password']);

  }

  backToLogin() {

    this.router.navigate(['/login']);

  }

}