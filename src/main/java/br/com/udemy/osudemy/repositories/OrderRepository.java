package br.com.udemy.osudemy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.udemy.osudemy.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
