package com.phucnghia.backend_sercurity.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name ="tbl_group_has_user")
public class GroupHasUser extends AbstractEntity<Integer>{
    @ManyToOne
    @JoinColumn(name ="group_id")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
