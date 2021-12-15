package src.business.SSGestEntidades;

import java.util.List;

public interface IGestEntidades {
    boolean verificaCliente(String NIF);
    
    void registarCliente(String NIF, String nome, String email, String numero);
    
    List<String> consultarEquipamentosCliente(String NIF);
    
    boolean autenticarFuncionario(String codF);

    int verificaTipoFuncionario (String codF);

    String registarFuncionario(String nome,int tipo);

    void removerFuncionario(String cod);

    boolean verificaEquipamento(String codE);
   
    void alterarEstado(String codE,int estado);
    
    void baixaEquipamento(String codE);
    
    void registarEquipamento(String codEquipamento, String modelo, String descricao, int estado);
    
    void associarEquipamentoCliente(String codEquipamento, String NIF);
}
