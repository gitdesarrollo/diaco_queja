import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CatMunicipioComponent } from './cat-municipio.component';

describe('CatMunicipioComponent', () => {
  let component: CatMunicipioComponent;
  let fixture: ComponentFixture<CatMunicipioComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CatMunicipioComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CatMunicipioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
