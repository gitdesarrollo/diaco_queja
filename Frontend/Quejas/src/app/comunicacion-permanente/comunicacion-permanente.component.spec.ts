import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ComunicacionPermanenteComponent } from './comunicacion-permanente.component';

describe('ComunicacionPermanenteComponent', () => {
  let component: ComunicacionPermanenteComponent;
  let fixture: ComponentFixture<ComunicacionPermanenteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ComunicacionPermanenteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ComunicacionPermanenteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
