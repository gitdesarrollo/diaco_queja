import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ArchivarVerificacionComponent } from './archivar-verificacion.component';

describe('ArchivarVerificacionComponent', () => {
  let component: ArchivarVerificacionComponent;
  let fixture: ComponentFixture<ArchivarVerificacionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ArchivarVerificacionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ArchivarVerificacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
