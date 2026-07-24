import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent {

  constructor(private router: Router) {}

   logout(): void {
  const confirmLogout = confirm('Are you sure you want to logout?');

  if (confirmLogout) {
    // Clear session or local storage if used
    localStorage.clear();
    sessionStorage.clear();

    // Navigate to login page
    this.router.navigate(['/login']);
  }
}

}