import { TestBed } from '@angular/core/testing';

import { ActividadEconomicaService } from './actividad-economica.service';

describe('ActividadEconomicaService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ActividadEconomicaService = TestBed.get(ActividadEconomicaService);
    expect(service).toBeTruthy();
  });
});
