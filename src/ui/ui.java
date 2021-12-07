package src.ui;

//import camada de lógica
import src.business.*;

public class ui {
    private IGestCRLN facade;
    public ui() {
        this.facade = GestCRFacade();
    }
}
