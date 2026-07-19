import { Component, OnInit } from '@angular/core';

interface Video {
  id: number;
  title: string;
  category: string;
  duration: string;
  status: string;
  uploaded: string;
  thumbnail: string;
}

@Component({
  selector: 'app-media-library',
  templateUrl: './media-library.component.html',
  styleUrls: ['./media-library.component.css']
})
export class MediaLibraryComponent implements OnInit {

  searchText: string = '';
  selectedStatus: string = 'All';

  videos: Video[] = [];

  filteredVideos: Video[] = [];

  ngOnInit(): void {

    this.videos = [

      {
        id: 1,
        title: 'AI Revolution in Healthcare',
        category: 'Technology',
        duration: '28:45',
        status: 'Completed',
        uploaded: 'Today',
        thumbnail: 'assets/images/video1.jpg'
      },

      {
        id: 2,
        title: 'Future of Artificial Intelligence',
        category: 'Education',
        duration: '18:22',
        status: 'Processing',
        uploaded: 'Yesterday',
        thumbnail: 'assets/images/video2.jpg'
      },

      {
        id: 3,
        title: 'Machine Learning Basics',
        category: 'Technology',
        duration: '35:12',
        status: 'Completed',
        uploaded: '2 days ago',
        thumbnail: 'assets/images/video3.jpg'
      },

      {
        id: 4,
        title: 'Deep Learning Crash Course',
        category: 'Education',
        duration: '42:55',
        status: 'Failed',
        uploaded: '3 days ago',
        thumbnail: 'assets/images/video4.jpg'
      }

    ];

    this.filteredVideos = [...this.videos];

  }

  searchVideos(): void {

    this.applyFilters();

  }

  filterVideos(): void {

    this.applyFilters();

  }

  applyFilters(): void {

    this.filteredVideos = this.videos.filter(video => {

      const matchesSearch = video.title
        .toLowerCase()
        .includes(this.searchText.toLowerCase());

      const matchesStatus =
        this.selectedStatus === 'All'
        || video.status === this.selectedStatus;

      return matchesSearch && matchesStatus;

    });

  }

  deleteVideo(id: number): void {

    const confirmDelete = confirm('Delete this video?');

    if (!confirmDelete) {
      return;
    }

    this.videos = this.videos.filter(video => video.id !== id);

    this.applyFilters();

  }

}