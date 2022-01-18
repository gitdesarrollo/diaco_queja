import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VisitaCampoComponent } from './visita-campo.component';

describe('VisitaCampoComponent', () => {
  let component: VisitaCampoComponent;
  let fixture: ComponentFixture<VisitaCampoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VisitaCampoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VisitaCampoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
