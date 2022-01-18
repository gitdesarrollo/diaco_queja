import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DepartamentotestComponent } from './departamentotest.component';

describe('DepartamentotestComponent', () => {
  let component: DepartamentotestComponent;
  let fixture: ComponentFixture<DepartamentotestComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DepartamentotestComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DepartamentotestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
