package src.business.ssGestCliente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestClientesFacade implements IGestClientes {
    private Map<String,Cliente> clientes;
    

    public GestClientesFacade() {
        this.clientes = new HashMap<>();
    }
    
    public GestClientesFacade(Map<String,Cliente> clientes) {
        this.clientes = clientes;
    }
    

    // Método que cria o cliente e o regista na base de dados
    public void registarCliente(String NIF, String nome, String email, String numero){
        Cliente c = new Cliente(nome, NIF, email, numero, new ArrayList<>());
        this.clientes.put(c.getNIF(), c);
    }
    
    // Método que verifica se o cliente existe na estrutura de dados
    public boolean verificaCliente(String NIF) {
        if (this.clientes.containsKey(NIF)) return true;
        return false;
    }

    // Método que adiciona o equipamento ao cliente com o NIF indicado
    public void associarEquipamentoCliente(String codEquipamento, String NIF) {
        Cliente c = this.clientes.get(NIF);
        c.adicionaEquipamento(codEquipamento);
    }

    // Método que indica os equipamentos de um cliente
    public List<String> consultarEquipamentosCliente(String NIF) {
        Cliente c = this.clientes.get(NIF);
        return c.getEquipamentosCliente();
    }
}
