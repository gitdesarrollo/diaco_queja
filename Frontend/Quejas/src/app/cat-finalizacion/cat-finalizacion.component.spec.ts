import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CatFinalizacionComponent } from './cat-finalizacion.component';

describe('CatFinalizacionComponent', () => {
  let component: CatFinalizacionComponent;
  let fixture: ComponentFixture<CatFinalizacionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CatFinalizacionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CatFinalizacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
