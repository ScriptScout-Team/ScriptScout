import { Injectable } from '@angular/core';
import { Video } from '../models/video';
@Injectable({
  providedIn: 'root',
})
export class VideoService {
  videos: Video[] = JSON.parse(localStorage.getItem('videos') || '[]');

  constructor() {}
  addVideo(video: Video) {
    this.videos.push(video);

    this.saveVideos();
  }
  getVideos() {
    return this.videos;
  }
  deleteVideo(id: number) {
    this.videos = this.videos.filter((video) => video.id !== id);

    this.saveVideos();
  }
  getVideoById(id: number): Video | undefined {
    return this.videos.find((video) => video.id === id);
  }
  updateVideo(updatedVideo: Video) {
    const index = this.videos.findIndex(
      (video) => video.id === updatedVideo.id,
    );

    if (index !== -1) {
      this.videos[index] = updatedVideo;

      this.saveVideos();
    }
  }
  saveVideos() {
    localStorage.setItem(
      'videos',

      JSON.stringify(this.videos),
    );
  }
}
