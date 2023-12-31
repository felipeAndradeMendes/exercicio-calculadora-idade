package com.betrybe.calcuradoradeidade.controller;

import com.betrybe.calcuradoradeidade.dto.DateDto;
import com.betrybe.calcuradoradeidade.dto.ErrorMessageDto;
import com.betrybe.calcuradoradeidade.exception.FutureDateException;
import com.betrybe.calcuradoradeidade.exception.InvalidSyntaxDateException;
import com.betrybe.calcuradoradeidade.exception.NonNumericDateException;
import com.betrybe.calcuradoradeidade.service.AgeCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe controller de idade.
 */
@RestController
@RequestMapping("/calculateAge")
public class AgeCalculatorController implements AgeCalculatorControllerInterface {
  AgeCalculatorService service;

  @Autowired
  public AgeCalculatorController(AgeCalculatorService service) {
    this.service = service;
  }

  @Override
  @GetMapping()
  public ResponseEntity<DateDto> calculateAge(
      @RequestParam String date,
      @RequestParam(required = false) String orDefaultAge
  ) {
    int age;
    if (orDefaultAge == null) {
      age = service.calculateAge(date);
    } else {
      age = service.calculateAgeWithDefault(date, Integer.parseInt(orDefaultAge));
    }
    return ResponseEntity.ok(new DateDto(age));
  }

  /**
   * Tratamento do erro de data futura.
   *
   * @param exception onde está a msg de erro em string.
   * @return status 422 e msg de erro obtida pelo getMessage().
   */
  @ExceptionHandler
  public ResponseEntity<ErrorMessageDto> handlerUnprocessableEntity(FutureDateException exception) {
    return ResponseEntity
        .status(HttpStatus.UNPROCESSABLE_ENTITY)
        .body(new ErrorMessageDto(exception.getMessage()));
  }

  /**
   * Tratamento de erro de formato de data.
   *
   * @param exception onde está a msg de erro em string.
   * @return status 400 e msg de erro obtida pelo getMessage().
   */
  @ExceptionHandler
  public ResponseEntity<ErrorMessageDto>
      handleInvalidDateFormat(InvalidSyntaxDateException exception) {
    return ResponseEntity
    .status(HttpStatus.BAD_REQUEST)
    .body(new ErrorMessageDto(exception.getMessage()));
  }

  /**
   * Tratamento de erro de data com digitos nao numericos.
   *
   * @param exception - onde está a msg de erro em string.
   * @return - status 400 e msg de erro obtida pelo getMessage()
   */
  @ExceptionHandler
  public ResponseEntity<ErrorMessageDto>
      handleNonNumericDateException(NonNumericDateException exception) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new ErrorMessageDto(exception.getMessage()));
  }
}
