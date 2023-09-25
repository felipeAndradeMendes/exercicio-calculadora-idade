package com.betrybe.calcuradoradeidade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.betrybe.calcuradoradeidade.dto.DateDto;
import com.betrybe.calcuradoradeidade.dto.ErrorMessageDto;
import java.time.Year;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApiTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  private String getAgeFor(String date) {
    return String.format("http://localhost:%s/calculateAge?date=%s", port, date);
  }

  private String getAgeFor(String date, int defaultAge) {
    return String.format("http://localhost:%s/calculateAge?date=%s&orDefaultAge=%d", port, date,
        defaultAge);
  }

  @Test
  @DisplayName("1 - Crie o endpoint GET /calculateAge")
  void testValidDate() throws Exception {
    ResponseEntity<DateDto> response =
        restTemplate.getForEntity(getAgeFor("2000-01-01"), DateDto.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    int expectedAge = Year.now().getValue() - 2000;
    assertEquals(expectedAge, response.getBody().age());
  }

  @Test
  @DisplayName("2 - Crie um fluxo de exceção para datas futuras")
  void testFutureDate() throws Exception {
    ResponseEntity<ErrorMessageDto> response =
        restTemplate.getForEntity(getAgeFor("2077-12-10"), ErrorMessageDto.class);
    assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
    assertEquals("This is a future date.", response.getBody().error());
  }

  @Test
  @DisplayName("3 - Crie um fluxo de exceção para datas com formato inválido")
  void testInvalidSyntaxDate() throws Exception {
    ResponseEntity<ErrorMessageDto> response =
        restTemplate.getForEntity(getAgeFor("12-10"), ErrorMessageDto.class);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals("Invalid date format. Expected aa-mm-dd.", response.getBody().error());
  }

  @Test
  @DisplayName("4 - Crie um fluxo de exceção para datas não numéricas")
  void testNonNumericDate() throws Exception {
    ResponseEntity<ErrorMessageDto> response =
        restTemplate.getForEntity(getAgeFor("2077-dez-10"), ErrorMessageDto.class);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals("Date should be in numeric format.", response.getBody().error());
  }

  @Test
  @DisplayName("5 - Implemente o retorno de um valor padrão para a idade com o query parameter orDefaultAge")
  void testDefaultAge() throws Exception {
    ResponseEntity<DateDto> response =
        restTemplate.getForEntity(getAgeFor("12-10", 0), DateDto.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(0, response.getBody().age());
  }
}
