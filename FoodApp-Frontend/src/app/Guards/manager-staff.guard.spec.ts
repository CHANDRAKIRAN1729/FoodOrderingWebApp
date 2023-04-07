import { TestBed } from '@angular/core/testing';

import { ManagerStaffGuard } from './manager-staff.guard';

describe('ManagerStaffGuard', () => {
  let guard: ManagerStaffGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(ManagerStaffGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
