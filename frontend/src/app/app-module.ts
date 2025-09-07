import { NgModule, provideBrowserGlobalErrorListeners } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing-module';
import { App } from './app';
import {provideAnimationsAsync} from '@angular/platform-browser/animations/async';
import { providePrimeNG } from 'primeng/config';
import Aura from '@primeuix/themes/aura';
import {MenubarModule} from 'primeng/menubar';
import { ListePersonne } from './personne/liste-personne/liste-personne';
import {TableModule} from 'primeng/table';
import { AjouterPersonne } from './personne/ajouter-personne/ajouter-personne';
import { ModifierPersonne } from './personne/modifier-personne/modifier-personne';

@NgModule({
  declarations: [
    App,
    ListePersonne,
    AjouterPersonne,
    ModifierPersonne
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MenubarModule,
    TableModule
  ],
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideAnimationsAsync(),
    providePrimeNG({
      theme: {
        preset: Aura,
        options: {
          darkModeSelector: '.my-app-dark',
          cssLayer: false
        }
      }
    }),
  ],
  bootstrap: [App]
})
export class AppModule { }
