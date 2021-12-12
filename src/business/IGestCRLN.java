package src.business;

import java.time.LocalDate;
import java.util.*;

public interface IGestCRLN {
    boolean autenticarFuncionario(String codF);

    boolean verificaEquipamento(String codE);

    boolean verificaCliente(String codC);

    void registarConclusaoReparacao(String codE);

    void removerOrcamento(String codO);

    void enviarEmail(String codC);
    
    String registarEquipamento(String modelo,String descricao); 

    void associarEquipamentoCliente(String codE, String NIF);

    void registarPedidoOrcamento(String codE);

    void registarCliente(String NIF, String nome, String email, String numero);

    List<String> consultarEquipamentosCliente(String codC);

    void alterarEstadoEntregue(String codE);

    void baixaEquipamento(String codE);

    String procuraPlanoTrabalhosEquipamento(String codE);

    void registarOrcamento(String codE,List<Passo> passos);

    void aceitarOrcamento(String codO);

    boolean verificarServicoExpresso();

    void registarServicoExpresso(String codE,float preco, String descricao);

    void registarConclusaoServicoExpresso(String codE);

    String procuraPedidoOrcamento(String codE);

    List<String> consultarPedidosOrcamentos(int criterio);

    void atualizarPlanoTrabalhos(String codE,Passo passo, int hora, int custo);

    void registaContactoCliente(String codF,LocalDate data);
    
    boolean verificarServicoExpresso();

    List<String> consultarServicoExpresso();

    List<String> consultarReparacoes();

    List<String> consultarOrcamentos();

    List<String> consultarListagemIntervencoes() ;

    List<String> consultarListagemTecnicos();
    
    List<String> consultarListagemFuncionariosBalcao();

}
