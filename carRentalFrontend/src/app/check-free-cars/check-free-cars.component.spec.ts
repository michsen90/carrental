import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckFreeCarsComponent } from './check-free-cars.component';

describe('CheckFreeCarsComponent', () => {
  let component: CheckFreeCarsComponent;
  let fixture: ComponentFixture<CheckFreeCarsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CheckFreeCarsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CheckFreeCarsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
