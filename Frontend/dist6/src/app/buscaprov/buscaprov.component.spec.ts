import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BuscaprovComponent } from './buscaprov.component';

describe('BuscaprovComponent', () => {
  let component: BuscaprovComponent;
  let fixture: ComponentFixture<BuscaprovComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BuscaprovComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BuscaprovComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
