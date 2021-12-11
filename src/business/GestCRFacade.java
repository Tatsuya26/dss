package src.business;

import java.util.List;
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
    
    public void associarEquipamentoCliente(String codE, String NIF) {
        this.gestCliente.associarEquipamentoCliente(codE, NIF);
        this.gestEquipamentos.associarEquipamentoCliente(codE, NIF);
    }

    public void registarServicoExpresso(String codE,float preco, String descricao) {
        // Mudar o estado do equipamento para que este passe a por reparar.
        this.gestEquipamentos.alterarEstado(codE, 0);
        this.gestRegistos.registarServicoExpresso(codE, codFLogado, preco, descricao);
    }
    
    public void registarConclusaoServicoExpresso(String codE) {
        // Mudar o estado do equipamento para que este passe a reparado.
        this.gestEquipamentos.alterarEstado(codE,1);
        this.gestRegistos.registarConclusaoReparacao(codE);
    }

    public void registarPedidoOrcamento(String codE) {
        this.gestRegistos.registarPedidoOrcamento(codE, codFLogado);
        this.gestEquipamentos.alterarEstado(codE,0);
    }
    

    public void registarOrcamento(String codE, List<Passo> passos) {
        this.gestRegistos.registarOrcamento(codE, codFLogado, passos);
    }
    
    public void removerOrcamento(String codO) {
        this.gestRegistos.removerOrcamento(codO);
        this.gestEquipamentos.alterarEstado(codO, -1);
    }
    
    public void aceitarOrcamento(String codO) {
        this.gestRegistos.aceitarOrcamento(codO, codFLogado);
        //TODO: Alterar o estado do equipamento para sinalizar que este foi aceite para reparacao.
    }
    
    public void registarConclusaoReparacao(String codE) {
        this.gestRegistos.registarConclusaoReparacao(codE);
        // Alterar estado para indicar que este foi reparado.
        this.gestEquipamentos.alterarEstado(codE, 3);
    }
    
    void enviarEmail(String codC);
    
    
    List<String> consultarEquipamentosCliente(String codC);
    
    void alterarEstadoEntregue(String codE);
    
    void baixaEquipamento(String codE);
    
    PlanoTrabalho procuraPlanoTrabalhosEquipamento(String codE);

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
