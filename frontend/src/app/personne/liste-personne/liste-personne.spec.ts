import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListePersonne } from './liste-personne';

describe('ListePersonne', () => {
  let component: ListePersonne;
  let fixture: ComponentFixture<ListePersonne>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ListePersonne]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListePersonne);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
