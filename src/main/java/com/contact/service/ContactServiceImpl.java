package com.contact.service;


import com.contact.dao.ContactRepository;
import com.contact.dto.ContactDto;
import com.contact.dto.ContactMapper;
import com.contact.entity.ContactEntity;
import com.contact.exception.AlreadyExistsException;
import com.contact.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService{

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ContactMapper contactMapper;

    @Override
    public List<ContactDto> getAllContact() {
        //find all the contact
        List<ContactEntity> listContact = contactRepository.findAll();
        // convert the list of contact to a list of ContactDto objects and return it
        return contactMapper.toContactDtos(listContact);
    }

    @Override
    public ContactDto getContactById(String id) {
        // find a contact by its id and throw an exception if the contact is not found
        ContactEntity contact = contactRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Contact not found with id {} "+id));
        // convert the contact to a ContactDto object and return it
        return contactMapper.toContactDto(contact);
    }

    @Override
    public ContactDto saveContact(ContactDto contactDto) {
        // check if a contact with the same id already exists
        if(contactRepository.existsById(contactDto.getIdContact())){
            throw new AlreadyExistsException("Contact with this id aleardy exists");
        }
        ContactEntity contact = contactRepository.save(contactMapper.toContact(contactDto));
        // convert the contact to a ContactDto object and return it
        return contactMapper.toContactDto(contact);
    }

    @Override
    public ContactDto deleteContact(String idContact) {
        // find a contact by its id and throw an exception if the contact is not found
        ContactEntity contact = contactRepository.findById(idContact).orElseThrow(()-> new ResourceNotFoundException("Contact not found with id {} for update "+idContact));
        //delete the contact
        contactRepository.delete(contact);
        return contactMapper.toContactDto(contact);
    }

    @Override
    public ContactDto updateContact(ContactDto contactDto,String idContact) {
        // find a contact by its id and throw an exception if the contact is not found
        ContactEntity contact = contactRepository.findById(idContact).orElseThrow(()-> new ResourceNotFoundException("Contact not found with id {} for update "+idContact));
        //set the new contact information
        contact.setEmail(contactDto.getEmail())
                .setName(contactDto.getEmail())
                .setPhone(contactDto.getPhone());
        // update the contact and return it as a ContactDto object
        return contactMapper.toContactDto(contactRepository.save(contact));
    }

    @Override
    public List<ContactDto> getContactByIds(List<String> ids) {
        // find a list of contacts by their ids
        List<ContactEntity> listContact = contactRepository.getContactByIds(ids);
        // check if the list of contacts is empty
        if(listContact.size() == 0){
            // throw an exception if the list of contacts is empty
            throw new ResourceNotFoundException("Contacts not found for ids {} "+ids);
        }
        // convert the list of contact to a list of ContactDto objects and return it
        return contactMapper.toContactDtos(listContact);
    }
}
