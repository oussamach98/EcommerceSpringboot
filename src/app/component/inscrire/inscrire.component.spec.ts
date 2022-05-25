import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InscrireComponent } from './inscrire.component';

describe('InscrireComponent', () => {
  let component: InscrireComponent;
  let fixture: ComponentFixture<InscrireComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InscrireComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InscrireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
