import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { VideoPlayerComponent } from './video-player.component';
import { VideoService } from '../../services/video.service';

describe('VideoPlayerComponent', () => {
  let component: VideoPlayerComponent;
  let fixture: ComponentFixture<VideoPlayerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
  declarations: [VideoPlayerComponent],
  imports: [RouterTestingModule],
  providers: [
    {
      provide: ActivatedRoute,
      useValue: {
        snapshot: {
          paramMap: {
            get: () => '1'
          }
        }
      }
    },
    {
      provide: VideoService,
      useValue: {
        getVideoById: () => ({
          id: 1,
          title: 'Test Video',
          category: 'AI',
          uploadDate: '25-07-2026',
          videoUrl: 'assets/sample.mp4'
        })
      }
    }
  ]
}).compileComponents();

    fixture = TestBed.createComponent(VideoPlayerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});