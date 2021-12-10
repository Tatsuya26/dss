package src.business.ssGestCliente;

import java.util.List;

public interface IGestClientes {
    boolean verificaCliente(String NIF);
    void registarCliente(String NIF, String nome, String email, String numero);
    void associarEquipamentoCliente(String codEquipamento, String NIF);
    List<String> consultarEquipamentosCliente(String NIF);
}
