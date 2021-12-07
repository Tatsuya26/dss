package src.business;

public class GestCRFacade implements IGestCRLN {
    private IGestCliente      gestCliente;
    private IGestFuncionario  gestFuncionario;
    private IGestEquipamentos gestEquipamentos;
    private IGestRegistos     gestRegistos;

    public GestCRFacade() {
        this.gestCliente      = new GestClienteFacade();
        this.gestEquipamentos = new GestEquipamentosFacade();
        this.gestFuncionario  = new GestFuncionariosFacade();
        this.gestRegistos     = new GestRegistosFacade();
    }
}
