import { Component, signal } from '@angular/core';
import {MenuItem} from 'primeng/api';

@Component({
  selector: 'app-root',
  templateUrl: './app.html',
  standalone: false,
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('frontend');

  items: MenuItem[] | undefined;

  ngOnInit() {
    this.items = [
      {
        label: 'Acceuil',
        icon: 'pi pi-home'
      },
      {
        label: 'Personne',
        icon: 'pi pi-user',
        routerLink: ['personne']
      }
    ]
  }
}
