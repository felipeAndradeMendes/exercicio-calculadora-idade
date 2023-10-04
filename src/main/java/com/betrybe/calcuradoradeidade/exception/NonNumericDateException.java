package com.betrybe.calcuradoradeidade.exception;

/**
 * Classe de erro de data com caracteres nao numericos.
 */
public class NonNumericDateException extends RuntimeException {
  public NonNumericDateException(String msg) {
    super(msg);
  }
}
