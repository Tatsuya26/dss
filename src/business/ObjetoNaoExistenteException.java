package src.business;


public class ObjetoNaoExistenteException extends Exception{
    public ObjetoNaoExistenteException() {
        super();
    }

    public ObjetoNaoExistenteException(String msg) {
        super(msg);
    }
}
