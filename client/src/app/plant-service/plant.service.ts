import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Plant} from "../modules/Plant";
import {ApiRoutes} from "../core/ApiRoutes";

@Injectable({
  providedIn: 'root'
})
export class PlantService {

  constructor(private http: HttpClient) { }

  getPlants(): Observable<Plant[]> {
    return this.http.get<Plant[]>(ApiRoutes.BACKEND_PLANTS);
  }

  getPlant(id: string): Observable<any> {
    return this.http.get(`${ApiRoutes.BACKEND_PLANTS}/${id}`);
  }
}
