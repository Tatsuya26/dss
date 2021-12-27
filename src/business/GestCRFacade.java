package src.business;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import src.business.SSGestEntidades.*;
import src.business.ssGestRegistos.*;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class GestCRFacade implements IGestCRLN {
    private IGestEntidades gestEntidades;
    private IGestRegistos gestRegistos;
    public Funcionario funcionario;

    public GestCRFacade() {
        Configuration con = new Configuration().configure();
        con.addAnnotatedClass(Orcamento.class);
        con.addAnnotatedClass(PlanoTrabalhos.class);
        con.addAnnotatedClass(Passo.class);
        con.addAnnotatedClass(PedidoOrcamento.class);
        con.addAnnotatedClass(Reparacao.class);
        con.addAnnotatedClass(Entrega.class);
        con.addAnnotatedClass(ServicoExpresso.class);
        con.addAnnotatedClass(Contacto.class);
        con.addAnnotatedClass(Equipamento.class);
        con.addAnnotatedClass(Funcionario.class);
        con.addAnnotatedClass(FuncionarioBalcao.class);
        con.addAnnotatedClass(TecnicoReparacoes.class);
        con.addAnnotatedClass(Gestor.class);
        con.addAnnotatedClass(Cliente.class);
        StandardServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();

        SessionFactory sf = con.buildSessionFactory(sr);
        this.gestRegistos = new GestRegistosFacade(sf);
        this.gestEntidades = new GestEntidadesFacade(sf);
        this.funcionario = new Gestor("Manel", "1111");
    }



    public int autenticarFuncionario(String codF) throws ObjetoNaoExistenteException{
        boolean autenticado = this.gestEntidades.autenticarFuncionario(codF);
        if (autenticado) {
            this.funcionario = this.gestEntidades.getFuncionarioByCod(codF);
            return this.gestEntidades.verificaTipoFuncionario(codF);
        }
        return 0;
    }

    public boolean verificaEquipamento(int codE) {
        return this.gestEntidades.verificaEquipamento(codE);
    }

    public boolean verificaCliente(String codC) {
        return this.gestEntidades.verificaCliente(codC);
    }
    
    //TODO: Throw exception de funcionario errado
    public int registarEquipamento(String modelo,String descricao ,String NIF) throws ObjetoExistenteException,ObjetoNaoExistenteException{
        if (this.funcionario instanceof FuncionarioBalcao)
            return this.gestEntidades.registarEquipamento(modelo, descricao, 0,NIF);
        else return -1;
    }
    
    public void registarCliente(String NIF, String nome, String email, String numero) throws ObjetoExistenteException{
        if (this.funcionario instanceof FuncionarioBalcao)
            this.gestEntidades.registarCliente(NIF, nome, email, numero);
    }

    public String registarFuncionario(String nome,int tipo) throws ObjetoExistenteException {
        if (this.funcionario instanceof Gestor)
            return this.gestEntidades.registarFuncionario(nome, tipo);
        else return "";
    }

    public List<String> consultarEquipamentosCliente(String codC) throws ObjetoNaoExistenteException {
        return this.gestEntidades.consultarEquipamentosCliente(codC).stream().map(Equipamento :: toString).collect(Collectors.toList());
        
    }

    public int registarEntrega (int codE) throws ObjetoNaoExistenteException, ObjetoExistenteException {
        if (this.funcionario instanceof FuncionarioBalcao) {
            Equipamento e = this.gestEntidades.getEquipamentoByID(codE);
            return this.gestRegistos.registarEntrega(e, funcionario);
        }
        else return -1;
    }


    public int registarServicoExpresso(int codE,float preco, String descricao) throws ObjetoNaoExistenteException, ObjetoExistenteException {
        // Mudar o estado do equipamento para que este passe a por reparar.
        if (this.funcionario instanceof FuncionarioBalcao) {
            this.gestEntidades.alterarEstadoEquipamento(codE, 1);
            Equipamento e = this.gestEntidades.getEquipamentoByID(codE);
            return this.gestRegistos.registarServicoExpresso(e, funcionario, preco, descricao);
        }
        else return -1;
    }
    
    public void registarConclusaoServicoExpresso(int codR) throws ObjetoNaoExistenteException {
        // Mudar o estado do equipamento para que este passe a reparado.
        if (this.funcionario instanceof TecnicoReparacoes) {
            Equipamento e = this.gestRegistos.getServicoExpressoByID(codR).getEquipamento();
            this.gestEntidades.alterarEstadoEquipamento(e.getIdEquipamento(),2);
            this.gestRegistos.registarConclusaoServicoExpresso(codR);
        }
    }

    public int registarPedidoOrcamento(int codE) throws ObjetoNaoExistenteException, ObjetoExistenteException {
        if (this.funcionario instanceof FuncionarioBalcao) {
            this.gestEntidades.alterarEstadoEquipamento(codE,0);
            Equipamento e = this.gestEntidades.getEquipamentoByID(codE);
            return this.gestRegistos.registarPedidoOrcamento(e, funcionario);
        }
        return -1;
    }
    
    public int registarOrcamento(int codE, List<Passo> passos) throws ObjetoNaoExistenteException, ObjetoExistenteException {
        if (this.funcionario instanceof TecnicoReparacoes){
            Equipamento e = this.gestEntidades.getEquipamentoByID(codE);
            int codR = this.gestRegistos.registarOrcamento(e, funcionario, passos);
            Cliente c = e.getCliente();
            enviarEmailOrcamento(c.getNIF(), codR);
            return codR;
        }
        return -1;
    }
    
    public void removerOrcamento(int codR) throws ObjetoNaoExistenteException {
        if (this.funcionario instanceof TecnicoReparacoes) {
            Equipamento e = this.gestRegistos.getOrcamentoByID(codR).getEquipamento();
            this.gestRegistos.removerOrcamento(codR);
            this.gestEntidades.alterarEstadoEquipamento(e.getIdEquipamento(), -1);
        }
    }
    
    public int aceitarOrcamento(int codO) throws ObjetoNaoExistenteException, ObjetoExistenteException {
        if (this.funcionario instanceof TecnicoReparacoes) {
            int codE = this.gestRegistos.getOrcamentoByID(codO).getEquipamento().getIdEquipamento();
            this.gestEntidades.alterarEstadoEquipamento(codE, 1);
            return this.gestRegistos.aceitarOrcamento(codO, funcionario);
        }
        return -1;
    }
    
    //TODO: Temos de enviar email ao cliente
    public void registarConclusaoReparacao(int codR) throws ObjetoNaoExistenteException {
        if (this.funcionario instanceof TecnicoReparacoes) {
            Equipamento e = this.gestRegistos.getReparacaoByID(codR).getEquipamento();
            Cliente c = e.getCliente();
            this.gestRegistos.registarConclusaoReparacao(codR);
            this.gestEntidades.alterarEstadoEquipamento(e.getIdEquipamento(), 2);
            this.enviarEmailReparacao(c.getNIF(), codR);
        }
    }
    
    public void enviarEmailOrcamento(String NIF,int codR) throws ObjetoNaoExistenteException {
        //TODO: Ir buscar a informaçcao do cliente e o orçamento a enviar. Provavelmente deveriamos criar um ficheiro do orçamento para dar attach.
        // Nao fiz diagrama de sequencia pois pareceu me algo complicado de realizar mas o método é para ser assim implementado.
        Cliente c = this.gestEntidades.getClienteByNIF(NIF);
        Orcamento o = this.gestRegistos.getOrcamentoByID(codR); 
            

        final String username = "crdssg7@gmail.com";
        final String password = "crdssg07";

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
        message.setFrom(new InternetAddress(username));
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(c.getEmail()));
        message.setSubject("Orçamento para o equipamento");

        message.setText("Querido cliente,"
                + "\nNão responda para este email.Para aceitar o orçamento ligue para 919275976 ou envie mail para crdssorcamentos@gmail.com\n\n" + o.toString());

        Transport.send(message);
        } catch (MessagingException e) {
           
        }
    }

    public void enviarEmailReparacao(String NIF,int codR) throws ObjetoNaoExistenteException {
        //TODO: Ir buscar a informaçcao do cliente e o orçamento a enviar. Provavelmente deveriamos criar um ficheiro do orçamento para dar attach.
        // Nao fiz diagrama de sequencia pois pareceu me algo complicado de realizar mas o método é para ser assim implementado.
        Cliente c = this.gestEntidades.getClienteByNIF(NIF);
        Reparacao r = this.gestRegistos.getReparacaoByID(codR); 
            

        final String username = "crdssg7@gmail.com";
        final String password = "crdssg07";

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
        message.setFrom(new InternetAddress(username));
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(c.getEmail()));
        message.setSubject("Reparacao concluida para o equipamento");

        message.setText("Querido cliente,"
                + "\nNão responda para este email.O seu equipamento foi reparado com sucesso.\n\n" + r.toString());

        Transport.send(message);
        } catch (MessagingException e) {
           
        }
    }
    
    public void baixaEquipamento(int codE) throws ObjetoNaoExistenteException {
        this.gestEntidades.alterarEstadoEquipamento(codE, -2);
    }

    public void atualizarReparacao(int codE,int passo,int tempo,float custo) throws ObjetoNaoExistenteException {
        if (this.funcionario instanceof TecnicoReparacoes) {
            this.gestRegistos.atualizarReparacao(codE, passo,tempo,custo);
        }
    }
    
    public void registaContactoCliente(String codC,LocalDateTime data) throws ObjetoNaoExistenteException {
        if (this.funcionario instanceof TecnicoReparacoes) {
            Cliente c = this.gestEntidades.getClienteByNIF(codC);
            this.gestRegistos.registaContactoCliente(funcionario, c, data);
        }
    }
    
    public boolean verificarServicoExpresso() {
        return this.gestRegistos.verificarServicoExpresso();
    }

    public List<String> consultarPedidosOrcamentos() {
        List<PedidoOrcamento> pos = this.gestRegistos.consultarPedidosOrcamentos();
        return pos.stream().map(PedidoOrcamento :: toString).collect(Collectors.toList());
    }

    public List<String> consultarServicoExpresso() {
        List<ServicoExpresso> pos = this.gestRegistos.consultarServicoExpresso();
        return pos.stream().map(ServicoExpresso :: toString).collect(Collectors.toList());
    }

    public List<String> consultarReparacoes() {
        List<Reparacao> pos = this.gestRegistos.consultarReparacoes();
        return pos.stream().map(Reparacao :: toString).collect(Collectors.toList());
    }

    public List<String> consultarOrcamentos() {
        List<Orcamento> pos = this.gestRegistos.consultarOrcamentos();
        return pos.stream().map(Orcamento :: toString).collect(Collectors.toList());
    }

    public List<String> consultarEntregas() {
        List<Entrega> pos = this.gestRegistos.consultarEntregas();
        return pos.stream().map(Entrega :: toString).collect(Collectors.toList());
    }

    public List<String> consultarListagemIntervencoes() {
        return this.gestRegistos.consultarListagemIntervencoes("23232");
    }

    public List<Double> consultarListagemTecnicos() {
        return this.gestRegistos.consultarListagemTecnicos("3232");
    }
    
    public List<String> consultarListagemFuncionariosBalcao() {
        return this.gestRegistos.consultarListagemFuncionariosBalcao();
    }
}
