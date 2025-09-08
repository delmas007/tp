import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import {FormBuilder, FormsModule, ReactiveFormsModule} from '@angular/forms';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { of, throwError } from 'rxjs';
import { MessageService } from 'primeng/api';
import { ToastModule } from 'primeng/toast';
import { PersonneVO } from '../models/personne.model';
import { Departement } from '../models/departement.model';
import {ModifierPersonne} from './modifier-personne';
import {PersonneService} from '../../../private/service/personne-service';
import {DepartementService} from '../../../private/service/departement-service';
import {BrowserModule} from '@angular/platform-browser';
import {AppRoutingModule} from '../../../app-routing-module';
import {MenubarModule} from 'primeng/menubar';
import {TableModule} from 'primeng/table';
import {HttpClientModule} from '@angular/common/http';
import {MessageModule} from 'primeng/message';
import {ButtonModule} from 'primeng/button';
import {InputTextModule} from 'primeng/inputtext';
import {ConfirmDialogModule} from 'primeng/confirmdialog';
import {RippleModule} from 'primeng/ripple';
import {MenuModule} from 'primeng/menu';
import {Tooltip} from 'primeng/tooltip';
import {Select} from 'primeng/select';
import {RadioButtonModule} from 'primeng/radiobutton';

describe('ModifierPersonne', () => {
  let component: ModifierPersonne;
  let fixture: ComponentFixture<ModifierPersonne>;
  let mockPersonneService: jasmine.SpyObj<PersonneService>;
  let mockDepartementService: jasmine.SpyObj<DepartementService>;
  let mockRouter: jasmine.SpyObj<Router>;
  let mockActivatedRoute: any;
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
    departement: { id: 1, code: 101, designation: 'Informatique' }
  };

  beforeEach(async () => {
    mockPersonneService = jasmine.createSpyObj('PersonneService', ['modifierUnePersonne', 'recupererUnePersonne']);
    mockDepartementService = jasmine.createSpyObj('DepartementService', ['listeDesDepartements']);
    mockRouter = jasmine.createSpyObj('Router', ['navigate']);
    mockMessageService = jasmine.createSpyObj('MessageService', ['add']);

    mockActivatedRoute = {
      snapshot: {
        params: { id: 1 }
      }
    };

    await TestBed.configureTestingModule({
      imports: [
        ReactiveFormsModule,
        ToastModule,
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
      declarations: [ModifierPersonne],
      providers: [
        FormBuilder,
        { provide: PersonneService, useValue: mockPersonneService },
        { provide: DepartementService, useValue: mockDepartementService },
        { provide: Router, useValue: mockRouter },
        { provide: ActivatedRoute, useValue: mockActivatedRoute },
        MessageService
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(ModifierPersonne);
    component = fixture.componentInstance;

    // Remplacer le MessageService injecté par le mock après la création
    (component as any).messageService = mockMessageService;
  });

  it('should create', () => {
    mockDepartementService.listeDesDepartements.and.returnValue(of(DEPARTEMENTS));
    mockPersonneService.recupererUnePersonne.and.returnValue(of(PERSONNE_EXISTANTE));

    expect(component).toBeTruthy();
  });

  it('devrait initialiser le formulaire et charger les données au ngOnInit', () => {
    mockDepartementService.listeDesDepartements.and.returnValue(of(DEPARTEMENTS));
    mockPersonneService.recupererUnePersonne.and.returnValue(of(PERSONNE_EXISTANTE));

    component.ngOnInit();

    expect(component.exampleForm).toBeDefined();
    expect(mockDepartementService.listeDesDepartements).toHaveBeenCalled();
    expect(mockPersonneService.recupererUnePersonne).toHaveBeenCalledWith(1);
    expect(component.id).toBe(1);
    expect(component.departement).toEqual(DEPARTEMENTS);
  });

  it('devrait remplir le formulaire avec les données de la personne', () => {
    mockDepartementService.listeDesDepartements.and.returnValue(of(DEPARTEMENTS));
    mockPersonneService.recupererUnePersonne.and.returnValue(of(PERSONNE_EXISTANTE));

    component.ngOnInit();

    expect(component.exampleForm.get('nom')?.value).toBe('Dupont');
    expect(component.exampleForm.get('prenom')?.value).toBe('Jean');
    expect(component.exampleForm.get('age')?.value).toBe(25);
    expect(component.exampleForm.get('departement')?.value).toBe(1);
  });

  it('devrait gérer les erreurs lors du chargement des départements', () => {
    mockDepartementService.listeDesDepartements.and.returnValue(throwError(() => new Error('Erreur départements')));

    component.ngOnInit();

    expect(component.departement).toBeUndefined();
    expect(mockPersonneService.recupererUnePersonne).not.toHaveBeenCalled();
  });

  it('devrait gérer les erreurs lors de la récupération de la personne', () => {
    mockDepartementService.listeDesDepartements.and.returnValue(of(DEPARTEMENTS));
    mockPersonneService.recupererUnePersonne.and.returnValue(throwError(() => new Error('Erreur personne')));

    component.ngOnInit();

    expect(component.exampleForm.get('nom')?.value).toBe('');
    expect(component.exampleForm.get('prenom')?.value).toBe('');
  });

  it('devrait valider les contraintes du formulaire', () => {
    mockDepartementService.listeDesDepartements.and.returnValue(of(DEPARTEMENTS));
    mockPersonneService.recupererUnePersonne.and.returnValue(of(PERSONNE_EXISTANTE));

    component.ngOnInit();

    component.exampleForm.get('nom')?.setValue('');
    component.exampleForm.get('prenom')?.setValue('');
    component.exampleForm.get('age')?.setValue('');
    component.exampleForm.get('departement')?.setValue(null);

    expect(component.exampleForm.get('nom')?.hasError('required')).toBeTruthy();
    expect(component.exampleForm.get('prenom')?.hasError('required')).toBeTruthy();
    expect(component.exampleForm.get('age')?.hasError('required')).toBeTruthy();
    expect(component.exampleForm.get('departement')?.hasError('required')).toBeTruthy();

    component.exampleForm.get('age')?.setValue(0);
    expect(component.exampleForm.get('age')?.hasError('min')).toBeTruthy();

    component.exampleForm.get('age')?.setValue(151);
    expect(component.exampleForm.get('age')?.hasError('max')).toBeTruthy();
  });


  it('devrait modifier une personne avec succès quand le formulaire est valide', fakeAsync(() => {
    mockDepartementService.listeDesDepartements.and.returnValue(of(DEPARTEMENTS));
    mockPersonneService.recupererUnePersonne.and.returnValue(of(PERSONNE_EXISTANTE));
    mockPersonneService.modifierUnePersonne.and.returnValue(of({ success: true }));

    component.ngOnInit();

    component.exampleForm.patchValue({
      nom: 'Martin',
      prenom: 'Alice',
      age: 30,
      departement: 2
    });

    component.onSubmit();

    const expectedPersonne: PersonneVO = {
      nom: 'Martin',
      prenom: 'Alice',
      age: 30
    };

    expect(mockPersonneService.modifierUnePersonne).toHaveBeenCalledWith(1, expectedPersonne, 2);
    expect(mockMessageService.add).toHaveBeenCalledWith({
      severity: 'success',
      summary: 'Succès',
      detail: 'Personne ajoutée avec succès!',
      life: 3000
    });

    expect(component.formSubmitted).toBeFalsy();

    tick(2000);
    expect(mockRouter.navigate).toHaveBeenCalledWith(['/personne']);
  }));


  it('devrait retourner true pour isInvalid quand le champ est invalide et touché', () => {
    mockDepartementService.listeDesDepartements.and.returnValue(of(DEPARTEMENTS));
    mockPersonneService.recupererUnePersonne.and.returnValue(of(PERSONNE_EXISTANTE));

    component.ngOnInit();

    component.exampleForm.get('nom')?.setValue('');
    component.exampleForm.get('nom')?.markAsTouched();

    expect(component.isInvalid('nom')).toBeTruthy();
  });

  it('devrait retourner true pour isInvalid quand le champ est invalide et le formulaire soumis', () => {
    mockDepartementService.listeDesDepartements.and.returnValue(of(DEPARTEMENTS));
    mockPersonneService.recupererUnePersonne.and.returnValue(of(PERSONNE_EXISTANTE));

    component.ngOnInit();

    component.exampleForm.get('nom')?.setValue('');
    component.formSubmitted = true;

    expect(component.isInvalid('nom')).toBeTruthy();
  });

  it('devrait retourner false pour isInvalid quand le champ est valide', () => {
    mockDepartementService.listeDesDepartements.and.returnValue(of(DEPARTEMENTS));
    mockPersonneService.recupererUnePersonne.and.returnValue(of(PERSONNE_EXISTANTE));

    component.ngOnInit();

    component.exampleForm.get('nom')?.markAsTouched();

    expect(component.isInvalid('nom')).toBeFalsy();
  });


  it('devrait gérer les valeurs de formulaire avec des espaces', () => {
    mockDepartementService.listeDesDepartements.and.returnValue(of(DEPARTEMENTS));
    mockPersonneService.recupererUnePersonne.and.returnValue(of(PERSONNE_EXISTANTE));
    mockPersonneService.modifierUnePersonne.and.returnValue(of({ success: true }));

    component.ngOnInit();

    component.exampleForm.patchValue({
      nom: '  Test  ',
      prenom: '  Nom  ',
      age: 25,
      departement: 1
    });

    component.onSubmit();

    expect(component.personne?.nom).toBe('  Test  ');
    expect(component.personne?.prenom).toBe('  Nom  ');
  });
});
