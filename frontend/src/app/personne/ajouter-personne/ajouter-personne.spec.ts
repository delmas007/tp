import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AjouterPersonne } from './ajouter-personne';

describe('AjouterPersonne', () => {
  let component: AjouterPersonne;
  let fixture: ComponentFixture<AjouterPersonne>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AjouterPersonne]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AjouterPersonne);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
