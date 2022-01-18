import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TablaMainVerificacionComponent } from './tabla-main-verificacion.component';

describe('TablaMainVerificacionComponent', () => {
  let component: TablaMainVerificacionComponent;
  let fixture: ComponentFixture<TablaMainVerificacionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TablaMainVerificacionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TablaMainVerificacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
