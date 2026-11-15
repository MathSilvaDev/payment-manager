export interface PaymentResponse {
  id: number;
  description: string;
  amount: number;
  feeAmount: number;
  paymentType: 'PIX' | 'CREDIT_CARD' | 'DEBIT_CARD' | 'MONEY';
  paidDate: string;
  paidBy: string;
  paidFor: string;
  refunded: boolean;
  createdAt: string;
}
