import { TestBed } from '@angular/core/testing';

import { ViewMainQuejaService } from './view-main-queja.service';

describe('ViewMainQuejaService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ViewMainQuejaService = TestBed.get(ViewMainQuejaService);
    expect(service).toBeTruthy();
  });
});
