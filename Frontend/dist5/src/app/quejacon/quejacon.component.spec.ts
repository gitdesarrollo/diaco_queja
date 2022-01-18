import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { QuejaconComponent } from './quejacon.component';

describe('QuejaconComponent', () => {
  let component: QuejaconComponent;
  let fixture: ComponentFixture<QuejaconComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ QuejaconComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QuejaconComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
