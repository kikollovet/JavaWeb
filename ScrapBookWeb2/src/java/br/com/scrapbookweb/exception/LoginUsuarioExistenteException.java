
package br.com.scrapbookweb.exception;

public class LoginUsuarioExistenteException extends Exception {

    public LoginUsuarioExistenteException() {
    }

    public LoginUsuarioExistenteException(String msg) {
        super(msg);
    }
}
