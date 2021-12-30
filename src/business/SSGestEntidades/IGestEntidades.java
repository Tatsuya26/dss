package src.business.SSGestEntidades;

import java.util.List;

import src.business.ObjetoExistenteException;
import src.business.ObjetoNaoExistenteException;

public interface IGestEntidades {
    boolean verificaCliente(String NIF);
    
    boolean verificaEquipamento(int idE);

    int registarEquipamento(String modelo, String descricao, int estado, String NIF) throws ObjetoExistenteException, ObjetoNaoExistenteException;
    
    void registarCliente(String NIF, String nome, String email, String numero) throws ObjetoExistenteException;
    
    String registarFuncionario(String nome,String codigo,int tipo) throws ObjetoExistenteException;
    
    Cliente getClienteByNIF(String NIF) throws ObjetoNaoExistenteException;
    
    Funcionario getFuncionarioByCod(String codF) throws ObjetoNaoExistenteException;
    
    Equipamento getEquipamentoByID(int codE) throws ObjetoNaoExistenteException;
    
    boolean autenticarFuncionario(String codF);
    
    int verificaTipoFuncionario (String codF) throws ObjetoNaoExistenteException;
    
    void alterarEstadoEquipamento(int idE,int estado) throws ObjetoNaoExistenteException;
    
    void baixaEquipamento(int idE) throws ObjetoNaoExistenteException;
    
    List<Equipamento> consultarEquipamentosCliente(String NIF) throws ObjetoNaoExistenteException;

    List<Funcionario> getTecnicosReparacao();
}
