export interface PaymentFilter {
  description: string | null;
  paymentType: string | null;
  startPaidDate: string | null;
  endPaidDate: string | null;
  refunded: boolean | null;
}
