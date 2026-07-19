import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Video } from '../../models/video';
import { VideoService } from '../../services/video.service';

@Component({
  selector: 'app-edit-video',
  templateUrl: './edit-video.component.html',
  styleUrls: ['./edit-video.component.css']
})
export class EditVideoComponent implements OnInit {

  video!: Video;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private videoService: VideoService
  ) {}

  ngOnInit(): void {

    const id = Number(this.route.snapshot.paramMap.get('id'));

    const selectedVideo = this.videoService.getVideoById(id);

    if(selectedVideo){

      this.video = {...selectedVideo};

    }

  }

  updateVideo(){

    this.videoService.updateVideo(this.video);

    alert("Video Updated Successfully");

    this.router.navigate(['/media-library']);

  }

}