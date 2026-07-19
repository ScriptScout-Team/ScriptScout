import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-metadata-editor',
  templateUrl: './metadata-editor.component.html',
  styleUrls: ['./metadata-editor.component.css']
})
export class MetadataEditorComponent {

  metadata = {
    title: 'AI Revolution in Healthcare',
    category: 'Technology',
    subCategory: 'Healthcare',
    videoCategory: 'English',

    description:
      'This episode explores how Artificial Intelligence is revolutionizing healthcare through diagnosis, treatment, automation, and future possibilities.',

    language: 'English',
    rating: 'PG',
    visibility: 'Public',

    tags: [
      'AI',
      'Healthcare',
      'Machine Learning',
      'Medical Technology',
      'Future of Healthcare'
    ]
  };

  constructor(private router: Router) {}

  saveMetadata(): void {
    console.log('Saved Metadata:', this.metadata);

    alert('Metadata saved successfully!');

    // Later replace with API call
  }

  resetForm(): void {

    this.metadata = {
      title: '',
      category: '',
      subCategory: '',
      videoCategory: '',
      description: '',
      language: '',
      rating: '',
      visibility: '',
      tags: []
    };

  }

  regenerateMetadata(): void {

    alert('AI Regeneration Started...');

    // Later connect with AI API

  }

  addKeyword(): void {

    const keyword = prompt('Enter new keyword');

    if (keyword && keyword.trim()) {

      this.metadata.tags.push(keyword.trim());

    }

  }

  removeKeyword(index: number): void {

    this.metadata.tags.splice(index, 1);

  }

  backToVideo(): void {

    this.router.navigate(['/video-player', 1]);

    // Replace 1 with actual video id after backend integration

  }

}