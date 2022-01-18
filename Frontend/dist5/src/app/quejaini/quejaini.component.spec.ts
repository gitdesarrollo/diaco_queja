import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { QuejainiComponent } from './quejaini.component';

describe('QuejainiComponent', () => {
  let component: QuejainiComponent;
  let fixture: ComponentFixture<QuejainiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ QuejainiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QuejainiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
