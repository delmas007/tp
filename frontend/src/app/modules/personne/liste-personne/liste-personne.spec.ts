import {ComponentFixture, fakeAsync, flush, TestBed, tick} from '@angular/core/testing';
import { ListePersonne } from './liste-personne';
import { Router } from '@angular/router';
import { ConfirmationService, MessageService } from 'primeng/api';
import { of, throwError } from 'rxjs';
import {PersonneVO} from '../models/personne.model';
import {PersonneService} from '../../../private/service/personne-service';
import {ToastModule} from 'primeng/toast';
import {ConfirmDialogModule} from 'primeng/confirmdialog';
import {BrowserModule} from '@angular/platform-browser';
import {AppRoutingModule} from '../../../app-routing-module';
import {MenubarModule} from 'primeng/menubar';
import {TableModule} from 'primeng/table';
import {HttpClientModule} from '@angular/common/http';
import {MessageModule} from 'primeng/message';
import {ButtonModule} from 'primeng/button';
import {InputTextModule} from 'primeng/inputtext';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RippleModule} from 'primeng/ripple';
import {MenuModule} from 'primeng/menu';
import {Tooltip} from 'primeng/tooltip';
import {Select} from 'primeng/select';
import {RadioButtonModule} from 'primeng/radiobutton';

describe('ListePersonne', () => {
  let component: ListePersonne;
  let fixture: ComponentFixture<ListePersonne>;
  let mockPersonneService: jasmine.SpyObj<PersonneService>;
  let mockRouter: jasmine.SpyObj<Router>;
  let mockConfirmationService: jasmine.SpyObj<ConfirmationService>;
  let mockMessageService: jasmine.SpyObj<MessageService>;

  const PERSONNES: PersonneVO[] = [
    { id: 1, nom: 'Dupont', prenom: 'Jean', age: 25, departement: { id: 1, code: 101, designation: 'Informatique' } },
    { id: 2, nom: 'Martin', prenom: 'Alice', age: 15, departement: { id: 2, code: 102, designation: 'Maths' } }
  ];

  beforeEach(async () => {
    mockPersonneService = jasmine.createSpyObj('PersonneService', ['liste', 'supprimer']);
    mockRouter = jasmine.createSpyObj('Router', ['navigateByUrl']);
    mockConfirmationService = jasmine.createSpyObj('ConfirmationService', ['confirm']);
    mockMessageService = jasmine.createSpyObj('MessageService', ['add']);

    await TestBed.configureTestingModule({
      imports: [
        BrowserModule,
        AppRoutingModule,
        MenubarModule,
        TableModule,
        HttpClientModule,
        ToastModule,
        MessageModule,
        ButtonModule,
        InputTextModule,
        ReactiveFormsModule,
        ConfirmDialogModule,
        RippleModule,
        MenuModule,
        MenuModule,
        Tooltip,
        Select,
        FormsModule,
        RadioButtonModule,
      ],
      declarations: [ListePersonne],
      providers: [
        { provide: PersonneService, useValue: mockPersonneService },
        { provide: Router, useValue: mockRouter },
        // { provide: ConfirmationService, useValue: mockConfirmationService }, // <- important
        ConfirmationService,
        { provide: MessageService, useValue: mockMessageService }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(ListePersonne);
    component = fixture.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('devrait charger la liste des personnes avec succès', () => {
    mockPersonneService.liste.and.returnValue(of(PERSONNES));

    component.chargerPersonnes();

    expect(component.personnes.length).toBe(2);
    expect(component.personnesFiltrees.length).toBe(2);
  });

  it('devrait afficher un message derreur si le chargement échoue', fakeAsync(() => {
  mockPersonneService.liste.and.returnValue(throwError(() => new Error('Erreur serveur')));

  component.chargerPersonnes();
  flush();

  expect(mockMessageService.add).toHaveBeenCalledWith({
    severity: 'error',
    summary: 'Erreur',
    detail: 'Impossible de charger la liste des personnes',
    life: 3000
  });
}));

it('devrait filtrer les personnes mineures', () => {
  component.personnes = PERSONNES;
  component.filtreAge = 'mineurs';
  component.appliquerFiltre();

  expect(component.personnesFiltrees.length).toBe(1);
  expect(component.personnesFiltrees[0].nom).toBe('Martin');
});

it('devrait naviguer vers la page de modification', () => {
  component.modifier(1);
  expect(mockRouter.navigateByUrl).toHaveBeenCalledWith('/personne/modifier/1');
});




it('devrait supprimer une personne avec succès et mettre à jour les listes', () => {
  component.personnes = [...PERSONNES];
  component.personnesFiltrees = [...PERSONNES];
  spyOn(component, 'appliquerFiltre');

  mockPersonneService.supprimer.and.returnValue(of({}));

  component.supprimer(1, PERSONNES[0]);

  expect(component.personnes.length).toBe(1);
  expect(component.personnes[0].id).toBe(2); // Seul Alice Martin reste
  expect(component.appliquerFiltre).toHaveBeenCalled();

  expect(mockMessageService.add).toHaveBeenCalledWith({
    severity: 'info',
    summary: 'Suppression en cours...',
    detail: 'Veuillez patienter',
    life: 1000
  });

  expect(mockMessageService.add).toHaveBeenCalledWith({
    severity: 'success',
    summary: 'Suppression réussie',
    detail: 'Dupont Jean a été supprimé(e) avec succès',
    life: 3000
  });
});


it('devrait gérer la suppression avec un ID undefined', () => {
  component.personnes = [...PERSONNES];
  mockPersonneService.supprimer.and.returnValue(of({}));

  component.supprimer(undefined, PERSONNES[0]);

  expect(mockPersonneService.supprimer).toHaveBeenCalledWith(undefined);
  expect(mockMessageService.add).toHaveBeenCalledWith(jasmine.objectContaining({
    severity: 'info',
    summary: 'Suppression en cours...'
  }));
});

it('devrait naviguer vers la page dajout', () => {
component.ajouter({});
expect(mockRouter.navigateByUrl).toHaveBeenCalledWith('/personne/ajout');
});
});
