import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { QuejaprComponent } from './quejapr.component';

describe('QuejaprComponent', () => {
  let component: QuejaprComponent;
  let fixture: ComponentFixture<QuejaprComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ QuejaprComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QuejaprComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
