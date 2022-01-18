import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TableConcVirtualComponent } from './table-conc-virtual.component';

describe('TableConcVirtualComponent', () => {
  let component: TableConcVirtualComponent;
  let fixture: ComponentFixture<TableConcVirtualComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TableConcVirtualComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TableConcVirtualComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
