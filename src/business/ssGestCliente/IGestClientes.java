package src.business.ssGestCliente;

import java.util.List;

public interface IGestClientes {
    boolean verificaCliente(String NIF);
    void registarCliente(String nome, String email, String telemovel, String NIF);
    void associarEquipamentoCliente(String codEquipamento, String NIF);
    List<String> consultarEquipamentosCliente(String NIF);
}
