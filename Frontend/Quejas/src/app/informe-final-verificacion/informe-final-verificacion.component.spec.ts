import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InformeFinalVerificacionComponent } from './informe-final-verificacion.component';

describe('InformeFinalVerificacionComponent', () => {
  let component: InformeFinalVerificacionComponent;
  let fixture: ComponentFixture<InformeFinalVerificacionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InformeFinalVerificacionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InformeFinalVerificacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
