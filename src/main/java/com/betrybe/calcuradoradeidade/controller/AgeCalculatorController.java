package com.betrybe.calcuradoradeidade.controller;

import com.betrybe.calcuradoradeidade.dto.DateDto;
import com.betrybe.calcuradoradeidade.service.AgeCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
      @RequestParam(defaultValue = "0") String orDefaultAge) {
    System.out.println("entrei no controller");
    int age = service.calculateAge(date);
    System.out.println(age);
    return ResponseEntity.ok(new DateDto(age));
  }

  @GetMapping("/testando")
  public String testando() {
    return "Deu certo essa rota";
  }
}
