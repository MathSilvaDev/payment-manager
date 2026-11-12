package com.math.payment.application.contact.controller;

import com.math.payment.application.contact.dto.request.CreateContactRequest;
import com.math.payment.application.contact.dto.request.EditContactRequest;
import com.math.payment.application.contact.dto.response.ContactResponse;
import com.math.payment.application.contact.service.ContactServiceImpl;
import com.math.payment.application.contact.specification.filter.ContactFilter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactServiceImpl contactService;

    @GetMapping(version = "1.0")
    public ResponseEntity<Page<ContactResponse>> findAll(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "20") int pageSize,
            @ModelAttribute ContactFilter contactFilter){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(contactService.findAll(pageNumber, pageSize, contactFilter));
    }

    @PostMapping(version = "1.0")
    public ResponseEntity<ContactResponse> create(@Valid @RequestBody CreateContactRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(contactService.create(request));
    }

    @PatchMapping(path = "/{id}", version = "1.0")
    public ResponseEntity<Void> editById(@PathVariable Long id,
                                     @Valid @RequestBody EditContactRequest request){
        contactService.editById(id, request);

        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping(path = "/{id}", version = "1.0")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        contactService.deleteById(id);

        return ResponseEntity
                .noContent()
                .build();
    }

}
