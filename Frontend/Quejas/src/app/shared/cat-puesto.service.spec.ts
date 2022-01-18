import { TestBed } from '@angular/core/testing';

import { CatPuestoService } from './cat-puesto.service';

describe('CatPuestoService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CatPuestoService = TestBed.get(CatPuestoService);
    expect(service).toBeTruthy();
  });
});
