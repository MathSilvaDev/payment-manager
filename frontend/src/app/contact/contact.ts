import { Component, OnInit, signal } from '@angular/core';
import { ContactService } from './contact.service';
import { ContactFilter } from './dto/filter/contact-filter';
import { GetParams, Page } from '../pageable/page';
import { ContactResponse } from './dto/response/contact-response';
import { FormsModule } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-contact',
  imports: [FormsModule, DatePipe, RouterLink],
  templateUrl: './contact.html',
  styleUrl: './contact.scss',
})

export class Contact implements OnInit{
  
  constructor(private contactService: ContactService){}

  pageParams: GetParams<ContactFilter> = {
    pageNumber: 0,
    pageSize:  20,
    filter: {
      name: null,
      email: null,
      phoneNumber: null
    }
  }

  pageContactResponse = signal<Page<ContactResponse[]>>({
    content: [],
    empty: true,
    first: true,
    last: true,
    number: 0
  })

  ngOnInit(): void {
    this.getAll();
  }

  getAll(): void{
    this.contactService.getAll(this.pageParams.pageNumber, 
      this.pageParams.pageSize, this.pageParams.filter).subscribe({
        next: (response) => this.pageContactResponse.set(response),
        error: () => console.error("ERROR")
      })
  }

  deleteContact(id: number): void{
    if(!confirm("Are you sure you want to delete this contact?")) return;

    this.contactService.deleteById(id).subscribe({
      next: () => this.getAll(),
      error: () => console.error("ERROR")
    })
  }

  search(): void {
    this.pageParams.pageNumber = 0;
    this.getAll();
  }

  clearFilters(): void {
    this.pageParams.filter = {
      name: null,
      email: null,
      phoneNumber: null
    };

    this.pageParams.pageNumber = 0;
    this.getAll();
  }

  changePage(direction: number): void {
    const page = this.pageContactResponse();

    if (direction < 0 && page.first) return;
    if (direction > 0 && page.last) return;

    this.pageParams.pageNumber = page.number + direction;
    this.getAll();
  }

}

