package br.com.udemy.osudemy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.udemy.osudemy.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
