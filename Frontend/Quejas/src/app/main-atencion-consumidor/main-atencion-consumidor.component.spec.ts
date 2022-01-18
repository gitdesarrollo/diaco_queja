import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MainAtencionConsumidorComponent } from './main-atencion-consumidor.component';

describe('MainAtencionConsumidorComponent', () => {
  let component: MainAtencionConsumidorComponent;
  let fixture: ComponentFixture<MainAtencionConsumidorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MainAtencionConsumidorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MainAtencionConsumidorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
