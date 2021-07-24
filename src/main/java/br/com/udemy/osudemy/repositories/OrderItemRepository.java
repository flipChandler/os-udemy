package br.com.udemy.osudemy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.udemy.osudemy.entities.OrderItem;
import br.com.udemy.osudemy.entities.pk.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK>{ // pode ser tmb <OrdermItem, Long>

}
