export interface EditPaymentRequest {
  description: string | null;
  amount: number | null;
  feeAmount: number | null;
  paidDate: string | null;
  refunded: boolean;
}
