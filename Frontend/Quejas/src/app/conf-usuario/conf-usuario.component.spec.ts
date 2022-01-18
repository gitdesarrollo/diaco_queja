import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfUsuarioComponent } from './conf-usuario.component';

describe('ConfUsuarioComponent', () => {
  let component: ConfUsuarioComponent;
  let fixture: ComponentFixture<ConfUsuarioComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConfUsuarioComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConfUsuarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
