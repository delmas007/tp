import { Injectable } from '@angular/core';
import {environmentDev} from '../../../environment/environment.dev';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PersonneService {

  constructor(private http: HttpClient) { }

  private baseUrl: string = environmentDev.baseUrl;
  private endpoint: any = environmentDev.endPoint;

  laListeDesPersonnes() : Observable<any>{
    return this.http.get(`${this.baseUrl}/${this.endpoint.personne.getAll}`);
  }

  supprimerUnePersonne(id: number | undefined) {
    return this.http.delete(`${this.baseUrl}/${this.endpoint.personne.delete}/${id}`);
  }

  ajouterUnePersonne(data: any, departementId: number) {
    return this.http.post(`${this.baseUrl}/${this.endpoint.personne.create}/${departementId}`, data);
  }

  recupererUnePersonne(id: number) : Observable<any>{
    return this.http.get(`${this.baseUrl}/${this.endpoint.personne.getOne}/${id}`);
  }

  modifierUnePersonne(idPersonne: number, data: any,idDepartement: number,) {
    console.log( "id departement" + idDepartement + " id personne " + idPersonne + " data " + data);

    return this.http.put(`${this.baseUrl}/${this.endpoint.personne.update}/${idPersonne}/${idDepartement}`, data);
  }
}
