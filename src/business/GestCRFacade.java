package src.business;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import src.business.SSGestEntidades.*;
import src.business.ssGestRegistos.*;


public class GestCRFacade implements IGestCRLN {
    private IGestEntidades gestEntidades;
    private IGestRegistos gestRegistos;
    private String codFLogado;

    public GestCRFacade() {
        this.gestEntidades = new GestEntidadesFacade();
        this.gestRegistos = new GestRegistosFacade();
        this.codFLogado = null;
    }


    //TODO: Fazer os diagramas de sequencia e implementar todos estes métodos.

    public boolean autenticarFuncionario(String codF) {
        boolean autenticado = this.gestEntidades.autenticarFuncionario(codF);
        if (autenticado) {
            this.codFLogado = codF;
        }
        return autenticado;
    }

    public boolean verificaEquipamento(String codE) {
        return this.gestEntidades.verificaEquipamento(codE);
    }

    public boolean verificaCliente(String codC) {
        return this.gestEntidades.verificaCliente(codC);
    }
    
    public String registarEquipamento(String modelo,String descricao) {
        return this.gestEntidades.registarEquipamento(modelo, descricao, 0);
    }
    
    public void registarCliente(String NIF, String nome, String email, String numero) {
        this.gestEntidades.registarCliente(NIF, nome, email, numero);
    }

    public String registarFuncionario(String nome,int tipo) {
        return this.gestEntidades.registarFuncionario(nome, tipo);
    }

    public void removerFuncionario(String codF){
        this.gestEntidades.removerFuncionario(codF);
    }
    
    public void associarEquipamentoCliente(String codE, String NIF) {
        this.gestEntidades.associarEquipamentoCliente(codE, NIF);
    }

    public List<String> consultarEquipamentosCliente(String codC) {
        return this.gestEntidades.consultarEquipamentosCliente(codC);
    }


    public void registarServicoExpresso(String codE,float preco, String descricao) {
        // Mudar o estado do equipamento para que este passe a por reparar.
        this.gestEntidades.alterarEstadoEquipamento(codE, 1);
        this.gestRegistos.registarServicoExpresso(codE, codFLogado, preco, descricao);
    }
    
    public void registarConclusaoServicoExpresso(String codE) {
        // Mudar o estado do equipamento para que este passe a reparado.
        this.gestEntidades.alterarEstadoEquipamento(codE,2);
        this.gestRegistos.registarConclusaoReparacao(codE);
    }

    public void registarPedidoOrcamento(String codE) {
        this.gestRegistos.registarPedidoOrcamento(codE, codFLogado);
        this.gestEntidades.alterarEstadoEquipamento(codE,0);
    }
    
    public void registarOrcamento(String codE, List<Passo> passos) {
        this.gestRegistos.registarOrcamento(codE, codFLogado, passos);
    }
    
    public void removerOrcamento(String codO) {
        this.gestRegistos.removerOrcamento(codO);
        this.gestEntidades.alterarEstadoEquipamento(codO, -1);
    }
    
    public void aceitarOrcamento(String codO) {
        this.gestRegistos.aceitarOrcamento(codO, codFLogado);
        this.gestEntidades.alterarEstadoEquipamento(codO, 1);
    }
    
    public void registarConclusaoReparacao(String codE) {
        this.gestRegistos.registarConclusaoReparacao(codE);
        // Alterar estado para indicar que este foi reparado.
        this.gestEntidades.alterarEstadoEquipamento(codE, 2);
    }
    
    public void enviarEmail(String codC,String codE) {
        //TODO: Ir buscar a informaçcao do cliente e o orçamento a enviar. Provavelmente deveriamos criar um ficheiro do orçamento para dar attach.
        // Nao fiz diagrama de sequencia pois pareceu me algo complicado de realizar mas o método é para ser assim implementado.
        final String username = "CRDSS@gmail.com";
        final String password = "Grupo7DSS";

        Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("from@gmail.com"));
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse("cliente@gmail.com"));
        message.setSubject("Orçamento para o equipamento");

        message.setText("Querido cliente,"
                + "\n\n Please do not spam my email!");

        Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
    public void baixaEquipamento(String codE) {
        this.gestEntidades.alterarEstadoEquipamento(codE, -2);
    }
    
    public String procuraPlanoTrabalhosEquipamento(String codE) {
        PlanoTrabalhos pt = this.gestRegistos.procuraPlanoTrabalhosEquipamento(codE);
        return pt.toString();
    }

    public String procuraPedidoOrcamento(String codE) {
        PedidoOrcamento po = this.gestRegistos.procuraPedidoOrcamento(codE);
        return po.toString();
    }

    public void atualizarPlanoTrabalhos(String codE,Passo passo) {
        PlanoTrabalhos pt = this.gestRegistos.procuraPlanoTrabalhosEquipamento(codE);
        passo.setFuncionario(codFLogado);
        pt.atualizaPlanoTrabalhos(passo);
    }
    
    //TODO: Diagrama de sequencia
    public void registaContactoCliente(String codC,LocalDateTime data) {
        this.gestRegistos.registaContactoCliente(codFLogado, codC, data);
    }
    
    //FIXME: PENSAR DE COMO VERIFICAR
    public boolean verificarServicoExpresso() {
        return this.gestRegistos.verificarServicoExpresso();
    }

    List<String> consultarPedidosOrcamentos();

    List<String> consultarServicoExpresso();

    List<String> consultarReparacoes();

    List<String> consultarOrcamentos();

    List<String> consultarListagemIntervencoes() ;

    List<String> consultarListagemTecnicos();
    
    public List<String> consultarListagemFuncionariosBalcao() {
        return this.gestRegistos.consultarListagemFuncionariosBalcao();
    }

    private boolean verificaFuncionarioBalcao() {
        if (this.gestEntidades.verificaTipoFuncionario(codFLogado) == 1) return true;
        else return false;
    }

    private boolean verificaTecnicoReparacoes() {
        if (this.gestEntidades.verificaTipoFuncionario(codFLogado) == 2) return true;
        else return false;
    }

    private boolean verificaGestor() {
        if (this.gestEntidades.verificaTipoFuncionario(codFLogado) == 3) return true;
        else return false;
    }
}
