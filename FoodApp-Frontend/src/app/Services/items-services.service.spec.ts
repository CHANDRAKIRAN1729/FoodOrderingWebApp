import { TestBed } from '@angular/core/testing';

import { ItemsServicesService } from './items-services.service';

describe('ItemsServicesService', () => {
  let service: ItemsServicesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ItemsServicesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
