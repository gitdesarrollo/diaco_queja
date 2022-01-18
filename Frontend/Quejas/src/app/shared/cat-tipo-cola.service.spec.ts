import { TestBed } from '@angular/core/testing';

import { CatTipoColaService } from './cat-tipo-cola.service';

describe('CatTipoColaService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CatTipoColaService = TestBed.get(CatTipoColaService);
    expect(service).toBeTruthy();
  });
});
