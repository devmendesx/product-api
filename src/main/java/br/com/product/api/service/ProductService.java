package br.com.product.api.service;

import br.com.product.api.domain.Product;
import br.com.product.api.dto.MessageDto;
import br.com.product.api.dto.ProductRequestDto;
import br.com.product.api.dto.ProductResponseDto;
import br.com.product.api.exception.ProductNotFoundException;
import br.com.product.api.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductService {

  private final ProductRepository productRepository;

  public List<ProductResponseDto> findAll() {
    try {
      var allProducts = this.productRepository.findAll();
      return allProducts.stream().map(ProductResponseDto::new).toList();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Transactional(value = Transactional.TxType.REQUIRES_NEW)
  public void create(ProductRequestDto productRequest) {
    try {
      var product = Product.builder()
            .codProduct(UUID.randomUUID())
            .name(productRequest.getName())
            .price(productRequest.getPrice())
            .build();
      this.productRepository.save(product);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Transactional(value = Transactional.TxType.REQUIRES_NEW)
  public ProductResponseDto update(UUID codProduct, ProductRequestDto productRequestDto) {
    var productOptional = this.productRepository.findByCodProduct(codProduct);
    productOptional.ifPresentOrElse(product1 -> {
      product1.setName(productRequestDto.getName());
      product1.setPrice(productRequestDto.getPrice());
      this.productRepository.save(product1);
    }, () -> {
      throw new ProductNotFoundException("Produto não encontrado");
    });

    return ProductResponseDto.builder()
          .codProduct(codProduct)
          .name(productRequestDto.getName())
          .price(productRequestDto.getPrice())
          .build();
  }

  @Transactional(value = Transactional.TxType.REQUIRES_NEW)
  public void delete(UUID codProduct) {
    var productOptional = this.productRepository.findByCodProduct(codProduct);
    productOptional.ifPresent(this.productRepository::delete);
  }

}
