package com.pniewinski.services;

import com.pniewinski.DAO.UserDAO;
import com.pniewinski.entities.Contact;
import com.pniewinski.entities.User;
import com.pniewinski.roles.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by niewinskip on 2017-03-06.
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserDAO usersDAO;

    public List<User> getAllUsers() {
        return usersDAO.findAll();
    }

    public User getUserById(Integer uId) {
        return usersDAO.findByuId(uId);
    }

    public User getUserByEmail(String email) {
        return usersDAO.findByEmail(email);
    }

    public void deleteUserById(Integer uId) {
        usersDAO.deleteByuId(uId);
    }

    public User saveUser(User user) {
        return usersDAO.save(user);
    }

    public List<Contact> getUserContacts(Integer uId) {
        return getUserById(uId).getContactList();
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = usersDAO.findByEmail(username);
        if (user == null) {
            return null;
        }
        List<GrantedAuthority> auth = AuthorityUtils
                .commaSeparatedStringToAuthorityList("USERS");

        if (user.getRole().equals(Role.ADMIN)) {
            auth = AuthorityUtils
                    .commaSeparatedStringToAuthorityList("ADMIN");
            user.setRole(Role.ADMIN);
        }
        return new org.springframework.security.core.userdetails.User(username, user.getPassword(),
                auth);
    }

}