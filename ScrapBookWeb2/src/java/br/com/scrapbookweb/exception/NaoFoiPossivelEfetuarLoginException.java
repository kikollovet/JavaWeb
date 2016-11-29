
package br.com.scrapbookweb.exception;

public class NaoFoiPossivelEfetuarLoginException extends Exception {

    public NaoFoiPossivelEfetuarLoginException() {
    }

    public NaoFoiPossivelEfetuarLoginException(String msg) {
        super(msg);
    }
}
