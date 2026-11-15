import { Component, OnInit, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { CreatePaymentRequest } from '../dto/request/create-payment-request';
import { PaymentService } from '../payment.service';
import { ContactService } from '../../contact/contact.service';
import { ContactResponse } from '../../contact/dto/response/contact-response';
import { Page } from '../../pageable/page';

@Component({
  selector: 'app-create-payment',
  imports: [FormsModule, RouterLink],
  templateUrl: './create-payment.html',
  styleUrl: './create-payment.scss',
})
export class CreatePayment implements OnInit {
  constructor(
    private paymentService: PaymentService,
    private contactService: ContactService,
  ) {}
  paymentRequest = signal<CreatePaymentRequest>({
    description: null,
    amount: null,
    feeAmount: null,
    paymentType: null,
    paidDate: null,
    paidById: null,
    paidForId: null,
  });
  users = signal<Page<ContactResponse[]>>({
    content: [],
    empty: true,
    first: true,
    last: true,
    number: 0,
  });
  userSearch = '';
  userPage = 0;
  userPicker: 'paidById' | 'paidForId' | null = null;
  ngOnInit(): void {
    this.loadUsers();
  }
  create(): void {
    this.paymentService.create(this.paymentRequest()).subscribe({
      next: () =>
        this.paymentRequest.set({
          description: null,
          amount: null,
          feeAmount: null,
          paymentType: null,
          paidDate: null,
          paidById: null,
          paidForId: null,
        }),
      error: () => console.error('ERROR'),
    });
  }
  openUserPicker(field: 'paidById' | 'paidForId'): void {
    this.userPicker = field;
    this.loadUsers();
  }
  loadUsers(): void {
    this.contactService
      .getAll(this.userPage, 10, { name: this.userSearch || null, email: null, phoneNumber: null })
      .subscribe({
        next: (response) => this.users.set(response),
        error: () => console.error('ERROR'),
      });
  }
  filterUsers(): void {
    this.userPage = 0;
    this.loadUsers();
  }
  changeUserPage(direction: number): void {
    const page = this.users();
    if ((direction < 0 && page.first) || (direction > 0 && page.last)) return;
    this.userPage = page.number + direction;
    this.loadUsers();
  }
  selectUser(user: ContactResponse): void {
    if (this.userPicker)
      this.paymentRequest.update((request) => ({ ...request, [this.userPicker!]: user.id }));
    this.userPicker = null;
  }
  userName(id: number | null): string {
    return (
      this.users().content.find((user) => user.id === id)?.name ||
      (id ? `User #${id}` : 'Select a user')
    );
  }
}
