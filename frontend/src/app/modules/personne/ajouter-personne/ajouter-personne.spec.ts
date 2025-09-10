

import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import {FormBuilder, FormsModule, ReactiveFormsModule} from '@angular/forms';
import { Router } from '@angular/router';
import { of, throwError } from 'rxjs';
import { MessageService } from 'primeng/api';
import { PersonneVO } from '../models/personne.model';
import { Departement } from '../models/departement.model';
import {AjouterPersonne} from './ajouter-personne';
import {PersonneService} from '../../../private/service/personne-service';
import {DepartementService} from '../../../private/service/departement-service';
import {BrowserModule} from '@angular/platform-browser';
import {AppRoutingModule} from '../../../app-routing-module';
import {MenubarModule} from 'primeng/menubar';
import {TableModule} from 'primeng/table';
import {HttpClientModule} from '@angular/common/http';
import {ToastModule} from 'primeng/toast';
import {MessageModule} from 'primeng/message';
import {ButtonModule} from 'primeng/button';
import {InputTextModule} from 'primeng/inputtext';
import {ConfirmDialogModule} from 'primeng/confirmdialog';
import {RippleModule} from 'primeng/ripple';
import {MenuModule} from 'primeng/menu';
import {Tooltip} from 'primeng/tooltip';
import {Select} from 'primeng/select';
import {RadioButtonModule} from 'primeng/radiobutton';

