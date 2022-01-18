import { TestBed } from '@angular/core/testing';

import { InteraccionService } from './interaccion.service';

describe('InteraccionService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: InteraccionService = TestBed.get(InteraccionService);
    expect(service).toBeTruthy();
  });
});
