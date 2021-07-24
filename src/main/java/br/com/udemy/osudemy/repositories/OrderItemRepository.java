package br.com.udemy.osudemy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.udemy.osudemy.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{ // pode ser tmb <OrdermItem, Long>

}
