import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { ContactResponse } from './dto/response/contact-response';
import { Page } from '../pageable/page';
import { Observable } from 'rxjs';
import { ContactFilter } from './dto/filter/contact-filter';
import { CreateContactRequest } from './dto/request/create-contact-request';
import { EditContactRequest } from './dto/request/edit-contact-request';

@Injectable({
  providedIn: 'root',
})
export class ContactService {

  private readonly API_URL = "/api/contacts"

  http: HttpClient = inject(HttpClient);

  getAll(pageNumber: number, pageSize: number, filter: ContactFilter): Observable<Page<ContactResponse[]>>{

    const params: any = {...filter}

    Object.keys(params).forEach(key => {
      if(params[key] == null) delete params[key]
    })

    return this.http.get<Page<ContactResponse[]>>(this.API_URL, {
      params: {
        pageNumber, pageSize, ...params,
      },
    });
  }

  create(createRequest: CreateContactRequest): Observable<ContactResponse>{
    return this.http.post<ContactResponse>(this.API_URL, createRequest)
  }

  editById(id: number, editRequest: EditContactRequest): Observable<void>{
    return this.http.patch<void>(`${this.API_URL}/${id}`, editRequest)
  }

  deleteById(id: number): Observable<void>{
    return this.http.delete<void>(`${this.API_URL}/${id}`)
  }
}
