import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InteraccionUsuarioComponent } from './interaccion-usuario.component';

describe('InteraccionUsuarioComponent', () => {
  let component: InteraccionUsuarioComponent;
  let fixture: ComponentFixture<InteraccionUsuarioComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InteraccionUsuarioComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InteraccionUsuarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
