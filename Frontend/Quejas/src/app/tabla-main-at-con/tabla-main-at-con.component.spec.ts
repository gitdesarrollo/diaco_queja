import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TablaMainAtConComponent } from './tabla-main-at-con.component';

describe('TablaMainAtConComponent', () => {
  let component: TablaMainAtConComponent;
  let fixture: ComponentFixture<TablaMainAtConComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TablaMainAtConComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TablaMainAtConComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
