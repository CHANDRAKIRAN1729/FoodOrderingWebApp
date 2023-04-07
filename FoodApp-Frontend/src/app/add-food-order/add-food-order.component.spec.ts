import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddFoodOrderComponent } from './add-food-order.component';

describe('AddFoodOrderComponent', () => {
  let component: AddFoodOrderComponent;
  let fixture: ComponentFixture<AddFoodOrderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddFoodOrderComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddFoodOrderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
