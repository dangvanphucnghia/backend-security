package com.phucnghia.backend_sercurity.model;

import com.phucnghia.backend_sercurity.util.Gender;
import com.phucnghia.backend_sercurity.util.UserStatus;
import com.phucnghia.backend_sercurity.util.UserType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_user")
public class User extends AbstractEntity<Long> implements UserDetails, Serializable {

    @Column(name ="first_name")
    private String firstName;

    @Column(name ="last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name ="gender")
    private Gender gender;

    @Column(name ="phone")
    private String phone;

    @Column(name ="email")
    private String email;

    @Column(name ="username")
    private String username;

    @Column(name = "password")
    private String password;


    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "type")
    private UserType type;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name="status")
    private UserStatus status;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Address> addresses = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<UserHasRole> roles = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<GroupHasUser> groups = new HashSet<>();

    public void saveAddress(Address address){
        if(address != null){
            if(addresses == null){
                addresses = new HashSet<>();
            }
            addresses.add(address);
            address.setUser(this);
        }
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
