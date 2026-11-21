import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { inject } from '@angular/core';
import { PaymentResponse } from './dto/response/payment-response';
import { PaymentFilter } from './dto/filter/payment-filter';
import { CreatePaymentRequest } from './dto/request/create-payment-request';
import { EditPaymentRequest } from './dto/request/edit-payment-request';
import { PaymentPageResponse } from './dto/response/payment-page-response';

@Injectable({
  providedIn: 'root',
})
export class PaymentService {
  private readonly API_URL = '/api/payments';
  private http: HttpClient = inject(HttpClient);

  getAll(
    pageNumber: number,
    pageSize: number,
    filter: PaymentFilter,
  ): Observable<PaymentPageResponse> {
    const params: any = { ...filter };
    Object.keys(params).forEach((key) => {
      if (params[key] == null) delete params[key];
    });
    return this.http.get<PaymentPageResponse>(this.API_URL, {
      params: { pageNumber, pageSize, ...params },
    });
  }

  getById(id: number): Observable<PaymentResponse> {
    return this.http.get<PaymentResponse>(`${this.API_URL}/${id}`);
  }

  create(request: CreatePaymentRequest): Observable<PaymentResponse> {
    return this.http.post<PaymentResponse>(this.API_URL, request);
  }

  editById(id: number, request: EditPaymentRequest): Observable<void> {
    return this.http.patch<void>(`${this.API_URL}/${id}`, request);
  }

  deleteById(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}/${id}`);
  }
}
