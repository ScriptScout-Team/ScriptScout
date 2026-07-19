import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChapterBreakdownComponent } from './chapter-breakdown.component';

describe('ChapterBreakdownComponent', () => {
  let component: ChapterBreakdownComponent;
  let fixture: ComponentFixture<ChapterBreakdownComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ChapterBreakdownComponent]
    });
    fixture = TestBed.createComponent(ChapterBreakdownComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
