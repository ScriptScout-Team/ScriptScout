import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MediaLibraryComponent } from './media-library.component';

describe('MediaLibraryComponent', () => {
  let component: MediaLibraryComponent;
  let fixture: ComponentFixture<MediaLibraryComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MediaLibraryComponent]
    });
    fixture = TestBed.createComponent(MediaLibraryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
