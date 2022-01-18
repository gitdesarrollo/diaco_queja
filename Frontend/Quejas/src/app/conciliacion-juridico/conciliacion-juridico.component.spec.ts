import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConciliacionJuridicoComponent } from './conciliacion-juridico.component';

describe('ConciliacionJuridicoComponent', () => {
  let component: ConciliacionJuridicoComponent;
  let fixture: ComponentFixture<ConciliacionJuridicoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConciliacionJuridicoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConciliacionJuridicoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
