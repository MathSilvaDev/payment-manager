import { Routes } from '@angular/router';
import { Contact } from './contact/contact';
import { Payment } from './payment/payment';
import { EditContact } from './contact/edit-contact/edit-contact';
import { CreateContact } from './contact/create-contact/create-contact';
import { CreatePayment } from './payment/create-payment/create-payment';
import { EditPayment } from './payment/edit-payment/edit-payment';

export const routes: Routes = [
  { path: '', component: Payment },
  { path: 'contacts', component: Contact },
  { path: 'create-contact', component: CreateContact },
  { path: 'edit-contact/:id', component: EditContact },
  { path: 'payments', component: Payment },
  { path: 'create-payment', component: CreatePayment },
  { path: 'edit-payment/:id', component: EditPayment },

  { path: '**', redirectTo: 'payments' },
];
