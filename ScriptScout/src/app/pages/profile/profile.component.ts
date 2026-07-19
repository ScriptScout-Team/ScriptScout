import { Component } from '@angular/core';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {

  user = {
    fullName: 'Jagruti Bandikalla',
    email: 'jagruti@example.com',
    phone: '+91 9876543210',
    role: 'Frontend Developer',

    currentPassword: '',
    newPassword: '',

    language: 'English',
    theme: 'Light',

    notifications: true,
    autoSave: true
  };

  constructor() {}

  saveProfile(): void {

    console.log('Profile Data:', this.user);

    // Backend API integration will be added later

    alert('Profile updated successfully!');
  }

  resetProfile(): void {

    this.user = {
      fullName: '',
      email: '',
      phone: '',
      role: '',

      currentPassword: '',
      newPassword: '',

      language: 'English',
      theme: 'Light',

      notifications: false,
      autoSave: false
    };

    alert('Profile has been reset.');
  }

  changePhoto(): void {

    // Image upload functionality will be added later

    alert('Change Photo feature coming soon!');
  }

}