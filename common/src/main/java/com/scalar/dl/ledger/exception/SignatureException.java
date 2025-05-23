package com.scalar.dl.ledger.exception;

import com.scalar.dl.ledger.error.ScalarDlError;
import com.scalar.dl.ledger.service.StatusCode;

public class SignatureException extends SecurityException {

  public SignatureException(String message) {
    super(message, StatusCode.INVALID_SIGNATURE);
  }

  public SignatureException(String message, Throwable cause) {
    super(message, cause, StatusCode.INVALID_SIGNATURE);
  }

  public SignatureException(ScalarDlError error, Object... args) {
    super(error.buildMessage(args), error.getStatusCode());
  }
}
