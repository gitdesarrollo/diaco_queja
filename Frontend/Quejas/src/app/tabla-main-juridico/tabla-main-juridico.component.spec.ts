import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TablaMainJuridicoComponent } from './tabla-main-juridico.component';

describe('TablaMainJuridicoComponent', () => {
  let component: TablaMainJuridicoComponent;
  let fixture: ComponentFixture<TablaMainJuridicoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TablaMainJuridicoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TablaMainJuridicoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
