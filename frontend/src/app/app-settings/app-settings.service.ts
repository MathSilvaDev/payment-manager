import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { EditAppSettingsRequest } from './dto/request/edit-app-settings-request';
import { AppSettingsResponse } from './dto/response/app-settings-response';

@Injectable({
  providedIn: 'root',
})
export class AppSettingsService {
  private readonly API_URL = '/api/app-settings';
  private readonly http = inject(HttpClient);

  find(): Observable<AppSettingsResponse> {
    return this.http.get<AppSettingsResponse>(this.API_URL);
  }

  edit(request: EditAppSettingsRequest): Observable<void> {
    return this.http.patch<void>(this.API_URL, request);
  }
}
