import { TestBed } from '@angular/core/testing';

import { DownloadfileService } from './downloadfile.service';

describe('DownloadfileService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DownloadfileService = TestBed.get(DownloadfileService);
    expect(service).toBeTruthy();
  });
});
