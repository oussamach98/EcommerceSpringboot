package com.ecommerce.site.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userName;
    private String userFirstName;
    private String userLastName;
    private String userPassword;
    private String email;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE",
            joinColumns = {
                    @JoinColumn(name = "USER_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID")
            }
    )
    private Set<Role> role;
public int getId() {
        return id;
}
public void setId(int id) {
        this.id = id;
}
public String getUserName() {
        return userName;
}
public void setUserName(String userName) {
        this.userName = userName;
}
public String getUserFirstName() {
        return userFirstName;
}
public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
}
public String getUserLastName() {
        return userLastName;
}
public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
}
public String getUserPassword() {
        return userPassword;
}
public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
}
public String getEmail() {
        return email;
}
public void setEmail(String email) {
        this.email = email;
}
public Set<Role> getRole() {
        return role;
}
public void setRole(Set<Role> role) {
        this.role = role;
}



}
