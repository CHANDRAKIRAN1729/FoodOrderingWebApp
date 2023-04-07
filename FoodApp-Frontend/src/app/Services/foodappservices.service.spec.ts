import { TestBed } from '@angular/core/testing';

import { FoodappservicesService } from './foodappservices.service';

describe('FoodappservicesService', () => {
  let service: FoodappservicesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FoodappservicesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
