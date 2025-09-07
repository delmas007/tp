import {Component, inject} from '@angular/core';
import {PersonneService} from '../../private/service/personne-service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MessageService} from 'primeng/api';
import {Router} from '@angular/router';

@Component({
  selector: 'app-ajouter-personne',
  standalone: false,
  templateUrl: './ajouter-personne.html',
  styleUrl: './ajouter-personne.css'
})
export class AjouterPersonne {
  messageService = inject(MessageService);
  router = inject(Router);
  exampleForm: FormGroup;
  formSubmitted = false;

  constructor(private fb: FormBuilder, private personneService: PersonneService) {
    this.exampleForm = this.fb.group({
      nom: ['', Validators.required],
      prenom: ['', Validators.required],
      age: ['', [Validators.required, Validators.min(1), Validators.max(150)]],
    });
  }

  onSubmit() {
    this.formSubmitted = true;

    if (this.exampleForm.valid) {
      this.personneService.ajouterUnePersonne(this.exampleForm.value).subscribe({
        next: (response: any) => {
          console.log('Succès:', response);

          this.messageService.add({
            severity: 'success',
            summary: 'Succès',
            detail: 'Personne ajoutée avec succès!',
            life: 3000
          });

          this.exampleForm.reset();
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
