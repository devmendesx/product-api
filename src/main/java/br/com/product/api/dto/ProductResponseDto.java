package br.com.product.api.dto;


import br.com.product.api.domain.Product;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ProductResponseDto {
  private UUID codProduct;
  private String name;
  private BigDecimal price;

  public ProductResponseDto(Product product) {
    this.codProduct = product.getCodProduct();
    this.name = product.getName();
    this.price = product.getPrice();
  }
}
