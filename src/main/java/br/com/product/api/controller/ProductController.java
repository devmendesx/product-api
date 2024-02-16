package br.com.product.api.controller;

import br.com.product.api.dto.MessageDto;
import br.com.product.api.dto.ProductRequestDto;
import br.com.product.api.dto.ProductResponseDto;
import br.com.product.api.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController {

  private final ProductService productService;

  @GetMapping
  public ResponseEntity<List<ProductResponseDto>> getAll() {
    return ResponseEntity.ok(this.productService.findAll());
  }

  @PostMapping
  public ResponseEntity<MessageDto> create(@RequestBody ProductRequestDto productRequest) {
    this.productService.create(productRequest);
    return ResponseEntity.ok(MessageDto.builder()
          .message("Produto criado com sucesso!").build());
  }

  @PutMapping("/{codProduct}")
  public ResponseEntity<?> update(@RequestBody ProductRequestDto productRequestDto,
                                                   @PathVariable(name = "codProduct") UUID codProduct) {
    return ResponseEntity.ok(this.productService.update(codProduct, productRequestDto));
  }

  @DeleteMapping("/{codProduct}")
  public ResponseEntity<MessageDto> delete(@PathVariable(name = "codProduct") UUID codProduct) {
    this.productService.delete(codProduct);
    return ResponseEntity.ok(MessageDto.builder()
          .message("Produto deletado com sucesso!").build());
  }
}
