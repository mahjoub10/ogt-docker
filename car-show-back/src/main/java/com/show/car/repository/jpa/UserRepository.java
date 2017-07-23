package com.show.car.repository.jpa;

import com.show.car.domain.jpa.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLoginAndActivatedIsTrue(String login);

    Optional<User> findByLogin(String login);
}
