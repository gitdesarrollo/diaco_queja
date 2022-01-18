import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FileReadUploadComponent } from './file-read-upload.component';

describe('FileReadUploadComponent', () => {
  let component: FileReadUploadComponent;
  let fixture: ComponentFixture<FileReadUploadComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FileReadUploadComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FileReadUploadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
