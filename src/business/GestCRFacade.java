package src.business;

import java.util.Random;

import src.business.ssGestCliente.*;
import src.business.ssGestEquipamentos.*;
import src.business.ssGestFuncionarios.*;
import src.business.ssGestRegistos.*;


public class GestCRFacade implements IGestCRLN {
    private IGestClientes      gestCliente;
    private IGestEquipamentos gestEquipamentos;
    private IGestFuncionarios  gestFuncionario;
    private IGestRegistos     gestRegistos;
    private String codFLogado;

    public GestCRFacade() {
        this.gestCliente      = new GestClientesFacade();
        this.gestEquipamentos = new GestEquipamentosFacade();
        this.gestFuncionario  = new GestFuncionariosFacade();
        this.gestRegistos     = new GestRegistosFacade();
        this.codFLogado = null;
    }


    //TODO: Fazer os diagramas de sequencia e implementar todos estes métodos.

    public boolean autenticarFuncionario(String codF) {
        boolean autenticado = this.gestFuncionario.autenticarFuncionario(codF);
        if (autenticado) {
            this.codFLogado = codF;
        }
        return autenticado;
    }

    public boolean verificaEquipamento(String codE) {
        return this.gestEquipamentos.verificaEquipamento(codE);
    }

    public boolean verificaCliente(String codC) {
        return this.gestCliente.verificaCliente(codC);
    }

    public String registarEquipamento(String modelo,String descricao) {
        byte[] bytes = new byte[7];
        new Random().nextBytes(bytes);
        String codGerado = new String(bytes);
        this.gestEquipamentos.registarEquipamento(codGerado, modelo, descricao, 0);
        return codGerado;
    }

    public void registarCliente(String NIF, String nome, String email, String numero) {
        this.gestCliente.registarCliente(NIF, nome, email, numero);
    }
    
    public void registarServicoExpresso(String codE,float preco, String descricao) {
        this.gestRegistos.registarServicoExpresso(codE, codFLogado, preco, descricao);
    }
    
    void registarConclusaoServicoExpresso(String codE);

    String registarPedidoOrcamento(String codE);
    
    //String registarPlanoTrabalho(List<Passo> passos,String codE);

    String registarOrcamento(String codE);
    
    void registarConclusaoReparacao(String codE,String codC);
    
    void removerOrcamento(String codO);

    void enviarEmail(String codC);
    
    void associarEquipamentoCliente(String codE, String NIF);

    List<String> consultarEquipamentosCliente(String codC);

    void alterarEstadoEntregue(String codE);

    void baixaEquipamento(String codE);

    PlanoTrabalho procuraPlanoTrabalhosEquipamento(String codE);


    void aceitarOrcamento(String codO);

    boolean verificarServicoExpresso();



    PedidoOrcamento procuraPedidoOrcamento(String codE);


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
