import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MainConcVirtualComponent } from './main-conc-virtual.component';

describe('MainConcVirtualComponent', () => {
  let component: MainConcVirtualComponent;
  let fixture: ComponentFixture<MainConcVirtualComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MainConcVirtualComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MainConcVirtualComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
