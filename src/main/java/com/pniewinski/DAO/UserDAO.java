package com.pniewinski.DAO;

import com.pniewinski.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by niewinskip on 2017-03-06.
 */
@Transactional
@Repository
public interface UserDAO extends CrudRepository<User, Integer> {
    List<User> findAll();

    User findByuId(Integer uId);

    User findByEmail(String email);

    void deleteByuId(Integer uId);

    User save(User user);
}
