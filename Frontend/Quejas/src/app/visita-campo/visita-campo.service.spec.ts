import { TestBed } from '@angular/core/testing';

import { VisitaCampoService } from './visita-campo.service';

describe('VisitaCampoService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: VisitaCampoService = TestBed.get(VisitaCampoService);
    expect(service).toBeTruthy();
  });
});
