package com.pniewinski.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pniewinski.roles.Role;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by niewinskip on 2017-03-06.
 */
@Entity
@Table(name = "Users")
public class User {

    private int uId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private List<Contact> contactList;

    public User() {

    }

    public User(int uId, String firstName, String lastName, String email) {
        this.uId = uId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "user_id", unique = true)
    public int getuId() {
        return uId;
    }

    public void setuId(int userId) {
        this.uId = userId;
    }

    @Column(name = "first_name")
    @NotNull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    @NotNull
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "email", unique = true)
    @NotNull
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    public Role getRole() {return role;}

    public void setRole(Role role) {
        this.role = role;
    }


    //    @JoinTable(
////            name = "user_contact", // table name
//            joinColumns = @JoinColumn(name = "uId"),
//            inverseJoinColumns = @JoinColumn(name = "cId")
//    )

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "owner")
    // field name used in contact entity
    @JsonManagedReference
    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }
}
