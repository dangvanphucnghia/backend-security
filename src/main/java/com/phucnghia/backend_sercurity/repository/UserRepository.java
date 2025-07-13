package com.phucnghia.backend_sercurity.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.phucnghia.backend_sercurity.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);

    @Query(value = "select r.name from Role rinner join UserHasRole ur on r.id = ur.user.id where ur.id= :userId")
    List<String> findAllRolesByUserId(Long userId);

    @Query(value = "select u from User u where " +
            "(lower(u.firstName) like lower(concat('%', :keyword, '%')) " +
            "or lower(u.lastName) like lower(concat('%', :keyword, '%')) " +
            "or lower(u.username) like lower(concat('%', :keyword, '%')) " +
            "or lower(u.email) like lower(concat('%', :keyword, '%')) " +
            "or u.phone like concat('%', :keyword, '%'))")
    Page<User> searchByKeyword(String keyword, Pageable pageable);

}
