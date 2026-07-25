import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { MetadataEditorComponent } from './metadata-editor.component';
import { RouterTestingModule } from '@angular/router/testing';
import { FormsModule } from '@angular/forms';

describe('MetadataEditorComponent', () => {
  let component: MetadataEditorComponent;
  let fixture: ComponentFixture<MetadataEditorComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MetadataEditorComponent],
      imports: [RouterTestingModule,FormsModule],
      schemas: [CUSTOM_ELEMENTS_SCHEMA]
    });
    fixture = TestBed.createComponent(MetadataEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
