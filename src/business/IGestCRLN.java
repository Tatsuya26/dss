package src.business;


import java.time.LocalDateTime;
import java.util.*;

import src.business.ssGestRegistos.Passo;

public interface IGestCRLN {
    int autenticarFuncionario(String codF) throws ObjetoNaoExistenteException;

    void logoutFuncionario();

    boolean verificaEquipamento(int codE);

    boolean verificaCliente(String codC);

    int registarEquipamento(String modelo,String descricao,String NIF) throws ObjetoExistenteException, ObjetoNaoExistenteException, FuncionarioTipoErradoException; 
    
    void registarCliente(String NIF, String nome, String email, String numero) throws ObjetoExistenteException, FuncionarioTipoErradoException;
    
    String registarFuncionario(String nome,String codigo,int tipo) throws ObjetoExistenteException, FuncionarioTipoErradoException;
    
    int registarPedidoOrcamento(int codE) throws ObjetoNaoExistenteException, ObjetoExistenteException, FuncionarioTipoErradoException;
    
    int registarOrcamento(int codE,List<Passo> passos) throws ObjetoNaoExistenteException, ObjetoExistenteException, FuncionarioTipoErradoException;

    int registarOrcamento(int codE,Map<Integer, List<String>> passos) throws ObjetoNaoExistenteException, ObjetoExistenteException, FuncionarioTipoErradoException;
    
    int aceitarOrcamento(int codO) throws ObjetoNaoExistenteException, ObjetoExistenteException, FuncionarioTipoErradoException;
    
    void recusarOrcamento(int codO) throws ObjetoNaoExistenteException, FuncionarioTipoErradoException;

    void arquivarOrcamento(int codO) throws ObjetoNaoExistenteException;
    
    void atualizarReparacao(int codR,int passo,int tempo,float custo) throws ObjetoNaoExistenteException, FuncionarioTipoErradoException;
    
    void registarConclusaoReparacao(int codE) throws ObjetoNaoExistenteException, FuncionarioTipoErradoException;
    
    int registarEntrega(int codE) throws ObjetoNaoExistenteException, ObjetoExistenteException, FuncionarioTipoErradoException, EquipamentoNaoEstaProntoParaEntregaException ;

    void baixaEquipamento(int codE) throws ObjetoNaoExistenteException;

    int registarServicoExpresso(int codE,float preco, String descricao) throws ObjetoNaoExistenteException, ObjetoExistenteException, FuncionarioTipoErradoException;

    void registarConclusaoServicoExpresso(int codE) throws ObjetoNaoExistenteException, FuncionarioTipoErradoException;
    
    void registaContactoCliente(String codC,LocalDateTime data) throws ObjetoNaoExistenteException, FuncionarioTipoErradoException;
    
    List<String> consultarEquipamentosCliente(String codC) throws ObjetoNaoExistenteException;
    
    List<String> consultarPedidosOrcamentos();
    
    List<String> consultarServicoExpresso();
    
    List<String> consultarReparacoes();
    
    List<String> consultarOrcamentos();

    Map<Integer, List<String>> consultaPassosFromReparacao(int codR) throws ObjetoNaoExistenteException;

    List<Passo> createPassosFromMap(Map<Integer, List<String>> passos);

    Map<String, String> getNomesFromFuncionariosId(Set<String> ids);

    String getClienteFromReparacao(int codR) throws ObjetoNaoExistenteException;

    Map<String, Map<String, List<String>>> consultarListagemIntervencoes();

    Map<String, List<Double>> consultarListagemTecnicos();
    
    Map<String, List<Integer>> consultarListagemFuncionariosBalcao();

}
