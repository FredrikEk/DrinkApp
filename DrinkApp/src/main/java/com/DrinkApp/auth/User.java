package com.DrinkApp.auth;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="USERS")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(nullable = false)  // unique is implied
    protected String username;
    @Column(nullable = false)
    protected String passwd;
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "USERS_GROUPS", 
            joinColumns = @JoinColumn(name = "username"))
    @Enumerated(EnumType.STRING)
    protected List<Groups> groups = new ArrayList<>();

    public User() {
    }

    public User(String username, String passwd, Groups group) {
        this.username = username;
        this.passwd = passwd;
        groups.add(group);
    }

    public void addGroup(Groups group) {
        groups.add(group);
    }

    public void removeGroup(Groups group) {
        groups.remove(group);
    }

    public List<Groups> getGroups() {
        return groups;
    }

    public String getUsername() {
        return username;
    }

    public void set(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.username);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        return Objects.equals(this.username, other.username);
    }

}
