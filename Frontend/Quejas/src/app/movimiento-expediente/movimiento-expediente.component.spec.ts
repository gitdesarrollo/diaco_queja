import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MovimientoExpedienteComponent } from './movimiento-expediente.component';

describe('MovimientoExpedienteComponent', () => {
  let component: MovimientoExpedienteComponent;
  let fixture: ComponentFixture<MovimientoExpedienteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MovimientoExpedienteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MovimientoExpedienteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
