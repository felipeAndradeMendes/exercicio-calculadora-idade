package com.betrybe.calcuradoradeidade.service;

import java.time.LocalDate;
import java.time.Period;
import org.springframework.stereotype.Service;

/**
 * Aqui nessa classe devem ser implementados os métodos para atender aos requisitos do exercício,
 * sinta-se à vontade para criar métodos privados para te auxiliar nas validações.
 */
@Service
public class AgeCalculatorService {

  /**
   * Metodo de calculo da idade.
   *
   * @param date data de nascimento.
   * @return inteiro de anos.
   */
  public int calculateAge(String date) {
    LocalDate currentDate = LocalDate.now();
    LocalDate birthDate = LocalDate.parse(date);
    return Period.between(birthDate, currentDate).getYears();
  }

  public int calculateAgeWithDefault(String date, int defaultAge) {
    // TODO method implementation
    return -1;
  }
}
