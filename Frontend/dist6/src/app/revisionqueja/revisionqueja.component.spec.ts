import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RevisionquejaComponent } from './revisionqueja.component';

describe('RevisionquejaComponent', () => {
  let component: RevisionquejaComponent;
  let fixture: ComponentFixture<RevisionquejaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RevisionquejaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RevisionquejaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
