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
        icon: 'pi pi-star'
      },
      // {
      //   label: 'Projects',
      //   icon: 'pi pi-search',
      //   items: [
      //     {
      //       label: 'Components',
      //       icon: 'pi pi-bolt'
      //     },
      //     {
      //       label: 'Blocks',
      //       icon: 'pi pi-server'
      //     },
      //     {
      //       label: 'UI Kit',
      //       icon: 'pi pi-pencil'
      //     },
      //     {
      //       label: 'Templates',
      //       icon: 'pi pi-palette',
      //       items: [
      //         {
      //           label: 'Apollo',
      //           icon: 'pi pi-palette'
      //         },
      //         {
      //           label: 'Ultima',
      //           icon: 'pi pi-palette'
      //         }
      //       ]
      //     }
      //   ]
      // },
      // {
      //   label: 'Contact',
      //   icon: 'pi pi-envelope'
      // }
    ]
  }
}
