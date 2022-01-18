import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CatAreaComponent } from './cat-area.component';

describe('CatAreaComponent', () => {
  let component: CatAreaComponent;
  let fixture: ComponentFixture<CatAreaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CatAreaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CatAreaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
