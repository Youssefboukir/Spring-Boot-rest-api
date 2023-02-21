package com.contact.service;


import com.contact.dto.ContactDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactService {

    List<ContactDto> getAllContact();

    ContactDto getContactById(String id);

    ContactDto saveContact(ContactDto contactDto);

    ContactDto deleteContact(String idContact);

    ContactDto updateContact(ContactDto contactDto,String idContact);

    List<ContactDto> getContactByIds(List<String> ids);
}
