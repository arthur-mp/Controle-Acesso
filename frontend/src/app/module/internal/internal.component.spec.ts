import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InternalComponent } from './internal.component';

describe('InternalComponent', () => {
  let component: InternalComponent;
  let fixture: ComponentFixture<InternalComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [InternalComponent]
    });
    fixture = TestBed.createComponent(InternalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
