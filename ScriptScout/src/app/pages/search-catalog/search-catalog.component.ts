import { Component } from '@angular/core';

@Component({
  selector: 'app-search-catalog',
  templateUrl: './search-catalog.component.html',
  styleUrls: ['./search-catalog.component.css']
})
export class SearchCatalogComponent {

  searchText: string = '';
  selectedCategory: string = '';
  selectedLanguage: string = '';

  videos = [
    {
      thumbnail: 'assets/images/video1.jpg',
      title: 'AI Revolution in Healthcare',
      category: 'Technology',
      language: 'English',
      duration: '18:45',
      status: 'Processed'
    },
    {
      thumbnail: 'assets/images/video2.jpg',
      title: 'Angular Complete Tutorial',
      category: 'Education',
      language: 'English',
      duration: '25:30',
      status: 'Processed'
    },
    {
      thumbnail: 'assets/images/video3.jpg',
      title: 'Machine Learning Basics',
      category: 'Technology',
      language: 'Hindi',
      duration: '15:20',
      status: 'Pending'
    },
    {
      thumbnail: 'assets/images/video4.jpg',
      title: 'Cloud Computing Overview',
      category: 'Technology',
      language: 'English',
      duration: '21:10',
      status: 'Processed'
    },
    {
      thumbnail: 'assets/images/video5.jpg',
      title: 'Health & Nutrition Guide',
      category: 'Healthcare',
      language: 'Telugu',
      duration: '13:50',
      status: 'Processed'
    }
  ];

  filteredVideos = [...this.videos];

  constructor() {}

  searchVideos(): void {

    this.filteredVideos = this.videos.filter(video => {

      const matchesTitle =
        video.title.toLowerCase().includes(this.searchText.toLowerCase());

      const matchesCategory =
        !this.selectedCategory ||
        video.category === this.selectedCategory;

      const matchesLanguage =
        !this.selectedLanguage ||
        video.language === this.selectedLanguage;

      return matchesTitle && matchesCategory && matchesLanguage;
    });

  }

  resetFilters(): void {

    this.searchText = '';
    this.selectedCategory = '';
    this.selectedLanguage = '';

    this.filteredVideos = [...this.videos];

  }

  viewVideo(video: any): void {

    alert('Opening "' + video.title + '"');

    console.log(video);

  }

}
