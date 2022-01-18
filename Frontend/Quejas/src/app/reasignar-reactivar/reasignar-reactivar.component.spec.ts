import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReasignarReactivarComponent } from './reasignar-reactivar.component';

describe('ReasignarReactivarComponent', () => {
  let component: ReasignarReactivarComponent;
  let fixture: ComponentFixture<ReasignarReactivarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReasignarReactivarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReasignarReactivarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
