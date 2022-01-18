import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParamGeneralesComponent } from './param-generales.component';

describe('ParamGeneralesComponent', () => {
  let component: ParamGeneralesComponent;
  let fixture: ComponentFixture<ParamGeneralesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ParamGeneralesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParamGeneralesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
