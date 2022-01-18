import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MuestraRegistroComponent } from './muestra-registro.component';

describe('MuestraRegistroComponent', () => {
  let component: MuestraRegistroComponent;
  let fixture: ComponentFixture<MuestraRegistroComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MuestraRegistroComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MuestraRegistroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
