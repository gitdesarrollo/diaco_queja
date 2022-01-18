import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MainJuridicoComponent } from './main-juridico.component';

describe('MainJuridicoComponent', () => {
  let component: MainJuridicoComponent;
  let fixture: ComponentFixture<MainJuridicoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MainJuridicoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MainJuridicoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
