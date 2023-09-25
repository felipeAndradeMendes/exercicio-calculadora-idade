package com.betrybe.calcuradoradeidade.controller;

import com.betrybe.calcuradoradeidade.dto.DateDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Essa é a interface utilizada para implementar a controller da calculadora de idade.
 */
public interface AgeCalculatorControllerInterface {
  ResponseEntity<DateDto> calculateAge(@RequestParam String date,
      @RequestParam(required = false) String orDefaultAge);
}
