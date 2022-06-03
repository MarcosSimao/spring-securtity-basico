package com.app.seguranca.repository;

import com.app.seguranca.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRespository extends JpaRepository<Users,Long> {

    Optional<Users>findByName(String username);
}
