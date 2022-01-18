import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MovimientoJuridicoComponent } from './movimiento-juridico.component';

describe('MovimientoJuridicoComponent', () => {
  let component: MovimientoJuridicoComponent;
  let fixture: ComponentFixture<MovimientoJuridicoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MovimientoJuridicoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MovimientoJuridicoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
