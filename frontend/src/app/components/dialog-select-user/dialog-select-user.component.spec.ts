import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogSelectUserComponent } from './dialog-select-user.component';

describe('DialogSelectUserComponent', () => {
  let component: DialogSelectUserComponent;
  let fixture: ComponentFixture<DialogSelectUserComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DialogSelectUserComponent]
    });
    fixture = TestBed.createComponent(DialogSelectUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
