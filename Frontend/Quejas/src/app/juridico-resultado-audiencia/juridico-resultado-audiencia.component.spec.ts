import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { JuridicoResultadoAudienciaComponent } from './juridico-resultado-audiencia.component';

describe('JuridicoResultadoAudienciaComponent', () => {
  let component: JuridicoResultadoAudienciaComponent;
  let fixture: ComponentFixture<JuridicoResultadoAudienciaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ JuridicoResultadoAudienciaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(JuridicoResultadoAudienciaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
