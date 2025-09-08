import {Departement} from './departement.model';

export interface PersonneVO {
  id?: number;
  nom: string;
  prenom: string;
  age: number;
  departement?:Departement;
}
