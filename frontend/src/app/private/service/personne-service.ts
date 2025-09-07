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
    return this.http.get(`${this.baseUrl}/${this.endpoint.getAll}`);
  }

  supprimerUnePersonne(id: number | undefined) {
    return this.http.delete(`${this.baseUrl}/${this.endpoint.delete}/${id}`);
  }

  ajouterUnePersonne(data: any) {
    return this.http.post(`${this.baseUrl}/${this.endpoint.create}`, data);
  }

  recupererUnePersonne(id: number) : Observable<any>{
    return this.http.get(`${this.baseUrl}/${this.endpoint.getOne}/${id}`);
  }

  modifierUnePersonne(id: number, data: any) {
    return this.http.put(`${this.baseUrl}/${this.endpoint.update}/${id}`, data);
  }
}
