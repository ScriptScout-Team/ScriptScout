import { Component } from '@angular/core';

@Component({
selector: 'app-video-details',
templateUrl: './video-details.component.html',
styleUrls: ['./video-details.component.css']
})
export class VideoDetailsComponent {

  selectedTab: string = 'chapters';

  video = {
    title: 'AI Revolution in Healthcare',
    category: 'Technology',
    duration: '28:45',
    uploadDate: '14 July 2026',
    status: 'Processed',
    description:
      'This video explains how Artificial Intelligence is transforming healthcare with practical examples, diagnosis support and future applications.',
    language: 'English',
    resolution: '1920 x 1080',
    size: '250 MB',
    uploadedBy: 'Arjun Producer',
    tags: [
      'Artificial Intelligence',
      'Healthcare',
      'Machine Learning',
      'Medical',
      'Technology'
    ]
  };

  chapters = [
    {
      time: '00:00',
      title: 'Introduction',
      description: 'Overview of AI in Healthcare.'
    },
    {
      time: '03:25',
      title: 'Diagnosis',
      description: 'How AI helps doctors.'
    },
    {
      time: '09:40',
      title: 'Medical Imaging',
      description: 'AI in X-Ray and MRI.'
    },
    {
      time: '16:20',
      title: 'Future Scope',
      description: 'Upcoming AI technologies.'
    }
  ];

  transcripts = [
    {
      time: '00:05',
      text: "Welcome to today's AI Healthcare episode."
    },
    {
      time: '01:15',
      text: 'Artificial Intelligence is changing hospitals.'
    },
    {
      time: '05:30',
      text: 'Doctors use AI for diagnosis support.'
    },
    {
      time: '12:10',
      text: 'Machine Learning improves prediction accuracy.'
    }
  ];

  selectTab(tab: string): void {
    this.selectedTab = tab;
  }

}