import { Component, OnInit, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AppSettingsService } from './app-settings.service';
import { EditAppSettingsRequest } from './dto/request/edit-app-settings-request';

@Component({
  selector: 'app-settings',
  imports: [FormsModule, RouterLink],
  templateUrl: './app-settings.html',
  styleUrl: './app-settings.scss',
})
export class AppSettings implements OnInit {
  constructor(
    private readonly appSettingsService: AppSettingsService,
    private readonly router: Router,
  ) {}

  settings = signal<EditAppSettingsRequest>({ initialAmount: 0 });
  isSaving = signal(false);

  ngOnInit(): void {
    this.appSettingsService.find().subscribe({
      next: (response) => this.settings.set({ initialAmount: response.initialAmount }),
      error: () => console.error('ERROR'),
    });
  }

  save(): void {
    this.isSaving.set(true);
    this.appSettingsService.edit(this.settings()).subscribe({
      next: () => {
        this.isSaving.set(false);
        this.router.navigate(['/payments']);
      },
      error: () => {
        this.isSaving.set(false);
        console.error('ERROR');
      },
    });
  }
}
