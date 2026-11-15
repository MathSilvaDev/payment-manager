export interface PaymentFilter {
  description: string | null;
  paymentType: string | null;
  paidDate: string | null;
  refunded: boolean | null;
}
