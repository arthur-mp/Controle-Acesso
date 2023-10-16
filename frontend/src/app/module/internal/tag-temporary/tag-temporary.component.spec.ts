import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TagTemporaryComponent } from './tag-temporary.component';

describe('TagTemporaryComponent', () => {
  let component: TagTemporaryComponent;
  let fixture: ComponentFixture<TagTemporaryComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TagTemporaryComponent]
    });
    fixture = TestBed.createComponent(TagTemporaryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
