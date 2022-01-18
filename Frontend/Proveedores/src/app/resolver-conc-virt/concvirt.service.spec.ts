import { TestBed } from '@angular/core/testing';

import { ConcvirtService } from './concvirt.service';

describe('ConcvirtService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ConcvirtService = TestBed.get(ConcvirtService);
    expect(service).toBeTruthy();
  });
});
