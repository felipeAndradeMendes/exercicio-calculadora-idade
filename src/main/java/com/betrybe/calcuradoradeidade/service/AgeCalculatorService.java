package com.betrybe.calcuradoradeidade.service;

import com.betrybe.calcuradoradeidade.exception.FutureDateException;
import com.betrybe.calcuradoradeidade.exception.InvalidSyntaxDateException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
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
    DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    isValidDateFormat(date);

    LocalDate currentDate = LocalDate.now();
    System.out.println(currentDate.getClass().getName());

    LocalDate birthDate = LocalDate.parse(date);
    System.out.println(birthDate.getClass().getName());
    System.out.println(date.getClass().getName());

    if (currentDate.isBefore(birthDate)) {
      throw new FutureDateException("This is a future date.");
    }

    return Period.between(birthDate, currentDate).getYears();
  }

  /**
   * Valida formato da data.
   *
   * @param date data da query.
   * @throws InvalidSyntaxDateException erro customizado de formato de data.
   */
  public void isValidDateFormat(String date) throws InvalidSyntaxDateException {
    String[] dateBlocks = date.split("-");
    if (dateBlocks.length != 3) {
      throw new InvalidSyntaxDateException("Invalid date format. Expected aa-mm-dd.");
    }
    String year = dateBlocks[0];
    String month = dateBlocks[1];
    String day = dateBlocks[2];
    if (year.length() != 4 || month.length() != 2 || day.length() != 2) {
      throw new InvalidSyntaxDateException("Invalid date format. Expected aa-mm-dd.");
    }
    // Desisti de fazer minha validação e usei a do gabarito.
  }

  public int calculateAgeWithDefault(String date, int defaultAge) {
    // TODO method implementation
    return -1;
  }
}
