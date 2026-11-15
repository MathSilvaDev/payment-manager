import { Component, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ContactService } from '../contact.service';
import { CreateContactRequest } from '../dto/request/create-contact-request';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-create-contact',
  imports: [FormsModule, RouterLink],
  templateUrl: './create-contact.html',
  styleUrl: './create-contact.scss',
})
export class CreateContact {
  
  constructor(private contactService: ContactService){}

  contactRequest = signal<CreateContactRequest>({
    name: null,
    email: null,
    phoneNumber: null
  });


  create(): void{
    this.contactService.create(this.contactRequest()).subscribe({
      next: () => {
        this.contactRequest.set({
          name: null,
          email: null,
          phoneNumber: null
        });
      },
      error: () => console.error("ERROR")
    });
  }
}
