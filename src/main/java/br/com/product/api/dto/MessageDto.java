package br.com.product.api.dto;

import br.com.product.api.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class MessageDto {
  private String message;

  public MessageDto(Product product) {
    this.message = product.getName();
  }

}
