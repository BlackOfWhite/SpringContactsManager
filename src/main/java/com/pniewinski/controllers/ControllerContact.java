package com.pniewinski.controllers;

import com.pniewinski.entities.Contact;
import com.pniewinski.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by niewinskip on 2017-03-04.
 */
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/contacts")
public class ControllerContact {

    @Autowired
    private ContactService contactService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Contact> getAllContacts() {
        System.out.println("list:");
        return contactService.getAllContacts();
    }

    @RequestMapping(value = "/{cId}", method = RequestMethod.GET)
    public List<Contact> getContactById(@PathVariable("cId") Integer cId) {
        List<Contact> contacts = new ArrayList<>();
        contacts.add(contactService.getContactById(cId));
        return contacts;
    }

    @RequestMapping(value = "/{cId}", method = RequestMethod.DELETE)
    public void deleteContactById(@PathVariable("cId") Integer cId) {
        contactService.deleteContactById(cId);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addContact(@RequestBody Contact contact) {
        contactService.saveContact(contact);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateContact(@RequestBody Contact contact) {
        Contact old = contactService.getContactById(contact.getcId());
        old.setCompanyName(contact.getCompanyName());
        contactService.saveContact(old);
    }
}
