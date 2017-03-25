package com.pniewinski.services;

import com.pniewinski.DAO.ContactDAO;
import com.pniewinski.entities.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by niewinskip on 2017-03-04.
 */
@Service
public class ContactService {

    @Autowired
    private ContactDAO contactsDAO;

    public List<Contact> getAllContacts() {
        return contactsDAO.findAll();
    }

    public Contact getContactById(Integer cId) {
        return contactsDAO.findBycId(cId);
    }

    public void deleteContactById(Integer cId) {
        contactsDAO.deleteBycId(cId);
    }

    public void saveContact(Contact contact) {
        contactsDAO.save(contact);
    }
}
