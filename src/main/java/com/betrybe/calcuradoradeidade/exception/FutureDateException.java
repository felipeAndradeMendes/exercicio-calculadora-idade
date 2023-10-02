package com.betrybe.calcuradoradeidade.exception;

/**
 * Classe de exceção de data futura.
 */
public class FutureDateException extends RuntimeException {
  public FutureDateException(String msg) {
    super(msg);
  }
}
