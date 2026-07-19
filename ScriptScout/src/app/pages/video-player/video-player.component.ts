import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { VideoService } from '../../services/video.service';
import { Video } from '../../models/video';

@Component({
  selector: 'app-video-player',
  templateUrl: './video-player.component.html',
  styleUrls: ['./video-player.component.css']
})

export class VideoPlayerComponent implements OnInit{

  video: any;
 selectedTab='chapters';

@ViewChild('videoPlayer')

videoElement!: ElementRef<HTMLVideoElement>;

chapters=[

{

time:'00:00',

seconds:0,

title:'Introduction',

position:'2%'

},

{

time:'01:30',

seconds:90,

title:'AI in Healthcare',

position:'18%'

},

{

time:'06:30',

seconds:390,

title:'AI Diagnosis',

position:'42%'

},

{

time:'10:16',

seconds:616,

title:'Patient Monitoring',

position:'60%'

},

{

time:'15:46',

seconds:946,

title:'Ethics',

position:'80%'

},

{

time:'20:31',

seconds:1231,

title:'Future',

position:'96%'

}

];

transcript=[

{

time:'00:00',

text:'Welcome to today\'s episode...'

},

{

time:'00:30',

text:'Artificial intelligence is...'

},

{

time:'01:02',

text:'AI is changing healthcare...'

}

];

seek(seconds: number) {

  this.videoElement.nativeElement.currentTime = seconds;
this.videoElement.nativeElement.play();
}
  constructor(

    private route:ActivatedRoute,

    private videoService:VideoService

  ){}

  ngOnInit():void{

    const id=Number(

      this.route.snapshot.paramMap.get('id')

    );
    console.log("Video ID:", id);

    const data=this.videoService.getVideoById(id);
    console.log("Video Data:", data);
    if(data){

      this.video=data;

    }

  }
  selectTab(tab:string){

    this.selectedTab = tab;

}

}