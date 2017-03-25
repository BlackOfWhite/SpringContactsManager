package com.pniewinski.DAO;

import com.pniewinski.entities.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by niewinskip on 2017-03-06.
 */
@Transactional
@Repository
public interface ContactDAO extends CrudRepository<Contact, Integer> {
    List<Contact> findAll();

    Contact findBycId(Integer cId);

    void deleteBycId(Integer cId);

    Contact save(Contact contact);
}
