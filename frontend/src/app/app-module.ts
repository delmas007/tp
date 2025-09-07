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
import {HttpClientModule} from '@angular/common/http';
import {ToastModule} from 'primeng/toast';
import {MessageModule} from 'primeng/message';
import {ButtonModule} from 'primeng/button';
import {InputTextModule} from 'primeng/inputtext';
import {ReactiveFormsModule} from '@angular/forms';
import {ConfirmationService, MessageService} from 'primeng/api';
import {ConfirmDialogModule} from 'primeng/confirmdialog';
import {RippleModule} from 'primeng/ripple';
import {DockModule} from 'primeng/dock';
import {MenuModule} from 'primeng/menu';
import {Tooltip} from 'primeng/tooltip';

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
  ],
  providers: [
    ConfirmationService,
    provideBrowserGlobalErrorListeners(),
    provideAnimationsAsync(),
    MessageService,
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