describe('AjouterPersonne', () => {
  let component: AjouterPersonne;
  let fixture: ComponentFixture<AjouterPersonne>;
  let mockPersonneService: jasmine.SpyObj<PersonneService>;
  let mockDepartementService: jasmine.SpyObj<DepartementService>;
  let mockRouter: jasmine.SpyObj<Router>;
  let mockMessageService: jasmine.SpyObj<MessageService>;

  const DEPARTEMENTS: Departement[] = [
    { id: 1, code: 101, designation: 'Informatique' },
    { id: 2, code: 102, designation: 'Maths' },
    { id: 3, code: 103, designation: 'Physique' }
  ];

  const PERSONNE_EXISTANTE = {
    id: 1,
    nom: 'Dupont',
    prenom: 'Jean',
    age: 25,
    departement: { id: 2, code: 101, designation: 'Informatique' }
  };

  beforeEach(async () => {
    mockPersonneService = jasmine.createSpyObj('PersonneService', ['ajouterModifier','supprimer', 'recuperer']);
    mockDepartementService = jasmine.createSpyObj('DepartementService', ['listeDesDepartements']);
    mockRouter = jasmine.createSpyObj('Router', ['navigate']);
    mockMessageService = jasmine.createSpyObj('MessageService', ['add']);

    await TestBed.configureTestingModule({
      imports: [
        ReactiveFormsModule,
        BrowserModule,
        AppRoutingModule,
        MenubarModule,
        TableModule,
        HttpClientModule,
        ToastModule,
        MessageModule,
        ButtonModule,
        InputTextModule,
        ConfirmDialogModule,
        RippleModule,
        MenuModule,
        MenuModule,
        Tooltip,
        Select,
        FormsModule,
        RadioButtonModule,
      ],
      declarations: [AjouterPersonne],
      providers: [
        FormBuilder,
        { provide: PersonneService, useValue: mockPersonneService },
        { provide: DepartementService, useValue: mockDepartementService },
        { provide: Router, useValue: mockRouter },
        MessageService
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(AjouterPersonne);
    component = fixture.componentInstance;

    (component as any).messageService = mockMessageService;
  });

  it('should create', () => {
    mockDepartementService.listeDesDepartements.and.returnValue(of(DEPARTEMENTS));
    fixture.detectChanges();
    expect(component).toBeTruthy();
  });

  it('devrait initialiser le formulaire avec les validateurs', () => {
    mockDepartementService.listeDesDepartements.and.returnValue(of(DEPARTEMENTS));
    fixture.detectChanges();

    expect(component.exampleForm.get('nom')?.hasError('required')).toBeTruthy();
    expect(component.exampleForm.get('prenom')?.hasError('required')).toBeTruthy();
    expect(component.exampleForm.get('age')?.hasError('required')).toBeTruthy();
    expect(component.exampleForm.get('departement')?.hasError('required')).toBeTruthy();
  });

  it('devrait charger la liste des départements au ngOnInit', () => {
    mockDepartementService.listeDesDepartements.and.returnValue(of(DEPARTEMENTS));

    component.ngOnInit();

    expect(mockDepartementService.listeDesDepartements).toHaveBeenCalled();
    expect(component.departement).toEqual(DEPARTEMENTS);
  });

  it('devrait gérer les erreurs lors du chargement des départements', () => {
    mockDepartementService.listeDesDepartements.and.returnValue(throwError(() => new Error('Erreur serveur')));

    component.ngOnInit();

    expect(component.departement).toBeUndefined();
  });

  it('devrait valider les contraintes d\'âge', () => {
    mockDepartementService.listeDesDepartements.and.returnValue(of(DEPARTEMENTS));
    fixture.detectChanges();

    component.exampleForm.get('age')?.setValue(0);
    expect(component.exampleForm.get('age')?.hasError('min')).toBeTruthy();

    component.exampleForm.get('age')?.setValue(151);
    expect(component.exampleForm.get('age')?.hasError('max')).toBeTruthy();

    component.exampleForm.get('age')?.setValue(25);
    expect(component.exampleForm.get('age')?.valid).toBeTruthy();
  });

  it('devrait afficher un message d\'erreur si le formulaire est invalide', () => {
    mockDepartementService.listeDesDepartements.and.returnValue(of(DEPARTEMENTS));
    fixture.detectChanges();

    component.onSubmit();

    expect(component.formSubmitted).toBeTruthy();
    expect(mockMessageService.add).toHaveBeenCalledWith({
      severity: 'warn',
      summary: 'Attention',
      detail: 'Veuillez corriger les erreurs dans le formulaire',
      life: 3000
    });
    expect(mockPersonneService.ajouterModifier).not.toHaveBeenCalled();
  });

  it('devrait ajouter une personne avec succès quand le formulaire est valide', fakeAsync(() => {
    mockDepartementService.listeDesDepartements.and.returnValue(of(DEPARTEMENTS));
    mockPersonneService.ajouterModifier.and.returnValue(of(PERSONNE_EXISTANTE));
    fixture.detectChanges();

    component.exampleForm.patchValue({
      nom: 'Dupont',
      prenom: 'Jean',
      age: 25,
      departement: 1
    });

    component.onSubmit();

    const expectedPersonne: PersonneVO = {
      nom: 'Dupont',
      prenom: 'Jean',
      age: 25
    };

    expect(mockPersonneService.ajouterModifier).toHaveBeenCalledWith(expectedPersonne, 1);
    expect(mockMessageService.add).toHaveBeenCalledWith({
      severity: 'success',
      summary: 'Succès',
      detail: 'Personne ajoutée avec succès!',
      life: 3000
    });

    expect(component.exampleForm.get('nom')?.value).toBeNull();
    expect(component.formSubmitted).toBeFalsy();

    tick(2000);
    expect(mockRouter.navigate).toHaveBeenCalledWith(['/personne']);
  }));

  it('devrait afficher un message d\'erreur si l\'ajout échoue', () => {
    mockDepartementService.listeDesDepartements.and.returnValue(of(DEPARTEMENTS));
    mockPersonneService.ajouterModifier.and.returnValue(throwError(() => new Error('Erreur serveur')));
    fixture.detectChanges();

    component.exampleForm.patchValue({
      nom: 'Dupont',
      prenom: 'Jean',
      age: 25,
      departement: 1
    });

    component.onSubmit();

    expect(mockMessageService.add).toHaveBeenCalledWith({
      severity: 'error',
      summary: 'Erreur',
      detail: 'Échec de l\'ajout de la personne. Veuillez réessayer.',
      life: 5000
    });
  });

  it('devrait retourner true pour isInvalid quand le champ est invalide et touché', () => {
    mockDepartementService.listeDesDepartements.and.returnValue(of(DEPARTEMENTS));
    fixture.detectChanges();

    component.exampleForm.get('nom')?.markAsTouched();

    expect(component.isInvalid('nom')).toBeTruthy();
  });

  it('devrait retourner true pour isInvalid quand le champ est invalide et le formulaire soumis', () => {
    mockDepartementService.listeDesDepartements.and.returnValue(of(DEPARTEMENTS));
    fixture.detectChanges();

    component.formSubmitted = true;

    expect(component.isInvalid('nom')).toBeTruthy();
  });

  it('devrait retourner false pour isInvalid quand le champ est valide', () => {
    mockDepartementService.listeDesDepartements.and.returnValue(of(DEPARTEMENTS));
    fixture.detectChanges();

    component.exampleForm.get('nom')?.setValue('Dupont');
    component.exampleForm.get('nom')?.markAsTouched();

    expect(component.isInvalid('nom')).toBeFalsy();
  });

  it('devrait retourner false pour isInvalid quand le champ est invalide mais non touché et formulaire non soumis', () => {
    mockDepartementService.listeDesDepartements.and.returnValue(of(DEPARTEMENTS));
    fixture.detectChanges();

    expect(component.isInvalid('nom')).toBeFalsy();
  });

  it('devrait créer l\'objet personne correctement lors de la soumission', () => {
    mockDepartementService.listeDesDepartements.and.returnValue(of(DEPARTEMENTS));
    mockPersonneService.ajouterModifier.and.returnValue(of(PERSONNE_EXISTANTE));
    fixture.detectChanges();

    component.exampleForm.patchValue({
      nom: 'Martin',
      prenom: 'Alice',
      age: 30,
      departement: 2
    });

    component.onSubmit();

    expect(component.personne).toEqual({
      nom: 'Martin',
      prenom: 'Alice',
      age: 30
    });
  });

  it('devrait gérer les champs avec des espaces', () => {
    mockDepartementService.listeDesDepartements.and.returnValue(of(DEPARTEMENTS));
    mockPersonneService.ajouterModifier.and.returnValue(of(PERSONNE_EXISTANTE));
    fixture.detectChanges();

    component.exampleForm.patchValue({
      nom: '  Dupont  ',
      prenom: '  Jean  ',
      age: 25,
      departement: 1
    });

    component.onSubmit();

    expect(component.personne?.nom).toBe('  Dupont  ');
    expect(component.personne?.prenom).toBe('  Jean  ');
  });
});
