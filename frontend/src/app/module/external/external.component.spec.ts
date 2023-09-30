import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExternalComponent } from './external.component';

describe('ExternalComponent', () => {
  let component: ExternalComponent;
  let fixture: ComponentFixture<ExternalComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ExternalComponent]
    });
    fixture = TestBed.createComponent(ExternalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
