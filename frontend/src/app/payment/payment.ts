import { Component, OnInit, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { PaymentService } from './payment.service';
import { PaymentFilter } from './dto/filter/payment-filter';
import { PaymentResponse } from './dto/response/payment-response';
import { GetParams, Page } from '../pageable/page';
import { DatePipe, DecimalPipe } from '@angular/common';

@Component({
  selector: 'app-payment',
  imports: [FormsModule, RouterLink, DecimalPipe, DatePipe],
  templateUrl: './payment.html',
  styleUrl: './payment.scss',
})
export class Payment implements OnInit {
  constructor(private paymentService: PaymentService) {}
  pageParams: GetParams<PaymentFilter> = {
    pageNumber: 0,
    pageSize: 20,
    filter: { description: null, paymentType: null, paidDate: null, refunded: null },
  };
  pagePaymentResponse = signal<Page<PaymentResponse[]>>({
    content: [],
    empty: true,
    first: true,
    last: true,
    number: 0,
  });
  ngOnInit(): void {
    this.getAll();
  }
  getAll(): void {
    this.paymentService
      .getAll(this.pageParams.pageNumber, this.pageParams.pageSize, this.pageParams.filter)
      .subscribe({
        next: (response) => this.pagePaymentResponse.set(response),
        error: () => console.error('ERROR'),
      });
  }
  search(): void {
    this.pageParams.pageNumber = 0;
    this.getAll();
  }
  clearFilters(): void {
    this.pageParams.filter = {
      description: null,
      paymentType: null,
      paidDate: null,
      refunded: null,
    };
    this.search();
  }
  changePage(direction: number): void {
    const page = this.pagePaymentResponse();
    if ((direction < 0 && page.first) || (direction > 0 && page.last)) return;
    this.pageParams.pageNumber = page.number + direction;
    this.getAll();
  }
  deletePayment(id: number): void {
    if (!confirm('Are you sure you want to delete this payment?')) return;
    this.paymentService
      .deleteById(id)
      .subscribe({ next: () => this.getAll(), error: () => console.error('ERROR') });
  }
}
