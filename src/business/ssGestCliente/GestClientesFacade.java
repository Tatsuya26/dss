package src.business.ssGestCliente;

import java.util.Map;

public class GestClientesFacade implements IGestClientes {
    private Map<String,Cliente> clientes;
    

    public GestClientesFacade(Map<String,Cliente> clientes) {
        this.clientes = clientes;
    }
    

    public GestClientesFacade() {
    }

    public void adicionaCliente(Cliente c) {
        this.clientes.put(c.getNIF(), c.clone());
    }
    //implementar interface
}
