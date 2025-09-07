import {Component, inject, OnInit} from '@angular/core';
import {MessageService} from 'primeng/api';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {PersonneService} from '../../private/service/personne-service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-modifier-personne',
  standalone: false,
  templateUrl: './modifier-personne.html',
  styleUrl: './modifier-personne.css'
})
export class ModifierPersonne implements OnInit{

  messageService = inject(MessageService);

  exampleForm!: FormGroup;
  formSubmitted = false;
  id!: number ;

  constructor(private fb: FormBuilder, private personneService: PersonneService,private activatedRoute : ActivatedRoute,
              private router: Router
  ) {

  }

  ngOnInit(): void {
    this.exampleForm = this.fb.group({
      nom: ['', Validators.required],
      prenom: ['', Validators.required],
      age: ['', [Validators.required, Validators.min(1), Validators.max(150)]],
    });

    this.id = this.activatedRoute.snapshot.params['id'];
    console.log(this.id);

    this.personneService.recupererUnePersonne(this.id).subscribe({
      next: (response: any) => {
        this.exampleForm.patchValue({
          nom: response.nom,
          prenom: response.prenom,
          age: response.age
        });
      },
      error: (erro: any) => {
        console.log(erro);
      }
    });
  }


  onSubmit() {
    this.formSubmitted = true;

    if (this.exampleForm.valid) {
      this.personneService.modifierUnePersonne(this.id,this.exampleForm.value).subscribe({
        next: (response: any) => {
          console.log('Succès:', response);

          this.messageService.add({
            severity: 'success',
            summary: 'Succès',
            detail: 'Personne ajoutée avec succès!',
            life: 3000
          });
          this.formSubmitted = false;
          setTimeout(() => {
            this.router.navigate(['/personne']);
          }, 2000);
        },
        error: (error: any) => {
          console.log('Erreur:', error);

          this.messageService.add({
            severity: 'error',
            summary: 'Erreur',
            detail: 'Échec de l\'ajout de la personne. Veuillez réessayer.',
            life: 5000
          });
        }
      });
    } else {
      this.messageService.add({
        severity: 'warn',
        summary: 'Attention',
        detail: 'Veuillez corriger les erreurs dans le formulaire',
        life: 3000
      });
    }
  }

  isInvalid(controlName: string) {
    const control = this.exampleForm.get(controlName);
    return control?.invalid && (control.touched || this.formSubmitted);
  }

}
