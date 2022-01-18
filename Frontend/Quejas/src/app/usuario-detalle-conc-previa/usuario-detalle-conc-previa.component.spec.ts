import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UsuarioDetalleConcPreviaComponent } from './usuario-detalle-conc-previa.component';

describe('UsuarioDetalleConcPreviaComponent', () => {
  let component: UsuarioDetalleConcPreviaComponent;
  let fixture: ComponentFixture<UsuarioDetalleConcPreviaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UsuarioDetalleConcPreviaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UsuarioDetalleConcPreviaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
