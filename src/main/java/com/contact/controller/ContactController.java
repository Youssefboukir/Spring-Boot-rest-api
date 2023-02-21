package com.contact.controller;


import com.contact.dto.ContactDto;
import com.contact.exception.AlreadyExistsException;
import com.contact.exception.ResourceNotFoundException;
import com.contact.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {

    private static final Logger log = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    @GetMapping("/list")
    public ResponseEntity<List<ContactDto>> getListContact(){
        log.info("Request for getting all the contact");
        // return an OK response with the list of contacts
        return new ResponseEntity<>(contactService.getAllContact(), HttpStatus.OK);
    }

    @GetMapping("/list-by-ids")
    public ResponseEntity<List<ContactDto>> getListContactByIds(@RequestParam("ids") List<String> ids){
        try {
            log.info("Request for getting contact by ids");
            // return an OK response with the list of contacts found by their IDs
            return new ResponseEntity<>(contactService.getContactByIds(ids), HttpStatus.OK);
        }catch (ResourceNotFoundException ex){
            // log an error message if the contact is not found
            log.error(ex.getMessage());
            // throw a ResponseStatusException with a NOT_FOUND status code and the exception message
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    @GetMapping("/{idContact}")
    public ResponseEntity<ContactDto> getContactById(@PathVariable("idContact") String idContact){
        try {
            log.info("Request for getting contact");
            // find the contact by its id and return an OK response with the found contact information
            return new ResponseEntity<>(contactService.getContactById(idContact),HttpStatus.OK);
        }catch (ResourceNotFoundException ex){
            // log an error message if the contact is not found
            log.error(ex.getMessage());
            // throw a ResponseStatusException with a NOT_FOUND status code and the exception message
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<ContactDto> createContact(@Valid @RequestBody ContactDto contactDto){
        try {
            log.info("Request for adding new contact");
            // save the new contact and return an CREATED response with the new contact information
            return new ResponseEntity<>(contactService.saveContact(contactDto),HttpStatus.CREATED);
        }catch (AlreadyExistsException ex){
            // log an error message if the contact already exists
            log.error(ex.getMessage());
            // throw a ResponseStatusException with a BAD_REQUEST status code and the exception message
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }

    @PutMapping("update/{idContact}")
    public ResponseEntity<ContactDto> updateContact(@Valid @PathVariable("idContact")String idContact ,@RequestBody ContactDto contactDto){
        try {
            log.info("Request for updating contact");
            // update contact and return an ok response with the contact information
            return new ResponseEntity<>(contactService.updateContact(contactDto,idContact),HttpStatus.OK);
        }catch (ResourceNotFoundException ex){
            // log an error message if the contact is not found
            log.error(ex.getMessage());
            // throw a ResponseStatusException with a BAD_REQUEST status code and the exception message
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    @DeleteMapping("delete/{idContact}")
    public ResponseEntity<ContactDto> deleteContact(@PathVariable("idContact") String idContact){
        try {
            log.info("Request for deleting contact");
            // delete contact and return an ok response with the contact information
            return new ResponseEntity<>(contactService.deleteContact(idContact),HttpStatus.OK);
        }catch (ResourceNotFoundException ex){
            // log an error message if the contact is not found
            log.error(ex.getMessage());
            // throw a ResponseStatusException with a NOT_FOUND status code and the exception message
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }
}
