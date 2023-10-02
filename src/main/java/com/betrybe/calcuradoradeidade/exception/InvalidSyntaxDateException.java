package com.betrybe.calcuradoradeidade.exception;

/**
 * Classe de erro de formato de data customizado.
 */
public class InvalidSyntaxDateException extends RuntimeException {

  public InvalidSyntaxDateException(String msg) {
    super(msg);
  }
}
