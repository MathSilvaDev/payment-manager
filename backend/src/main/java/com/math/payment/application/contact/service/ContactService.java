package com.math.payment.application.contact.service;

import com.math.payment.application.contact.dto.request.CreateContactRequest;
import com.math.payment.application.contact.dto.response.ContactResponse;
import com.math.payment.application.contact.specification.filter.ContactFilter;
import org.springframework.data.domain.Page;

public interface ContactService {

    Page<ContactResponse> findAll(int pageNumber, int pageSize, ContactFilter contactFilter);

    ContactResponse create(CreateContactRequest request);
}
