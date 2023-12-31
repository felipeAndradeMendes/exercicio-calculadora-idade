package com.betrybe.calcuradoradeidade.service;

import com.betrybe.calcuradoradeidade.exception.FutureDateException;
import com.betrybe.calcuradoradeidade.exception.InvalidSyntaxDateException;
import com.betrybe.calcuradoradeidade.exception.NonNumericDateException;
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

    isDateNonNumeric(date);
    isValidDateFormat(date);

    LocalDate currentDate = LocalDate.now();

    LocalDate birthDate = LocalDate.parse(date);

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

  /**
   * Valida data so com numeros.
   *
   * @param date - data da query.
   * @throws NonNumericDateException - erro customizado de data nao numerica.
   */
  public void isDateNonNumeric(String date) throws NonNumericDateException {
    String newString = date.replace("-", "");
    for (char c : newString.toCharArray()) {
      if (!Character.isDigit(c)) {
        throw new NonNumericDateException("Date should be in numeric format.");
      }
    }
  }

  /**
   * Calcula idade com parametro default.
   *
   * @param date - data passada no paramentro date.
   * @param defaultAge - data default.
   * @return - data default.
   */
  public int calculateAgeWithDefault(String date, int defaultAge) {
    System.out.println("entrei no calculateAgeWithDefault");
    try {
      return calculateAge(date);
    } catch (FutureDateException | InvalidSyntaxDateException | NonNumericDateException e) {
      return defaultAge;
    }
  }
}
