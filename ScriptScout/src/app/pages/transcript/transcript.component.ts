import { Component,OnInit  } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
interface TranscriptItem {
  time: string;
  speaker: string;
  text: string;
}

@Component({
  selector: 'app-transcript',
  templateUrl: './transcript.component.html',
  styleUrls: ['./transcript.component.css']
})
export class TranscriptComponent implements OnInit {
  videoId!: number;
  constructor(private route: ActivatedRoute) {}
  ngOnInit(): void {
    this.videoId = Number(this.route.snapshot.paramMap.get('id'));
  }
  videoTitle = 'AI Revolution in Healthcare';

  activeTab = 'transcript';

  searchText = '';

  transcripts: TranscriptItem[] = [
    {
      time: '00:00',
      speaker: 'Host',
      text: 'Welcome everyone to today’s discussion on how Artificial Intelligence is transforming healthcare across the world.'
    },
    {
      time: '00:12',
      speaker: 'Doctor',
      text: 'AI enables faster diagnosis, improves treatment planning, and supports doctors by analyzing medical images more accurately.'
    },
    {
      time: '00:35',
      speaker: 'Host',
      text: 'Can AI completely replace healthcare professionals in the future?'
    },
    {
      time: '00:46',
      speaker: 'Doctor',
      text: 'No. AI is a decision-support tool. Human expertise, empathy, and clinical judgment remain essential.'
    },
    {
      time: '01:08',
      speaker: 'Host',
      text: 'What are some real-world applications already being used today?'
    }
  ];

  get filteredTranscripts(): TranscriptItem[] {

    if (!this.searchText.trim()) {
      return this.transcripts;
    }

    return this.transcripts.filter(item =>
      item.text.toLowerCase().includes(this.searchText.toLowerCase()) ||
      item.speaker.toLowerCase().includes(this.searchText.toLowerCase()) ||
      item.time.includes(this.searchText)
    );
  }

  changeTab(tab: string) {
    this.activeTab = tab;
  }
  downloadTranscript() {

  const content = this.transcripts
    .map(item => `${item.time} ${item.speaker}: ${item.text}`)
    .join('\n\n');

  const blob = new Blob([content], { type: 'text/plain' });

  const url = window.URL.createObjectURL(blob);

  const a = document.createElement('a');

  a.href = url;

  a.download = 'transcript.txt';

  a.click();

  window.URL.revokeObjectURL(url);

}

}