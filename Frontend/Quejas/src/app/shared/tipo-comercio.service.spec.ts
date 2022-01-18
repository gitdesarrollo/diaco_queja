import { TestBed } from '@angular/core/testing';

import { TipoComercioService } from './tipo-comercio.service';

describe('TipoComercioService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TipoComercioService = TestBed.get(TipoComercioService);
    expect(service).toBeTruthy();
  });
});
