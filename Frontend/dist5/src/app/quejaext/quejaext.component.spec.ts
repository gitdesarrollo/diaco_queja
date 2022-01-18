import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { QuejaextComponent } from './quejaext.component';

describe('QuejaextComponent', () => {
  let component: QuejaextComponent;
  let fixture: ComponentFixture<QuejaextComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ QuejaextComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QuejaextComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
