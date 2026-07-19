import { Component } from '@angular/core';

@Component({
  selector: 'app-chapter-breakdown',
  templateUrl: './chapter-breakdown.component.html',
  styleUrls: ['./chapter-breakdown.component.css']
})
export class ChapterBreakdownComponent {

  chapters = [
    {
      start: '00:00',
      end: '02:15',
      title: 'Introduction'
    },
    {
      start: '02:16',
      end: '06:45',
      title: 'What is Artificial Intelligence?'
    },
    {
      start: '06:46',
      end: '11:30',
      title: 'AI Applications in Healthcare'
    },
    {
      start: '11:31',
      end: '16:20',
      title: 'Benefits and Challenges'
    },
    {
      start: '16:21',
      end: '20:00',
      title: 'Future of AI'
    }
  ];

  constructor() {}

  generateChapters(): void {

    // Backend AI integration will be added later

    alert('AI is generating chapters...');

  }

  addChapter(): void {

    this.chapters.push({

      start: '',
      end: '',
      title: ''

    });

  }

  deleteChapter(index: number): void {

    this.chapters.splice(index, 1);

  }

  resetChapters(): void {

    this.chapters = [];

    alert('All chapters have been removed.');

  }

  saveChapters(): void {

    console.log('Chapter Data:', this.chapters);

    // Backend API integration will be added later

    alert('Chapters saved successfully!');

  }

}