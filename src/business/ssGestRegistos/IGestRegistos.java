package src.business.ssGestRegistos;


import java.util.List;

import src.business.ObjetoExistenteException;
import src.business.ObjetoNaoExistenteException;
import src.business.SSGestEntidades.Cliente;
import src.business.SSGestEntidades.Equipamento;
import src.business.SSGestEntidades.Funcionario;

import java.time.LocalDateTime;

public interface IGestRegistos {
    
    int registarPedidoOrcamento(Equipamento codE,Funcionario codF) throws ObjetoExistenteException;
    
    int registarOrcamento(Equipamento codE,Funcionario codF,List<Passo> passos) throws ObjetoExistenteException;
    
    int aceitarOrcamento(int codO,Funcionario codFuncionario) throws ObjetoExistenteException, ObjetoNaoExistenteException;
    
    void removerOrcamento(int codO) throws ObjetoNaoExistenteException;
    
    void arquivarOrcamento(int codO) throws ObjetoNaoExistenteException;
    
    void atualizarReparacao(int codR,int p,int tempo,float custo,Funcionario f) throws ObjetoNaoExistenteException;
    
    void registarConclusaoReparacao(int codR,Funcionario f) throws ObjetoNaoExistenteException;
    
    int registarEntrega(Equipamento codE,Funcionario codF) throws ObjetoExistenteException;
    
    boolean verificarServicoExpresso();
    
    int registarServicoExpresso(Equipamento codE,Funcionario codF,float valor,String descricao) throws ObjetoExistenteException;
    
    void registarConclusaoServicoExpresso(int codR, Funcionario funcionario) throws ObjetoNaoExistenteException;
    
    void registaContactoCliente(Funcionario codF,Cliente codC,LocalDateTime data);
    
    PedidoOrcamento getPedidoOrcamentoByID(int codR) throws ObjetoNaoExistenteException;
    
    Orcamento getOrcamentoByID(int codR) throws ObjetoNaoExistenteException;
    
    Reparacao getReparacaoByID(int codR) throws ObjetoNaoExistenteException;
    
    Entrega getEntregaByID(int codR) throws ObjetoNaoExistenteException;
    
    ServicoExpresso getServicoExpressoByID(int codR) throws ObjetoNaoExistenteException;
    
    List<PedidoOrcamento> consultarPedidosOrcamentos();
    
    List<ServicoExpresso> consultarServicoExpresso();

    List<Reparacao> consultarReparacoes();

    List<Orcamento> consultarOrcamentos();

    List<Entrega> consultarEntregas();

    List<String> consultarListagemIntervencoes(String codF);

    List<Double> consultarListagemTecnicos(String codF);
    
    List<String> consultarListagemFuncionariosBalcao();


}