import {Component, OnInit} from '@angular/core';
import {PersonneVO} from '../models/personne.model';
import {PersonneService} from '../../../private/service/personne-service';
import {Router} from '@angular/router';
import {ConfirmationService, MenuItem, MessageService} from 'primeng/api';

@Component({
  selector: 'app-liste-personne',
  standalone: false,
  templateUrl: './liste-personne.html',
  styleUrl: './liste-personne.css'
})
export class ListePersonne implements OnInit {
  personnes!: PersonneVO[];
  personnesFiltrees!: PersonneVO[];
  filtreAge: string = 'tous';

  menuItems: MenuItem[] = [
    {
      label: 'Tous',
      icon: 'pi pi-list',
      command: () => {
        this.filtreAge = 'tous';
        this.appliquerFiltre();
      }
    },
    {
      label: 'Mineurs (< 18 ans)',
      icon: 'pi pi-users',
      command: () => {
        this.filtreAge = 'mineurs';
        this.appliquerFiltre();
      }
    },
    {
      label: 'Majeurs (≥ 18 ans)',
      icon: 'pi pi-user',
      command: () => {
        this.filtreAge = 'majeurs';
        this.appliquerFiltre();
      }
    }
  ];

  constructor(
    private personneService: PersonneService,
    private router: Router,
    private confirmationService: ConfirmationService,
    private messageService: MessageService
  ) {}

  ngOnInit() {
    this.chargerPersonnes();
  }

  chargerPersonnes() {
    this.personneService.liste().subscribe({
      next: (response: any) => {
        this.personnes = response;
        this.personnesFiltrees = [...this.personnes];
        this.appliquerFiltre();
        console.log(this.personnes);
      },
      error: (error: any) => {
        console.log(error);
        this.messageService.add({
          severity: 'error',
          summary: 'Erreur',
          detail: 'Impossible de charger la liste des personnes',
          life: 3000
        });
      }
    });
  }

  appliquerFiltre() {
    if (!this.personnes) return;

    switch (this.filtreAge) {
      case 'mineurs':
        this.personnesFiltrees = this.personnes.filter(p => p.age < 18);
        break;
      case 'majeurs':
        this.personnesFiltrees = this.personnes.filter(p => p.age >= 18);
        break;
      default:
        this.personnesFiltrees = [...this.personnes];
        break;
    }
  }

  modifier(id: number) {
    this.router.navigateByUrl(`/personne/modifier/${id}`);
  }

  confirmerSuppression(personne: PersonneVO) {
    const statut = personne.age < 18 ? 'Mineur' : 'Majeur';
    this.confirmationService.confirm({
      message: `Êtes-vous sûr de vouloir supprimer <strong>${personne.nom} ${personne.prenom}</strong> (${statut}) ?<br/>Cette action est irréversible.`,
      header: 'Confirmation de suppression',
      icon: 'pi pi-exclamation-triangle',
      acceptLabel: 'Oui, supprimer',
      rejectLabel: 'Annuler',
      acceptButtonStyleClass: 'p-button-danger p-button-sm',
      rejectButtonStyleClass: 'p-button-secondary p-button-sm',
      accept: () => {
        this.supprimer(personne.id, personne);
      },
      reject: () => {
        this.messageService.add({
          severity: 'info',
          summary: 'Annulé',
          detail: 'Suppression annulée',
          life: 2000
        });
      }
    });
  }

  supprimer(id: number | undefined, personne: PersonneVO) {
    this.messageService.add({
      severity: 'info',
      summary: 'Suppression en cours...',
      detail: 'Veuillez patienter',
      life: 1000
    });

    this.personneService.supprimer(id).subscribe({
      next: (response: any) => {
        console.log('Suppression réussie:', response);

        this.personnes = this.personnes.filter(p => p.id !== id);
        this.appliquerFiltre();

        this.messageService.add({
          severity: 'success',
          summary: 'Suppression réussie',
          detail: `${personne.nom} ${personne.prenom} a été supprimé(e) avec succès`,
          life: 3000
        });
      },
      error: (error: any) => {
        console.log('Erreur suppression:', error);

        this.messageService.add({
          severity: 'error',
          summary: 'Erreur de suppression',
          detail: 'Impossible de supprimer cette personne. Veuillez réessayer.',
          life: 4000
        });
      }
    });
  }

  ajouter(personne: any) {
    this.router.navigateByUrl('/personne/ajout');
  }

  getFilterTooltip(): string {
    switch (this.filtreAge) {
      case 'mineurs':
        return 'Filtre actif: Mineurs seulement';
      case 'majeurs':
        return 'Filtre actif: Majeurs seulement';
      default:
        return 'Cliquez pour filtrer par âge';
    }
  }
}
