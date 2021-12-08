package src.business.ssGestRegistos;

import java.util.List;
import java.time.LocalDateTime;

public interface IGestRegistos {

    void registarConclusaoReparacao(String codE);

    void removerOrcamento(String codO);

    void registarPedidoOrcamento(String codE,String codF);

    PlanoTrabalhos procuraPlanoTrabalhosEquipamento(String codE);

    void registarOrcamento(String codE,String codF,List<Passo> passos);

    void aceitarOrcamento(String codO,String codFuncionario);

    boolean verificarServicoExpresso();

    void registarServicoExpresso(String codE,String codF,float valor,String descricao);

    void registarConclusaoServicoExpresso(String codE);

    PedidoOrcamento procuraPedidoOrcamento(String codE);

    List<PedidoOrcamento> consultarPedidosOrcamentos();

    void atualizarReparacao(String codE,Passo p);

    void registaContactoCliente(String codF,String codC,LocalDateTime data);
    
    List<ServicoExpresso> consultarServicoExpresso();

    List<Reparacao> consultarReparacoes();

    List<Orcamento> consultarOrcamentos();

    //TODO: Fazer os métodos de listagem
    List<String> consultarListagemIntervencoes();

    List<String> consultarListagemTecnicos();
    
    List<String> consultarListagemFuncionariosBalcao();
}