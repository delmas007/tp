import { Injectable } from '@angular/core';
import {environmentDev} from '../../../environment/environment.dev';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {PersonneVO} from '../../modules/personne/models/personne.model';

@Injectable({
  providedIn: 'root'
})
export class PersonneService {

  constructor(private http: HttpClient) { }

  private baseUrl: string = environmentDev.baseUrl;
  private endpoint: any = environmentDev.endPoint;

  liste() : Observable<PersonneVO[]>{
    return this.http.get<PersonneVO[]>(`${this.baseUrl}/${this.endpoint.personne.getAll}`);
  }

  supprimer(id: number | undefined) {
    return this.http.delete(`${this.baseUrl}/${this.endpoint.personne.delete}/${id}`);
  }

  ajouterModifier(data: any, departementId: number): Observable<PersonneVO>{
    return this.http.post<PersonneVO>(`${this.baseUrl}/${this.endpoint.personne.create}/${departementId}`, data);
  }

  recuperer(id: number) : Observable<PersonneVO>{
    return this.http.get<PersonneVO>(`${this.baseUrl}/${this.endpoint.personne.getOne}/${id}`);
  }
}
