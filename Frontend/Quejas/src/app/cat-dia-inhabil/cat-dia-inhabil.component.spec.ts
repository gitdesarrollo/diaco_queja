import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CatDiaInhabilComponent } from './cat-dia-inhabil.component';

describe('CatDiaInhabilComponent', () => {
  let component: CatDiaInhabilComponent;
  let fixture: ComponentFixture<CatDiaInhabilComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CatDiaInhabilComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CatDiaInhabilComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
