import { Routes } from '@angular/router';
import { Contact } from './contact/contact';
import { Payment } from './payment/payment';
import { EditContact } from './contact/edit-contact/edit-contact';
import { CreateContact } from './contact/create-contact/create-contact';

export const routes: Routes = [
  
  // {path: "", component: Payment},
  {path: "contacts", component: Contact},
  {path: "create-contact", component: CreateContact},
  {path: "edit-contact/:id", component: EditContact},
  
  {path: "**", redirectTo: "contacts"}
];
