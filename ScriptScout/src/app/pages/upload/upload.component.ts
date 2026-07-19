import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent {
  constructor(private router: Router) {}


  selectedFile: File | null = null;

  video = {
    title: '',
    category: '',
    language: '',
    description: ''
  };

  // Browse File
  onFileSelected(event: any): void {

    if (event.target.files.length > 0) {
      this.selectedFile = event.target.files[0];
    }

  }

  // Drag Over
  onDragOver(event: DragEvent): void {

    event.preventDefault();

  }

  // Drop File
  onDrop(event: DragEvent): void {

    event.preventDefault();

    if (event.dataTransfer && event.dataTransfer.files.length > 0) {

      this.selectedFile = event.dataTransfer.files[0];

    }

  }

  // Remove File
  removeFile(): void {

    this.selectedFile = null;

  }

  // Reset Form
  resetForm(): void {

    this.selectedFile = null;

    this.video = {
      title: '',
      category: '',
      language: '',
      description: ''
    };

  }

  // Upload Button
  uploadVideo(): void {

    if (!this.selectedFile) {

      alert('Please select a video.');

      return;

    }

    console.log('Uploading:', this.selectedFile);

    console.log(this.video);

    alert('Video Uploaded Successfully (Demo)');
    this.router.navigate(['/media-library']);
  }

}