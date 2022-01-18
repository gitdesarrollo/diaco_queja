import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PrimeraAudienciaComponent } from './primera-audiencia.component';

describe('PrimeraAudienciaComponent', () => {
  let component: PrimeraAudienciaComponent;
  let fixture: ComponentFixture<PrimeraAudienciaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PrimeraAudienciaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PrimeraAudienciaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
