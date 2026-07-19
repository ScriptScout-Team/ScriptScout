import { Component } from '@angular/core';

@Component({
  selector: 'app-processing-queue',
  templateUrl: './processing-queue.component.html',
  styleUrls: ['./processing-queue.component.css']
})
export class ProcessingQueueComponent {

  processingVideos = [

    {
      id:1,
      title:'AI Revolution in Healthcare',
      status:'Processing',
      progress:75,
      started:'10:25 AM'
    },

    {
      id:2,
      title:'Future of AI',
      status:'Completed',
      progress:100,
      started:'09:45 AM'
    },

    {
      id:3,
      title:'Machine Learning Basics',
      status:'Failed',
      progress:30,
      started:'08:10 AM'
    }

  ];

  retry(id:number){

    alert("Retry started for Video ID : "+id);

  }

  refreshQueue(){

    alert("Queue Refreshed");

  }
  delete(id:number){

this.processingVideos=

this.processingVideos.filter(v=>v.id!==id);
console.log("deleted.")
}

}