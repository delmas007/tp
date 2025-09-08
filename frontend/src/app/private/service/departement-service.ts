import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environmentDev} from '../../../environment/environment.dev';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DepartementService {

  constructor(private http: HttpClient) { }

  private baseUrl: string = environmentDev.baseUrl;
  private endpoint: any = environmentDev.endPoint;

  listeDesDepartements() : Observable<any>{
    return this.http.get(`${this.baseUrl}/${this.endpoint.departement.getAll}`);
  }

}
