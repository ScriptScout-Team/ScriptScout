import { Component } from '@angular/core';
import { VideoService } from '../../services/video.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent {
  cards = [
    {
      title: 'Total Videos',
      value: 128,
      change: '+12%',
      color: 'blue',
    },

    {
      title: 'Uploading',
      value: 8,
      change: '+1%',
      color: 'green',
    },

    {
      title: 'Processing',
      value: 5,
      change: 'In Progress',
      color: 'orange',
    },

    {
      title: 'Metadata Generated',
      value: 98,
      change: '+18%',
      color: 'purple',
    },
  ];

  processingVideos = [
    {
      title: 'AI Revolution in Healthcare',
      status: 'Processing',
      progress: 60,
    },

    {
      title: 'Future of Space Travel',
      status: 'Processing',
      progress: 40,
    },

    {
      title: 'Documentary: Ocean Life',
      status: 'Uploading',
      progress: 20,
    },

    {
      title: 'Machine Learning Basics',
      status: 'Metadata Generated',
      progress: 80,
    },
  ];

  totalVideos = 0;

  constructor(private videoService: VideoService) {}

  ngOnInit() {
    this.totalVideos = this.videoService.getVideos().length;
  }
}
