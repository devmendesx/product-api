package br.com.product.api.config;

import br.com.product.api.dto.MessageDto;
import br.com.product.api.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@org.springframework.web.bind.annotation.RestControllerAdvice
public class RestControllerAdvice {
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(ProductNotFoundException.class)
  public MessageDto handleProductNotFound(ProductNotFoundException exception) {
    return MessageDto.builder().message(exception.getMessage()).build();
  }

}
