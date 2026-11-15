import { Component, OnInit, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { PaymentService } from '../payment.service';
import { EditPaymentRequest } from '../dto/request/edit-payment-request';

@Component({
  selector: 'app-edit-payment',
  imports: [FormsModule, RouterLink],
  templateUrl: './edit-payment.html',
  styleUrl: '../create-payment/create-payment.scss',
})
export class EditPayment implements OnInit {
  constructor(
    private paymentService: PaymentService,
    private route: ActivatedRoute,
    private router: Router,
  ) {}
  id = 0;
  paymentRequest = signal<EditPaymentRequest>({
    description: null,
    amount: null,
    feeAmount: null,
    paidDate: null,
    refunded: false,
  });
  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.paymentService.getById(this.id).subscribe({
      next: (response) =>
        this.paymentRequest.set({
          description: response.description,
          amount: response.amount,
          feeAmount: response.feeAmount,
          paidDate: response.paidDate,
          refunded: response.refunded,
        }),
      error: () => console.error('ERROR'),
    });
  }
  edit(): void {
    this.paymentService.editById(this.id, this.paymentRequest()).subscribe({
      next: () => this.router.navigate(['/payments']),
      error: () => console.error('ERROR'),
    });
  }
}
