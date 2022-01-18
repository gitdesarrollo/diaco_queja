import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MovimientoServPubComponent } from './movimiento-serv-pub.component';

describe('MovimientoServPubComponent', () => {
  let component: MovimientoServPubComponent;
  let fixture: ComponentFixture<MovimientoServPubComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MovimientoServPubComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MovimientoServPubComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
