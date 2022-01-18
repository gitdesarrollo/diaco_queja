import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CatSedesComponent } from './cat-sedes.component';

describe('CatSedesComponent', () => {
  let component: CatSedesComponent;
  let fixture: ComponentFixture<CatSedesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CatSedesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CatSedesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
