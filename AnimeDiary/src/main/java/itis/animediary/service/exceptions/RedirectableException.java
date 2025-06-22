package itis.animediary.service.exceptions;

public abstract class RedirectableException extends RuntimeException {

  private final String redirectUrl;

  public RedirectableException(String message, String redirectUrl) {
    super(message);
    this.redirectUrl = redirectUrl;
  }

  public RedirectableException(String message, String redirectUrl, Throwable cause) {
    super(message, cause);
    this.redirectUrl = redirectUrl;
  }

  public String getRedirectUrl() {
    return redirectUrl;
  }
}
