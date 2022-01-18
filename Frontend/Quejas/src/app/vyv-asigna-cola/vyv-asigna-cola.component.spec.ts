import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VyvAsignaColaComponent } from './vyv-asigna-cola.component';

describe('VyvAsignaColaComponent', () => {
  let component: VyvAsignaColaComponent;
  let fixture: ComponentFixture<VyvAsignaColaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VyvAsignaColaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VyvAsignaColaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
