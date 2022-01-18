import { TestBed } from '@angular/core/testing';

import { ProveedorComunService } from './proveedor-comun.service';

describe('ProveedorComunService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ProveedorComunService = TestBed.get(ProveedorComunService);
    expect(service).toBeTruthy();
  });
});
