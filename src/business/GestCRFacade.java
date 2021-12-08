package src.business;

import src.business.ssGestCliente.*;
import src.business.ssGestEquipamentos.*;



public class GestCRFacade implements IGestCRLN {
    private IGestClientes      gestCliente;
    private IGestEquipamentos gestEquipamentos;
    private IGestFuncionario  gestFuncionario;
    private IGestRegistos     gestRegistos;

    public GestCRFacade() {
        this.gestCliente      = new GestClientesFacade();
        this.gestEquipamentos = new GestEquipamentosFacade();
        this.gestFuncionario  = new GestFuncionariosFacade();
        this.gestRegistos     = new GestRegistosFacade();
    }
}
