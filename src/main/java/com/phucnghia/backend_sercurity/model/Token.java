package com.phucnghia.backend_sercurity.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_token")
public class Token extends AbstractEntity<Integer> {
    @Column(name ="username", unique = true)
    private String username;

    @Column(name ="access_token")
    private String accessToken;

    @Column(name ="refresh_token")
    private String refreshToken;


}
