package src.business.ssGestRegistos;

import java.util.List;
import java.time.LocalDate;

public interface IGestRegistos {

    void registarConclusaoReparacao(String codE,String codC);

    void removerOrcamento(String codO);

    String registarPedidoOrcamento(String codE);

    PlanoTrabalho procuraPlanoTrabalhosEquipamento(String codE);

    String registarOrcamento(String codE);

    void armazenarOrcamento(String codO);

    boolean verificarServicoExpresso();

    void registarServicoExpresso(String codE);

    void registarConclusaoServicoExpresso(String codE);

    PedidoOrcamento procuraPedidoOrcamento(String codE);

    String registarPlanoTrabalho(List<Passo> passos,String codE);

    List<PedidoOrcamento> consultarPedidosOrcamentos(int criterio);

    void atualizarPlanoTrabalhos(Passo passo, int hora, int custo);

    void registaContactoCliente(String codF,LocalDate data);
    
    List<ServicoExpresso> consultarServicoExpresso();

    List<Reparacao> consultarReparacoes();

    List<Orcamento> consultarOrcamentos();

    List<String> consultarListagemIntervencoes() ;

    List<String> consultarListagemTecnicos();
    
    List<String> consultarListagemFuncionariosBalcao();
}