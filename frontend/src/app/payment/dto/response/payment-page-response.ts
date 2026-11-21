import { Page } from '../../../pageable/page';
import { PaymentResponse } from './payment-response';

export interface PaymentPageResponse {
  paymentResponses: Page<PaymentResponse[]>;
  totalAmount: number;
}
