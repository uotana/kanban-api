package com.bdweb.kanbanapi.repositories;

import com.bdweb.kanbanapi.models.Customer;
import com.bdweb.kanbanapi.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByName(String name);
}
