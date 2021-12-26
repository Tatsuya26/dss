package src.business.SSGestEntidades;

import java.util.List;

import src.business.ObjetoExistenteException;
import src.business.ObjetoNaoExistenteException;

public interface IGestEntidades {
    boolean verificaCliente(String NIF);
    
    void registarCliente(String NIF, String nome, String email, String numero) throws ObjetoExistenteException;
    
    List<Equipamento> consultarEquipamentosCliente(String NIF) throws ObjetoNaoExistenteException;

    Cliente getClienteByNIF(String NIF) throws ObjetoNaoExistenteException;
    
    Funcionario getFuncionarioByCod(String codF) throws ObjetoNaoExistenteException;

    Equipamento getEquipamentoByID(int codE) throws ObjetoNaoExistenteException;

    boolean autenticarFuncionario(String codF);

    int verificaTipoFuncionario (String codF) throws ObjetoNaoExistenteException;

    String registarFuncionario(String nome,int tipo) throws ObjetoExistenteException;

    void removerFuncionario(String cod) throws ObjetoNaoExistenteException;

    boolean verificaEquipamento(int idE);
   
    void alterarEstadoEquipamento(int idE,int estado) throws ObjetoNaoExistenteException;
    
    void baixaEquipamento(int idE) throws ObjetoNaoExistenteException;
    
    int registarEquipamento(String modelo, String descricao, int estado, String NIF) throws ObjetoExistenteException, ObjetoNaoExistenteException;
}
