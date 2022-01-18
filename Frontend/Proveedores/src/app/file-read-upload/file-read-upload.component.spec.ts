import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { FileReadUploadComponent } from './file-read-upload.component';

describe('FileReadUploadComponent', () => {
  let component: FileReadUploadComponent;
  let fixture: ComponentFixture<FileReadUploadComponent>;

  beforeEach(waitForAsync(() => {
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
