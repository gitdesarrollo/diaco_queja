import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MainVerificacionVigilanciaComponent } from './main-verificacion-vigilancia.component';

describe('MainVerificacionVigilanciaComponent', () => {
  let component: MainVerificacionVigilanciaComponent;
  let fixture: ComponentFixture<MainVerificacionVigilanciaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MainVerificacionVigilanciaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MainVerificacionVigilanciaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
