package br.com.product.api.repository;

import br.com.product.api.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  Optional<Product> findByCodProduct(UUID codProduct);

}