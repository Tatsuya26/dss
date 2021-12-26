package src.business;

import java.time.LocalDateTime;
import java.util.*;

import src.business.ssGestRegistos.Passo;

public interface IGestCRLN {
    int autenticarFuncionario(String codF) throws ObjetoNaoExistenteException;

    boolean verificaEquipamento(int codE);

    boolean verificaCliente(String codC);

    void registarConclusaoReparacao(int codE) throws ObjetoNaoExistenteException;

    void removerOrcamento(int codO) throws ObjetoNaoExistenteException;

    String registarFuncionario(String nome,int tipo) throws ObjetoExistenteException;
    
    int removerFuncionario(String codF) throws ObjetoNaoExistenteException;
    
    void enviarEmail(String codC,int codE) throws ObjetoNaoExistenteException;
    
    int registarEquipamento(String modelo,String descricao,String NIF) throws ObjetoExistenteException, ObjetoNaoExistenteException; 

    int registarPedidoOrcamento(int codE) throws ObjetoNaoExistenteException, ObjetoExistenteException;

    void registarCliente(String NIF, String nome, String email, String numero) throws ObjetoExistenteException;

    List<String> consultarEquipamentosCliente(String codC) throws ObjetoNaoExistenteException;

    void baixaEquipamento(int codE) throws ObjetoNaoExistenteException;

    int registarOrcamento(int codE,List<Passo> passos) throws ObjetoNaoExistenteException, ObjetoExistenteException;

    int aceitarOrcamento(int codO) throws ObjetoNaoExistenteException, ObjetoExistenteException;

    int registarEntrega(int codE) throws ObjetoNaoExistenteException, ObjetoExistenteException;

    int registarServicoExpresso(int codE,float preco, String descricao) throws ObjetoNaoExistenteException, ObjetoExistenteException;

    void registarConclusaoServicoExpresso(int codE) throws ObjetoNaoExistenteException;

    List<String> consultarPedidosOrcamentos();

    void atualizarReparacao(int codE,Passo passo) throws ObjetoNaoExistenteException;

    void registaContactoCliente(String codC,LocalDateTime data) throws ObjetoNaoExistenteException;
    
    boolean verificarServicoExpresso();

    List<String> consultarServicoExpresso();

    List<String> consultarReparacoes();

    List<String> consultarOrcamentos();

    List<String> consultarListagemIntervencoes() ;

    List<Double> consultarListagemTecnicos();
    
    List<String> consultarListagemFuncionariosBalcao();

}
