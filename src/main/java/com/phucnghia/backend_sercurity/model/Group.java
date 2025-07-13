package com.phucnghia.backend_sercurity.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_group")
public class Group  extends AbstractEntity<Integer>{
    @Column(name ="name")
    private String name;
    @Column(name="description")
    private String description;

    @OneToOne
    @JoinColumn(name ="role_id")
    private Role role;
}
