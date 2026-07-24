import { Component } from '@angular/core';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent {

  settings = {

    // General
    workspaceName: 'ScriptScout',
    language: 'English',
    theme: 'Light',
    timezone: 'Asia/Kolkata',

    // Notifications
    emailNotifications: true,
    processingAlerts: true,
    weeklyReports: false,

    // AI Processing
    autoTranscript: true,
    autoMetadata: true,
    autoChapters: true,

    // Security
    currentPassword: '',
    newPassword: ''

  };

  

  saveSettings(): void {

    console.log('Settings:', this.settings);

    // Future Backend API Integration

    alert('Settings saved successfully!');

  }

  resetSettings(): void {

    this.settings = {

      workspaceName: 'ScriptScout',
      language: 'English',
      theme: 'Light',
      timezone: 'Asia/Kolkata',

      emailNotifications: false,
      processingAlerts: false,
      weeklyReports: false,

      autoTranscript: false,
      autoMetadata: false,
      autoChapters: false,

      currentPassword: '',
      newPassword: ''

    };

    alert('Settings reset successfully!');

  }

}