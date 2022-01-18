import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BitacoraACComponent } from './bitacora-ac.component';

describe('BitacoraACComponent', () => {
  let component: BitacoraACComponent;
  let fixture: ComponentFixture<BitacoraACComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BitacoraACComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BitacoraACComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
