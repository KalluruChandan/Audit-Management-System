import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AuditResponsesComponent } from './audit-responses.component';

describe('AuditResponsesComponent', () => {
  let component: AuditResponsesComponent;
  let fixture: ComponentFixture<AuditResponsesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AuditResponsesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AuditResponsesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
