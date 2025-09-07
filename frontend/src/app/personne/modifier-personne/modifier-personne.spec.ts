import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModifierPersonne } from './modifier-personne';

describe('ModifierPersonne', () => {
  let component: ModifierPersonne;
  let fixture: ComponentFixture<ModifierPersonne>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ModifierPersonne]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModifierPersonne);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
