import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AsignarColaComponent } from './asignar-cola.component';

describe('AsignarColaComponent', () => {
  let component: AsignarColaComponent;
  let fixture: ComponentFixture<AsignarColaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AsignarColaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AsignarColaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
