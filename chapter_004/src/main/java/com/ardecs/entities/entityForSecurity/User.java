package com.ardecs.entities.entityForSecurity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 02.07.2019
 */
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @Column(nullable = false, unique = true)
    @Size(max = 16)
    @NotEmpty(message = "*Please provide your name")
    @Pattern(regexp = "[a-zA-Z]+", message = "*The name must contains only letters.")
    private String name;

    @Column(nullable = false, unique = true)
    @NotEmpty(message = "*Please provide your password")
    @Size(min = 4, message = "*Password length must be more than 4 characters")
    private String password;

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user", referencedColumnName = "name")},
            inverseJoinColumns = {@JoinColumn(name = "role", referencedColumnName = "name")})
    @NotNull(message = "*Please provide some Role: ADMIN; CREATOR; UPDATER; VIEWER.")
    private List<Role> roles;

    public User() {
    }

    public User(@NotEmpty() String name, @NotEmpty @Size(min = 4) String password, List<Role> roles) {
        this.name = name;
        this.password = password;
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
