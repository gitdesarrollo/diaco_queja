import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CatActividadEconomicaComponent } from './cat-actividad-economica.component';

describe('CatActividadEconomicaComponent', () => {
  let component: CatActividadEconomicaComponent;
  let fixture: ComponentFixture<CatActividadEconomicaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CatActividadEconomicaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CatActividadEconomicaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
