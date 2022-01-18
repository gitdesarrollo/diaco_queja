import { TestBed } from '@angular/core/testing';

import { VerifDatosService } from './verif-datos.service';

describe('VerifDatosService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: VerifDatosService = TestBed.get(VerifDatosService);
    expect(service).toBeTruthy();
  });
});
