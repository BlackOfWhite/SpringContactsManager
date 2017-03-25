package com.pniewinski.DAO;

import com.pniewinski.entities.Contact;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by niewinskip on 2017-03-04.
 */
@Repository
public class ContactDAOImpl {

    private static Map<Integer, Contact> contactMap;

    static {
        contactMap = new HashMap<>();
        contactMap.put(1, new
                Contact(1, "Piotr1", "Niewinski1", "Telcom1", "niewinskipiotr1@op.pl", "696295401"));
        contactMap.put(2, new
                Contact(2, "Piotr2", "Niewinski2", "Telcom2", "niewinskipiotr2@op.pl", "696295402"));
        contactMap.put(3, new
                Contact(3, "Piotr3", "Niewinski3", "Telcom3", "niewinskipiotr3@op.pl", "696295403"));
    }

//    @Override
//    public Collection<Contact> getAllContacts() {
//        return this.contactMap.values();
//    }
//
//    @Override
//    public Contact getContactById(Integer cId) {
//        return this.contactMap.get(cId);
//    }
//
//    @Override
//    public void deleteContactById(Integer cId) {
//        contactMap.remove(cId);
//    }
//
//    @Override
//    public void addContact(Contact contact) {
//        contactMap.put(contact.getUserId(), contact);
//    }
}
