import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ResumenQuejasComponent } from './resumen-quejas.component';

describe('ResumenQuejasComponent', () => {
  let component: ResumenQuejasComponent;
  let fixture: ComponentFixture<ResumenQuejasComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ResumenQuejasComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ResumenQuejasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
