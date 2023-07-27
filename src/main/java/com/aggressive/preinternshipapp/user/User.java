package com.aggressive.preinternshipapp.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import java.util.Collection;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name",length=50, nullable=false, unique=false)
    private String firstName;

    @Column(name = "last_name",length=50, nullable=false, unique=false)

    private String lastName;

    @NaturalId(mutable = false)
    @Column(name = "username")
    private String username;

    @Column(name = "password",length=50, nullable=false, unique=false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
    private Collection<Role> roles;
    private boolean isEnabled = false;

    public User(String firstName, String lastName, String username, String password, Collection<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}
