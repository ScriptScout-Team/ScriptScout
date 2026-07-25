import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { ActivatedRoute } from '@angular/router';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { EditVideoComponent } from './edit-video.component';
import { VideoService } from '../../services/video.service';

describe('EditVideoComponent', () => {
  let component: EditVideoComponent;
  let fixture: ComponentFixture<EditVideoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EditVideoComponent],
      imports: [RouterTestingModule,FormsModule],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
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
            }),
            updateVideo: () => {}
          }
        }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(EditVideoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});