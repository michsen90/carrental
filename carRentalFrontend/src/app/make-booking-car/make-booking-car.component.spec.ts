import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MakeBookingCarComponent } from './make-booking-car.component';

describe('MakeBookingCarComponent', () => {
  let component: MakeBookingCarComponent;
  let fixture: ComponentFixture<MakeBookingCarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MakeBookingCarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MakeBookingCarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
