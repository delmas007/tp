import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ListePersonne} from './personne/liste-personne/liste-personne';
import {AjouterPersonne} from './personne/ajouter-personne/ajouter-personne';
import {ModifierPersonne} from './personne/modifier-personne/modifier-personne';

const routes: Routes = [
  { path: 'personne', component: ListePersonne },
  { path: 'personne/ajout', component: AjouterPersonne },
  { path: 'personne/modifier/:id', component: ModifierPersonne }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

