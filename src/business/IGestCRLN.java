package src.business;

import java.time.LocalDate;
import java.util.*;

public interface IGestCRLN {
    boolean autenticarFuncionario(String codF);

    boolean verificaEquipamento(String codE);

    boolean verificaCliente(String codC);

    void registarConclusaoReparacao(String codE,String codC);

    void removerOrcamento(String codO);

    void enviarEmail(String codC);
    
    String registarEquipamento(String modelo,String descricao); 

    void associarEquipamentoCliente(String codE, String NIF);

    String registarPedidoOrcamento(String codE);

    void registarCliente(String NIF, String nome, String email, String numero);

    List<Equipamento> consultarEquipamentosCliente(codC : String);

    void alterarEstadoEntregue(String codE);

    void baixaEquipamento(String codE);

    PlanoTrabalho procuraPlanoTrabalhosEquipamento(String codE);

    String registarOrcamento(String codE);

    void armazenarOrcamento(String codO);

    boolean verificarServicoExpresso();

    void registarServicoExpresso(String codE);

    void registarConclusaoServicoExpresso(String codE);

    PedidoOrcamento procuraPedidoOrcamento(String codE);

    String registarPlanoTrabalho(List<Passo> passos,String codE);

    List<PedidoOrcamento> consultarPedidosOrcamentos(criterio : int);

    void atualizarPlanoTrabalhos(Passo passo, int hora, int custo);

    void registaContactoCliente(String codF,LocalDate data);
    
    List<ServicoExpresso> consultarServicoExpresso();

    List<Reparacao> consultarReparacoes();

    List<Orcamento> consultarOrcamentos();

    List<String> consultarListagemIntervencoes() ;

    List<String> consultarListagemTecnicos();
    
    List<String> consultarListagemFuncionariosBalcao();

}
