import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Customer, SaveCustomer } from '../model/customer';
import { Order, SaveOrder } from '../model/order';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  constructor(private http: HttpClient) { }

  // customer service

  findByCustomerId(id: number){
    return this.http.get<Customer>(`${environment.api}/api/customer/${id}`, {observe: "response"})
  }

  getList(): Observable<HttpResponse<Customer[]>>{
    return this.http.get<Customer[]>(`${environment.api}/api/customer/list`, {observe: "response"})
  }

  save(data : SaveCustomer){
    return this.http.post<any>(`${environment.api}/api/customer/save`, data, {observe: "response"})
  }

  saveImage(file: File){
    const data : FormData = new FormData();
    data.append('file', file);

    return this.http.post<any>(`${environment.api}/api/minio/save`, data, {observe: "response"})
  }

  // order service

  getListOrderByCustomer(customerId: number){
    return this.http.get<Order[]>(`${environment.api}/api/order/list/${customerId}`, {observe: "response"})
  }

  saveOrder(data: SaveOrder){
    return this.http.post<any>(`${environment.api}/api/order/save`, data, {observe: "response"})
  }

  updateOrder(id: number, data: SaveOrder){
    return this.http.put<any>(`${environment.api}/api/order/update/${id}`, data, {observe: "response"})
  }

  findByOrderId(id: number){
    return this.http.get<Order>(`${environment.api}/api/order/${id}`, {observe: "response"})
  }

  deleteOrder(id: number){
    return this.http.delete<Order>(`${environment.api}/api/order/${id}`)
  }
}
