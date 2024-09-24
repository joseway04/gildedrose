import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Item } from '../models/item';

@Injectable({
  providedIn: 'root'
})
export class InventoryService {
  private http: HttpClient = inject(HttpClient);
  private inventoryUrl: string = 'http://localhost:8080/api/inventory';

  constructor() { }

  upload(data: FormData): Observable<Item[]>{
    return this.http.post<Item[]>(`${this.inventoryUrl}`, data);
  }
  findAll(): Observable<Item[]>{
    return this.http.get<Item[]>(`${this.inventoryUrl}`);
  }
}
