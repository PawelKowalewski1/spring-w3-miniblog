package pl.reaktor.w3.miniblog.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Date added;
    @Column(name = "enabled")
    private Boolean active;

    @ManyToMany
    @JoinTable(name = "user_role")
    private Set<Role> roles = new HashSet<>();



}
