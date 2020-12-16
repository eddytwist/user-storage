package com.innowise_group.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class User implements Serializable {

    private static final long serialVersionUID = 5027143598751233241L;
    private static final AtomicInteger count = new AtomicInteger(0);
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private Set<Role> roles;
    private Set<String> phoneNumbers;

    public User(String firstName, String lastName, String email, Set<Role> roles, Set<String> phoneNumbers) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = roles;
        this.phoneNumbers = phoneNumbers;
    }

    public User(String firstName, String lastName) {
        this.id = count.incrementAndGet();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = "";
        this.roles = new HashSet<>();
        this.phoneNumbers = new HashSet<>();
    }

    public User(User user) {
        this.id = user.id;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.email = user.email;
        this.roles = user.roles;
        this.phoneNumbers = user.phoneNumbers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(Set<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getUserFields() {
        return "\nUser id '" + getId() + "' fields: " +
                "\n1. First name: [" + getFirstName() + "]" +
                "\n2. Last name: [" + getLastName() + "]" +
                "\n3. Email: [" + getEmail() + "]" +
                "\n4. Roles: " + getRoles() +
                "\n5. Phone numbers: " + getPhoneNumbers() + "";
    }

    @Override
    public String toString() {
        return "User id='" + id + "', " +
                "First name:'" + firstName + "', " +
                "Last name:'" + lastName + "', " +
                "Email:'" + email + "', " +
                "Roles:" + roles + ", " +
                "Phone numbers:" + phoneNumbers + ". ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final User user = (User) o;
        return getId() == (user.getId()) &&
                getFirstName().equals(user.getFirstName()) &&
                getLastName().equals(user.getLastName()) &&
                getEmail().equals(user.getEmail()) &&
                getRoles().equals(user.getRoles()) &&
                getPhoneNumbers().equals(user.getPhoneNumbers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLastName(), getFirstName());
    }
}
