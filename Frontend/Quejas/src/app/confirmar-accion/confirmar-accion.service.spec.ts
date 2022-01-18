import { TestBed } from '@angular/core/testing';

import { ConfirmarAccionService } from './confirmar-accion.service';

describe('ConfirmarAccionService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ConfirmarAccionService = TestBed.get(ConfirmarAccionService);
    expect(service).toBeTruthy();
  });
});
