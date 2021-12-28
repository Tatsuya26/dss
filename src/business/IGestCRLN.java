package src.business;


import java.time.LocalDateTime;
import java.util.*;

import src.business.ssGestRegistos.Passo;

public interface IGestCRLN {
    int autenticarFuncionario(String codF) throws ObjetoNaoExistenteException;

    boolean verificaEquipamento(int codE);

    boolean verificaCliente(String codC);

    void registarConclusaoReparacao(int codE) throws ObjetoNaoExistenteException, FuncionarioTipoErradoException;

    void recusarOrcamento(int codO) throws ObjetoNaoExistenteException, FuncionarioTipoErradoException;

    String registarFuncionario(String nome,int tipo) throws ObjetoExistenteException, FuncionarioTipoErradoException;
    
    void enviarEmailOrcamento(String codC,int codE) throws ObjetoNaoExistenteException;
    
    int registarEquipamento(String modelo,String descricao,String NIF) throws ObjetoExistenteException, ObjetoNaoExistenteException, FuncionarioTipoErradoException; 

    int registarPedidoOrcamento(int codE) throws ObjetoNaoExistenteException, ObjetoExistenteException, FuncionarioTipoErradoException;

    void registarCliente(String NIF, String nome, String email, String numero) throws ObjetoExistenteException, FuncionarioTipoErradoException;

    List<String> consultarEquipamentosCliente(String codC) throws ObjetoNaoExistenteException;

    void baixaEquipamento(int codE) throws ObjetoNaoExistenteException;

    int registarOrcamento(int codE,List<Passo> passos) throws ObjetoNaoExistenteException, ObjetoExistenteException, FuncionarioTipoErradoException;

    int aceitarOrcamento(int codO) throws ObjetoNaoExistenteException, ObjetoExistenteException, FuncionarioTipoErradoException;

    int registarEntrega(int codE) throws ObjetoNaoExistenteException, ObjetoExistenteException, FuncionarioTipoErradoException;

    int registarServicoExpresso(int codE,float preco, String descricao) throws ObjetoNaoExistenteException, ObjetoExistenteException, FuncionarioTipoErradoException;

    void registarConclusaoServicoExpresso(int codE) throws ObjetoNaoExistenteException, FuncionarioTipoErradoException;

    List<String> consultarPedidosOrcamentos();

    void atualizarReparacao(int codE,int passo,int tempo,float custo) throws ObjetoNaoExistenteException, FuncionarioTipoErradoException;

    void registaContactoCliente(String codC,LocalDateTime data) throws ObjetoNaoExistenteException, FuncionarioTipoErradoException;
    
    boolean verificarServicoExpresso();

    List<String> consultarServicoExpresso();

    List<String> consultarReparacoes();

    List<String> consultarOrcamentos();

    Map<String, List<String>> consultarListagemIntervencoes() ;

    Map<String, List<Double>> consultarListagemTecnicos();
    
    List<String> consultarListagemFuncionariosBalcao();

}
