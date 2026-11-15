export interface CreatePaymentRequest {
  description: string | null;
  amount: number | null;
  feeAmount: number | null;
  paymentType: string | null;
  paidDate: string | null;
  paidById: number | null;
  paidForId: number | null;
}
