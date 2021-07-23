package br.com.udemy.osudemy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.udemy.osudemy.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
