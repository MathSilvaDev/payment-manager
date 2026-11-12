package com.math.payment.application.contact.specification;

import com.math.payment.application.contact.specification.filter.ContactFilter;
import com.math.payment.domain.contact.entity.Contact;
import org.springframework.data.jpa.domain.Specification;

public class ContactSpecification {

    public static Specification<Contact> filterWith(ContactFilter filter){
        return Specification
                .where(containsNameIgnoreCaseSensitive(filter.name()))
                .and(containsEmailIgnoreCaseSensitive(filter.email()))
                .and(containsPhoneNumberIgnoreCaseSensitive(filter.phoneNumber()));
    }

    private static Specification<Contact> containsNameIgnoreCaseSensitive(String name){
        return (root, query, cb) ->
                name == null
                ? null
                : cb.like(cb.lower(root.get("name")),
                        "%" + name.toLowerCase() + "%");
    }

    private static Specification<Contact> containsEmailIgnoreCaseSensitive(String email){
        return (root, query, cb) ->
                email == null
                ? null
                : cb.like(cb.lower(root.get("email")),
                        "%" + email.toLowerCase() + "%");
    }

    private static Specification<Contact> containsPhoneNumberIgnoreCaseSensitive(String phoneNumber){
        return (root, query, cb) ->
                phoneNumber == null
                ? null
                : cb.like(cb.lower(root.get("phoneNumber")),
                        "%" + phoneNumber.toLowerCase() + "%");
    }
}
