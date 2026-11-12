package com.math.payment.application.contact.service;

import com.math.payment.application.contact.dto.request.CreateContactRequest;
import com.math.payment.application.contact.dto.response.ContactResponse;
import com.math.payment.application.contact.specification.ContactSpecification;
import com.math.payment.application.contact.specification.filter.ContactFilter;
import com.math.payment.domain.contact.entity.Contact;
import com.math.payment.domain.contact.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    @Override
    public Page<ContactResponse> findAll(int pageNumber, int pageSize, ContactFilter filter) {
        Specification<Contact> spec = ContactSpecification.filterWith(filter);
        Pageable pageable = PageRequest.of(
                pageNumber, pageSize,
                Sort.by(
                        Sort.Order.desc("createdAt"),
                        Sort.Order.asc("name")
                )
        );

        return contactRepository.findAll(spec, pageable)
                .map(this::toResponse);
    }

    @Override
    public ContactResponse create(CreateContactRequest request) {
        Contact contact = new Contact(
                request.name(),
                request.email(),
                request.phoneNumber()
        );

        contactRepository.save(contact);

        return toResponse(contact);
    }

    private ContactResponse toResponse(Contact contact){
        return new ContactResponse(
                contact.getId(),
                contact.getName(),
                contact.getEmail(),
                contact.getPhoneNumber(),
                contact.getCreatedAt()
        );
    }
}
