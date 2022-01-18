import { TestBed } from '@angular/core/testing';
import { FileManagerService } from './file-manager.service';
describe('FileManagerService', function () {
    beforeEach(function () { return TestBed.configureTestingModule({}); });
    it('should be created', function () {
        var service = TestBed.get(FileManagerService);
        expect(service).toBeTruthy();
    });
});
//# sourceMappingURL=file-manager.service.spec.js.map