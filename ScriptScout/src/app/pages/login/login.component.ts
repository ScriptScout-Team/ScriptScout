import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  email: string = '';
  password: string = '';
  rememberMe: boolean = false;
  showPassword: boolean = false;

  constructor(private router: Router) {}

  login(): void {

    if (!this.email) {
      alert('Please enter your email');
      return;
    }

    if (!this.password) {
      alert('Please enter your password');
      return;
    }

    // Demo Login
    if (
      this.email === 'admin@scriptscout.com' &&
      this.password === 'admin123'
    ) {

      alert('Login Successful');

      this.router.navigate(['/dashboard']);

    } else {

      alert('Invalid Email or Password');

    }

  }

  loginWithGoogle(): void {

    alert('Google Sign In will be added later.');

  }

  togglePassword(): void {

    this.showPassword = !this.showPassword;

  }

}