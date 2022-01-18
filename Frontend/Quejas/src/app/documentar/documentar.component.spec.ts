import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DocumentarComponent } from './documentar.component';

describe('DocumentarComponent', () => {
  let component: DocumentarComponent;
  let fixture: ComponentFixture<DocumentarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DocumentarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DocumentarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
