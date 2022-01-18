import { TestBed } from '@angular/core/testing';

import { DepartamentoInternoService } from './departamento-interno.service';

describe('DepartamentoInternoService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DepartamentoInternoService = TestBed.get(DepartamentoInternoService);
    expect(service).toBeTruthy();
  });
});
