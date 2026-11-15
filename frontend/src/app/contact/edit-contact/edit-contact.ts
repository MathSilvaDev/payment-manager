import { Component, OnInit, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ContactService } from '../contact.service';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { EditContactRequest } from '../dto/request/edit-contact-request';

@Component({
  selector: 'app-edit-contact',
  imports: [FormsModule, RouterLink],
  templateUrl: './edit-contact.html',
  styleUrl: './edit-contact.scss',
})
export class EditContact implements OnInit{

  constructor(
    private contactService: ContactService,
    private route: ActivatedRoute,
    private router: Router
  ){}

  id: number = 0;

  contactRequest = signal<EditContactRequest>({
    name: null,
    email: null,
    phoneNumber: null
  });
  
  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get("id"));

    this.loadContactById(this.id);
  }

  loadContactById(id: number){
    this.contactService.getById(id).subscribe({
      next: (response) => {
        this.contactRequest.set(response);
      },
      error: () => console.error("ERROR")
    });
  }

  edit(){
    this.contactService.editById(this.id, this.contactRequest()).subscribe({
      next: () => this.router.navigate(["contacts"]),
      error: () => console.error("ERROR")
    })
  }
}
