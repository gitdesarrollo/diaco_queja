import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AprobarConcPreviaComponent } from './aprobar-conc-previa.component';

describe('AprobarConcPreviaComponent', () => {
  let component: AprobarConcPreviaComponent;
  let fixture: ComponentFixture<AprobarConcPreviaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AprobarConcPreviaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AprobarConcPreviaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
