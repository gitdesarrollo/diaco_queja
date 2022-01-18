import { TestBed } from '@angular/core/testing';

import { AudienciaService } from './audiencia.service';

describe('AudienciaService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AudienciaService = TestBed.get(AudienciaService);
    expect(service).toBeTruthy();
  });
});
