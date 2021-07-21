package br.com.udemy.osudemy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.udemy.osudemy.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
